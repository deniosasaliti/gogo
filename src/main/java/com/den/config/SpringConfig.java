package com.den.config;





import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;



@Configuration
@ComponentScan("com.den")
@PropertySource("classpath:property.properties")
@EnableTransactionManagement
public class SpringConfig {

    @Value("${jdbc.url}")
    private String Url;
    @Value("${jdbc.username}")
    private String UserName;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driverClassName}")
    private String DriverClassName;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(Url);
        dataSource.setUsername(UserName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(DriverClassName);
        return dataSource;
    }
        @Bean
        public JdbcTemplate getJdbcTemplate() {
            return new JdbcTemplate(getDataSource());
        }
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    HibernateJpaVendorAdapter vendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    EntityManagerFactory entityManagerFactory(HibernateJpaVendorAdapter
                                                      hibernateJpaVendorAdapter,
                                              DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emf =
               new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.den");
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return  tm;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}







