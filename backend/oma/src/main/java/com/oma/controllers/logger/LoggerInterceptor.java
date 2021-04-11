package com.oma.controllers.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
         logger.info(" [preHandler] " + request.getMethod() + " " + request.getRequestURI());
         return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info(" [postHandler]" + request.getMethod() + " " + request.getRequestURI() + " " + request.getPathInfo());
    }
}
