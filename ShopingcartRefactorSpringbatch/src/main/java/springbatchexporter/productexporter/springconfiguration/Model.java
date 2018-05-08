package springbatchexporter.productexporter.springconfiguration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.Properties;

@Import(DataSourceManager.class)
@Configuration
public class Model {

    @Autowired
    public ComboPooledDataSource dataSource;

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(ComboPooledDataSource dataSource) {
        LocalSessionFactoryBean session = new LocalSessionFactoryBean();
        session.setDataSource(dataSource);
        session.setPackagesToScan("shopping_cart.product");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        session.setHibernateProperties(properties);
        return session;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

}
