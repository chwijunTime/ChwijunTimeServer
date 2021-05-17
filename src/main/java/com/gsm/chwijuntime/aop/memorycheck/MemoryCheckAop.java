package com.gsm.chwijuntime.aop.memorycheck;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MemoryCheckAop {

    @Pointcut("@annotation(com.gsm.chwijuntime.aop.memorycheck.MemoryCheck)")
    public void MemoryCheck() {}

    @Around("MemoryCheck()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            Runtime.getRuntime().gc();
            long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            log.info("used memory is " + used + " bytes");
            return result;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}
