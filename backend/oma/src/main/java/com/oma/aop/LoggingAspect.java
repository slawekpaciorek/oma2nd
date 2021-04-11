package com.oma.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class LoggingAspect {
    /*
    @Pointcut("execution(* com.oma..*(..))")
    private void anyPublicMethod(){
    }

    @After("anyPublicMethod()")
    public void afterAnyPublicMethod(JoinPoint joinPoint){
        log.info(" :: after :: " + joinPoint.getSignature().getName());
    }
    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod(JoinPoint joinPoint){
        log.info(" :: before :: " + joinPoint.getSignature().getName());
    }*/
}
