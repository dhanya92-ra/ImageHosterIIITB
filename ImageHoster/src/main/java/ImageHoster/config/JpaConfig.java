package ImageHoster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/*
    @Configuration annotation indicates that a class declares one or more @Bean methods
    and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime
 */
@Configuration
public class JpaConfig {

    //@Bean annotation indicates that the annotated method produces a bean to be managed by the Spring container
    @Bean
    //This method is used to set classpath variable for a xml file
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        emfb.afterPropertiesSet();
        return emfb.getObject();
    }

    //@Bean annotation indicates that the annotated method produces a bean to be managed by the Spring container
    @Bean
    //This method is used to set driver name,url name,db username,db password
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/imageHoster");
        ds.setUsername("postgres");
        ds.setPassword("password");
        return ds;
    }
}

