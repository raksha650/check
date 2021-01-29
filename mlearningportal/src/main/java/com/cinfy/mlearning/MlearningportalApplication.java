package com.cinfy.mlearning;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.cinfy.mlearning.JpaConfiguration;
import com.cinfy.mlearning.MlearningportalApplication;

@EnableAutoConfiguration
@EnableCaching
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.cinfy.mlearning.api","com.cinfy.mlearning.controller","com.cinfy.mlearning.utils","com.cinfy.mlearning.model.mapper"})
@EnableJpaRepositories({"com.cinfy.mlearning.model.repositories"})
@org.springframework.boot.autoconfigure.domain.EntityScan(basePackages={"com.cinfy.mlearning.model"})
@EnableTransactionManagement
@EnableJpaAuditing
public class MlearningportalApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(MlearningportalApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MlearningportalApplication.class);
    }
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.ENGLISH);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/files/**")
	      .addResourceLocations("file:///D:/MLearning/");
	 }
	
	/*@Bean
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(new String[] { "classpath:validation","classpath:messages" });
        //messageSource.setBasename("classpath:validation");
        return messageSource;
    }*/
	 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
