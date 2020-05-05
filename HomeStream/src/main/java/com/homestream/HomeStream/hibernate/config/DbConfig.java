package com.homestream.HomeStream.hibernate.config;

import org.springframework.context.annotation.Configuration;

/**
 * Some further configuration for Hibernate
 * @author baeldung
 * @see <a href="https://github.com/eugenp/tutorials/blob/51352c470f98c417bb4ecb9b3150eb9f167bc361/spring-data-rest/src/main/java/com/baeldung/config/DbConfig.java">Github ref</a>
 */
@Configuration
public class DbConfig {



    /*
    private static final String[] packagesToScan = {
            "com.homestream.HomeStream.dao",
            "com.homestream.HomeStream.entity",
            "com.homestream.HomeStream.vo"
    };

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:./res/db.sqlite");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();


        //Setting further hibernaste properties
        Properties hibernateProperties = new Properties();

        //set session factory
        lsfb.setDataSource(dataSource());
        lsfb.setPackagesToScan("com.homestream.HomeStream.dao", "com.homestream.HomeStream.entity", "com.homestream.HomeStream.vo");
        lsfb.setHibernateProperties(hibernateProperties);
        return lsfb;

    }

    //TODO: Does this actually work?
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setDataSource(dataSource());
        em.setPackagesToScan(packagesToScan);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    Properties additionalProperties(){
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", "com.homestream.HomeStream.hibernate.sqlite.SQLiteDialect");

        return p;
    }
    */
}
