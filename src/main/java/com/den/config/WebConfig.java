package com.den.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.den.controller")
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public ViewResolver getViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setOrder(1);
        freeMarkerViewResolver.setExposeContextBeansAsAttributes(true);
        freeMarkerViewResolver.setExposeSessionAttributes(true);
        freeMarkerViewResolver.setExposeRequestAttributes(true);
        return freeMarkerViewResolver;
    }

    @Bean
    FreeMarkerConfigurer getFreemarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("/","/WEB-INF/views");
        return freeMarkerConfigurer;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("/WEB-INF/upload/");
        registry.addResourceHandler("/CSS/**")
                .addResourceLocations("/WEB-INF/CSS/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:");
        registry.addResourceHandler("/video/**")
                .addResourceLocations("/WEB-INF/video/");
        registry.addResourceHandler("/player/**")
                .addResourceLocations("/WEB-INF/player/");



    }


}
