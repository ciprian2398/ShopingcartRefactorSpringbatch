package springbatchexporter;

import springbatchexporter.productexporter.springconfiguration.SpringBatchContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBasedContextExporter {

    public static void main(String areg[]) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringBatchContext.class);
        context.refresh();

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("productResultJob");

        JobStarter.executeJob(jobLauncher, job);
    }

}
