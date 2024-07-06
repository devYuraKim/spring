package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class VehicleStartCheckAspect {

    private Logger logger = Logger.getLogger(VehicleStartCheckAspect.class.getName());

    /** args( a, b, ..): 실제 aspect method가 받게 되는 arguments 나열
     * */
    @Before("execution(* com.example.services.*.*(..)) && args(vehicleStarted,..)")
    /** ProceedingJoinPoint: method가 실행한 후에 이를 manipulate 할 수 있는 경우에 사용
     *  JoinPoint: 아직 method가 실행되지 않아 이를 control 불가한 경우에 사용
     * */
    public void checkVehicleStarted(JoinPoint joinPoint, boolean vehicleStarted) throws Throwable {
        if(!vehicleStarted){
            throw new RuntimeException("Vehicle not started");
        }
    }
}