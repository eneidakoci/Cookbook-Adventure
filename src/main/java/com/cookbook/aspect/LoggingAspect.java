package com.cookbook.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Before("com.cookbook.aspect.PointcutConfig.apiLayerPointcut()")
    public void beforeLogAspect(JoinPoint joinPoint) {
        logger.info("BeforeLogAspect - Entering method: " + joinPoint.getSignature() + " with endpoint: " + httpServletRequest.getRequestURI());
    }

    @AfterReturning("com.cookbook.aspect.PointcutConfig.apiLayerPointcut()")
    public void afterReturningAdvice(JoinPoint joinPoint) {
        logger.info("AfterReturningAdvice - Method call completed " + joinPoint.getSignature());
    }

    @After("com.cookbook.aspect.PointcutConfig.apiLayerPointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("AfterAdvice - Exiting method: " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "com.cookbook.aspect.PointcutConfig.serviceLayerPointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        try {
            logger.error("AfterThrowing - Exception in method: " + joinPoint.getSignature(), exception);
        } catch (Exception e) {
            logger.error("An error occurred during exception handling", e);
        }
    }

    @Around("com.cookbook.aspect.PointcutConfig.measureTimeAnnotationPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long timeBefore = System.currentTimeMillis();
        var execution = joinPoint.proceed();
        logger.info("AroundAdvice - Method call: " + joinPoint.getSignature() +" started executing.");
        long timeAfter = System.currentTimeMillis();
        logger.info("AroundAdvice - Method call: " + joinPoint.getSignature() +" ended executing.");
        long executionTime = timeAfter - timeBefore;

        logger.info("AroundAdvice - Method call: " + joinPoint.getSignature() + " took: " + executionTime + " millis to proceed.");

        return execution;
    }
}