package springbatchexporter.productexporter.springconfiguration;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import shopping_cart.product.Product;
import shopping_cart.product.validator.SipmleProductValidator;
import springbatchexporter.productexporter.FileMoveTasklet;
import springbatchexporter.productexporter.ProductFieldSetMapper;
import springbatchexporter.productexporter.ProductItemProcessor;
import springbatchexporter.productexporter.ProductJobListener;


@Import(Model.class)
@Configuration
public class SpringBatchContext {

    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public HibernateTransactionManager transactionManager;

    @Bean
    public MapJobRepositoryFactoryBean jobRepo() {
        return new MapJobRepositoryFactoryBean();
    }

    @Bean
    @Autowired
    public SimpleJobLauncher jobLauncher(JobRepository jobRepo) {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepo);
        return simpleJobLauncher;
    }

    @Bean
    @Autowired
    public MultiResourceItemReader resourceItemReader(FlatFileItemReader flatFileItemReader) {
        MultiResourceItemReader multiResourceItemReader = new MultiResourceItemReader();
        Resource resource = new ClassPathResource("csv/data.csv");
        Resource[] resources = new Resource[]{resource};
        multiResourceItemReader.setResources(resources);
        multiResourceItemReader.setDelegate(flatFileItemReader);
        return multiResourceItemReader;
    }

    @Bean
    @Autowired
    public FlatFileItemReader flatFileItemReader(LineMapper lineMapper) {
        FlatFileItemReader flatFileItemReader = new FlatFileItemReader();
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Bean
    @Autowired
    public LineMapper lineMapper(DelimitedLineTokenizer delimitedLineTokenizer,
                                 ProductFieldSetMapper mapper) {
        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        defaultLineMapper.setFieldSetMapper(mapper);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        return defaultLineMapper;
    }

    @Bean
    public ProductFieldSetMapper mapper() {
        return new ProductFieldSetMapper();
    }

    @Bean
    public DelimitedLineTokenizer delimitedLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(";");
        return delimitedLineTokenizer;
    }

    @Bean
    public HibernateItemWriter databaseItemWriter() {
        HibernateItemWriter hibernateItemWriter = new HibernateItemWriter();
        hibernateItemWriter.setSessionFactory(sessionFactory);
        return hibernateItemWriter;
    }

    @Bean
    @Autowired
    public ProductItemProcessor itemProcessor(SipmleProductValidator sipmleProductValidator) {
        ProductItemProcessor productItemProcessor = new ProductItemProcessor();
        productItemProcessor.setProductValidator(sipmleProductValidator);
        return productItemProcessor;
    }

    @Bean
    public SipmleProductValidator validator() {
        return new SipmleProductValidator();
    }

    @Bean
    public ProductJobListener jobListener() {
        return new ProductJobListener();
    }

    @Bean
    public FileMoveTasklet fileMovieTasklet() {
        return new FileMoveTasklet();
    }

    @Bean
    @Autowired
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepo) {
        return new JobBuilderFactory(jobRepo);
    }

    @Bean
    @Autowired
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepo) {
        return new StepBuilderFactory(jobRepo, transactionManager);
    }

    @Bean
    @Autowired
    public Job productResultJob(Step step1,
                                Step moveData,
                                ProductJobListener jobListener,
                                JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("productResultJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .flow(step1).next(moveData)
                .end()
                .build();
    }

    @Bean
    @Autowired
    public Step step1(HibernateItemWriter writer,
                      MultiResourceItemReader resourceItemReader,
                      ProductItemProcessor itemProcessor,
                      StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("step1")
                .<Product,Product>chunk(10)
                .reader(resourceItemReader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }


    @Bean
    @Autowired
    public Step moveData(StepBuilderFactory stepBuilderFactory,
                         FileMoveTasklet fileMovieTasklet) {
        return stepBuilderFactory.get("moveData")
                .tasklet(fileMovieTasklet)
                .build();
    }

}












