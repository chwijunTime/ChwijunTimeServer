package com.gsm.chwijuntime.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MemoryCheckAop {

    @Pointcut("@annotation(com.gsm.chwijuntime.aop.MemoryCheck)")
    public void MemoryCheck() {}

    @Before("MemoryCheck()")
    public void before(JoinPoint joinPoint) {
        log.info("=====================AspectJ TEST  : Before Logging Start=====================");
        log.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    @AfterReturning(pointcut = "MemoryCheck()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        log.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        log.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    @Around("MemoryCheck()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=====================AspectJ TEST  : Around Logging Start=====================");
        try {
            Object result = joinPoint.proceed();
            log.info("=====================AspectJ TEST  : Around Logging END=====================");
            return result;
        } catch (Exception e) {
            log.error("=====================AspectJ Around Exception=====================");
            log.error(e.toString());
            return null;
        }
    }
}
