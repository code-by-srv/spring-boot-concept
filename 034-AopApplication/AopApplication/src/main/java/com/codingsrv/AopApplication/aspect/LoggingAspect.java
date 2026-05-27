package com.codingsrv.AopApplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // kind of pointcut expression (to put my advice code)

    // execution kind pointcut
    @Before("execution(* beforeOrderPackage(..))") // for a specific method in any class
    //@Before("execution(* com.codingsrv.AopApplication.services.impl.ShipmentServiceImpl.beforeOrderPackage(..))") // for a specific method in a specific class
    //@Before("execution(* com.codingsrv.AopApplication.services.impl.*.beforeOrderPackage(..))") // for a specific method in any class inside impl package
    //@Before("execution(* com.codingsrv.AopApplication.services.impl.ShipmentServiceImpl.*(..))")  // for any method in a specific class
    //@Before("execution(* com.codingsrv.AopApplication.services.impl.*.*(..))")  // for any method inside impl package
    public void beforeShipmentServiceMethod(JoinPoint joinPoint){
        log.info("Before orderPackage called from LoggingAspect kind: {}",joinPoint.getKind());
        log.info("Before orderPackage called from LoggingAspect signature: {}",joinPoint.getSignature()); // contains info about class,return type and argument it takes
    }

    // within kind pointcut (to put advice code on any class/method/constructor inside a package)
    //@Before("within(com.codingsrv.AopApplication.services.impl.*)") // target all classes inside impl package.
    @Before("within(com.codingsrv.AopApplication..*)") // target everything inside the application
    public void beforeServiceImplCalls(){
        log.info("Service Impl calls");
    }


    // annotation kind pointcut (to put advice code on any method having a specific annotation)
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalAnnotationCalls(){ // to put advice code on any method having @Transactional annotation
        log.info("Before Transactional annotation calls");
    }


    // annotation kind pointcut (to put advice code on any method having a custom annotation i.e. @MyLogging)
    @Before("@annotation(com.codingsrv.AopApplication.aspect.MyLogging)")
    public void beforeCustomAnnotationCalls(){ // to put advice code on any method having @MyLogging annotation
        log.info("Before MyLogging annotation calls");
    }


    // pointcut declaration
    @Pointcut("@annotation(com.codingsrv.AopApplication.aspect.MyLogging) && within(com.codingsrv.AopApplication..*)")
    public void myLoggingPointcut(){
    }

    @After("myLoggingPointcut()")
    public void afterLoggingAndAopMethodPointcut(){ // to put advice code on any method having @MyLogging annotation
        log.info("after MyLogging annotation calls");
    }







}
