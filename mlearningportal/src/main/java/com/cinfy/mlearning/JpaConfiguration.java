package com.cinfy.mlearning;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;
 
@Configuration
@EnableJpaRepositories(basePackages = "com.cinfy.mlearning.model.repositories",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {
 
    @Autowired
    private Environment environmentMlearning;
 
    @Value("${datasource.maxPoolSize}")
    private int maxPoolSizeMlearning;
 
    /*
     * Populate SpringBoot DataSourceProperties object directly from application.yml 
     * based on prefix.Thanks to .yml, Hierachical data is mapped out of the box with matching-name
     * properties of DataSourceProperties object].
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }
 
    /*
     * Configure HikariCP pooled DataSource.
     */
    @Bean
    public DataSource dataSourceMp() {
        DataSourceProperties dataSourcePropertiesMp = dataSourceProperties();
            HikariDataSource dataSourceMp = (HikariDataSource) DataSourceBuilder
                    .create(dataSourcePropertiesMp.getClassLoader())
                    .driverClassName(dataSourcePropertiesMp.getDriverClassName())
                    .url(dataSourcePropertiesMp.getUrl())
                    .username(dataSourcePropertiesMp.getUsername())
                    .password(dataSourcePropertiesMp.getPassword())
                    .type(HikariDataSource.class)
                    .build();
            dataSourceMp.setMaximumPoolSize(maxPoolSizeMlearning);            
            return dataSourceMp;
    }
 
    /*
     * Entity Manager Factory setup.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBeanMp = new LocalContainerEntityManagerFactoryBean();
        factoryBeanMp.setDataSource(dataSourceMp());
        factoryBeanMp.setPackagesToScan(new String[] { "com.cinfy.mlearning.model" });
        factoryBeanMp.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBeanMp.setJpaProperties(jpaProperties());
        return factoryBeanMp;
    }
 
    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapterMp = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapterMp;
    }
 
    /*
     * Here you can specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties propertiesMp = new Properties();
        propertiesMp.put("hibernate.dialect", environmentMlearning.getRequiredProperty("datasource.hibernate.dialect"));
        propertiesMp.put("hibernate.hbm2ddl.auto", environmentMlearning.getRequiredProperty("datasource.hibernate.hbm2ddl.method"));
        propertiesMp.put("hibernate.show_sql", environmentMlearning.getRequiredProperty("datasource.hibernate.show_sql"));
        propertiesMp.put("hibernate.format_sql", environmentMlearning.getRequiredProperty("datasource.hibernate.format_sql"));
        propertiesMp.put("hibernate.hbm2ddl.auto", environmentMlearning.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        if(StringUtils.isNotEmpty(environmentMlearning.getRequiredProperty("datasource.defaultSchema"))){
        	propertiesMp.put("hibernate.default_schema", environmentMlearning.getRequiredProperty("datasource.defaultSchema"));
        }
        return propertiesMp;
    }
 
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManagerMp = new JpaTransactionManager();
        txManagerMp.setEntityManagerFactory(emf);
        return txManagerMp;
    }
    
}