package com.codingsrv.AopApplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspectV2 {

    // first we are declaring a pointcut
    @Pointcut("execution(* com.codingsrv.AopApplication.services.impl.*.*(..))")
    public void allServiceMethodsPointcut(){

    }

    @Before("allServiceMethodsPointcut()")
    public void beforeServiceMethodsCalls(JoinPoint joinPoint){
        log.info("Before advice method calls: {}",joinPoint.getSignature());
    }

    @After("allServiceMethodsPointcut()")
    public void afterServiceMethodsCalls(JoinPoint joinPoint){
        log.info("After advice method calls: {}",joinPoint.getSignature());
    }

    @AfterReturning(value = "allServiceMethodsPointcut()",returning = "returnedObj")
    public void afterReturningServiceMethodsCalls(Object returnedObj){
        log.info("After returning advice method calls");
        log.info("After returning returned value:{}",returnedObj);
    }

    @AfterThrowing("allServiceMethodsPointcut()")
    public void afterThrowingServiceMethodsCalls(JoinPoint joinPoint){
        log.info("After throwing advice method calls: {}",joinPoint.getSignature());
    }

    @Around("allServiceMethodsPointcut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnedValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Long diff = endTime - startTime;
        log.info("Time taken for {} is {}",proceedingJoinPoint.getSignature(),diff);
        return returnedValue;
    }










}
