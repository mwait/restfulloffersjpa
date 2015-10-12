package pl.offersjpa;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.OpenJpaDialect;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableAutoConfiguration
@ComponentScan("pl.offersjpa")
public class App extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(applicationClass);
	    }

	    private static Class<App> applicationClass = App.class;
	
	    @Bean
	    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
	        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
	        bean.setJndiName("java:comp/env/jdbc/spring");
	        bean.setProxyInterface(DataSource.class);
	        bean.setLookupOnStartup(false);
	        bean.afterPropertiesSet();
	        return (DataSource)bean.getObject();
	    }
	    
	    @Bean
	    OpenJpaVendorAdapter jpaAdapter() {
	    	OpenJpaVendorAdapter openJpaVendorAdapter = new OpenJpaVendorAdapter();
	    openJpaVendorAdapter.setDatabasePlatform("org.apache.openjpa.jdbc.sql.MySQLDictionary");
	    //openJpaVendorAdapter.setDatabase("MYSQL");
	    return openJpaVendorAdapter;
	    }
	    
	    @Bean
	    PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor () {
	    	return new PersistenceAnnotationBeanPostProcessor();
	    }
	    
	    @Bean
	    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, OpenJpaVendorAdapter jpaAdapter) {
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource);
	        entityManagerFactoryBean.setJpaVendorAdapter(new OpenJpaVendorAdapter());
	       entityManagerFactoryBean.setPersistenceUnitName("restfulloffersjpa");
	       entityManagerFactoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
	      entityManagerFactoryBean.setJpaVendorAdapter(jpaAdapter);
	        return entityManagerFactoryBean;
	    }
	    
	    @Bean
	    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
	        final JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(emf);
	        try {
				transactionManager.setDataSource(jndiDataSource());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        transactionManager.setJpaDialect(new OpenJpaDialect());
	    //    transactionManager.setJpaDialect(jpaDialect());
	        return transactionManager;
	    }
}
