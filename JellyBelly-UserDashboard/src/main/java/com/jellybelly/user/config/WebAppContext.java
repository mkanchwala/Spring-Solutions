package com.jellybelly.user.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jellybelly.user.beans.Config;

/**
 * @author mkanchwala
 */
@Configuration
@ComponentScan(basePackages = {
        "com.jellybelly.user.webmvc"
})
@EnableWebMvc
public class WebAppContext extends WebMvcConfigurerAdapter {

	@Resource
    private Environment env;

    protected static final String PROPERTY_NAME_RESOURCE_LOADER = "resource.loader";
    protected static final String PROPERTY_NAME_RESOURCE_LOADER_CACHE = "file.resource.loader.cache";
    protected static final String PROPERTY_NAME_RESOURCE_LOADER_CLASS = "file.resource.loader.class";
    protected static final String PROPERTY_NAME_RESOURCE_LOADER_PATH = "file.resource.loader.path";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties exceptionMappings = new Properties();
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
        exceptionResolver.setExceptionMappings(exceptionMappings);
        Properties statusCodes = new Properties();
        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");
        exceptionResolver.setStatusCodes(statusCodes);
        return exceptionResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(Config.VIEW_RESOLVER_PREFIX.parameterName);
        viewResolver.setSuffix(Config.VIEW_RESOLVER_SUFFIX.parameterName);
        return viewResolver;
    }
    
    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException{
    	VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
        Properties props = new Properties();
        props.put(PROPERTY_NAME_RESOURCE_LOADER, env.getProperty(PROPERTY_NAME_RESOURCE_LOADER));
        props.put(PROPERTY_NAME_RESOURCE_LOADER_CACHE, env.getProperty(PROPERTY_NAME_RESOURCE_LOADER_CACHE));
        props.put(PROPERTY_NAME_RESOURCE_LOADER_CLASS, env.getProperty(PROPERTY_NAME_RESOURCE_LOADER_CLASS));
        props.put(PROPERTY_NAME_RESOURCE_LOADER_PATH, env.getProperty(PROPERTY_NAME_RESOURCE_LOADER_PATH));
        factory.setVelocityProperties(props);
        return factory.createVelocityEngine();      
    }
}
