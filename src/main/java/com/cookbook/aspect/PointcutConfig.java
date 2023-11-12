package com.cookbook.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointcutConfig {
    @Pointcut("execution(* com.cookbook.controller.*.*(..))")
    public void apiLayerPointcut() {
    }

    @Pointcut("execution(* com.cookbook.service.*.*(..))")
    public void serviceLayerPointcut() {
    }

    @Pointcut("execution(* com.cookbook.controller.*.*(..)) || execution(* com.cookbook.service.*.*(..))")
    public void serviceControllerLayerPointcut() {
    }

    @Pointcut("@annotation(com.cookbook.aspect.MeasureTime)")
    public void measureTimeAnnotationPointcut() {
    }
}
