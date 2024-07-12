package com.devyurakim.devschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.devyurakim.devschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=====" + joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed(); //marks the point where the actual method being intercepted is executed
        /*Object로 joinPoint.proceed()의 결과를 받는 이유
        * joinPoint.proceed()는 실제 method가 실행되는 것인데, 만일 그것이 return 하는 객체가 있다면
        * 그것을 Object type(가장 general한 type이므로)으로 받아서 다시 return 해주어야
        * intercepted method가 제대로 작동하게 되는 효과를 갖는다
        * In Aspect-Oriented Programming (AOP), capturing joinPoint.proceed() result as an Object is crucial because it allows the intercepted method to execute as intended.
        * By returning this result within the aspect method, we ensure that the application maintains its expected behavior, seamlessly integrating any additional functionality provided by the aspect.
        * This approach ensures that the method's original return value, regardless of its type, is preserved and passed back to the calling code, thus ensuring the overall functionality remains intact.*/
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("===== Time took to execute " + joinPoint.getSignature().toString() + " method is : "+timeElapsed);
        log.info("=====" + joinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.devyurakim.devschool.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("*****" + joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }


}
