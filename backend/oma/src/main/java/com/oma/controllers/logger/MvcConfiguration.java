package com.oma.controllers.logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    private Set<HandlerInterceptor>interceptors;

    public MvcConfiguration(Set<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
    public void addInterceptors(InterceptorRegistry registry){
        interceptors.forEach(registry::addInterceptor);
        registry.addInterceptor(new LoggerInterceptor());
    }
}
