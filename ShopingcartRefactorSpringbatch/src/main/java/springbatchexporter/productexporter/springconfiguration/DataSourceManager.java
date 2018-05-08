package springbatchexporter.productexporter.springconfiguration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;


@Configuration
public class DataSourceManager {

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
        comboPooledDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        comboPooledDataSource.setUser("DU");
        comboPooledDataSource.setPassword("wasd1234");
        return comboPooledDataSource;
    }
}
