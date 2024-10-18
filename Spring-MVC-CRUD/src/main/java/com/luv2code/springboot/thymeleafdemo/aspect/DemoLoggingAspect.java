package com.luv2code.springboot.thymeleafdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class DemoLoggingAspect {

    // setup Logger
    private Logger log= Logger.getLogger(getClass().getName());
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow(){}


    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        // display method we are calling
        String method=joinPoint.getSignature().toShortString();
        log.info("=====>> in @Before: calling method: "+method);

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop and diplay args
        for(Object arg:args){
            log.info("args: " + arg);
        }

    }
}
