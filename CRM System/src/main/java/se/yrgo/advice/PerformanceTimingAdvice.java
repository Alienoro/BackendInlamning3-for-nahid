package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceTimingAdvice {

    @Around("execution(* se.yrgo.services..*(..)) || execution(* se.yrgo.dataaccess..*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;

            System.out.println("Time taken for the method " + joinPoint.getSignature().getName()
                    + " took " + timeTaken / 1_000_000 + " ms");
        }
    }
}