package com.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();

        /***** Tyres나 Speakers 바꾸려면 vehicle class에서 코드를 빠꿔야 함*/
        //wihout IoC
        System.out.println("vehicle1");
        vehicle.makeVehicle1();
        //Factory Pattern
        System.out.println("vehicle2");
        vehicle.makeVehicle2();

        /** Spring Framework IoC
        ***** Tyres나 Speakers를 바꾸려고 할 때, SpringVehicles에서 바꿀 코드가 전혀 없음
        1. Config에 Context에서 Bean으로 관리가 필요한 대상 범위 지정: @Configuration, @ComponentScan(basePackages = {"com.example.main"})
        2. @Component 추가 class: SpringVehicle, *Speakers(하나는 @Primary), *Tyres(하나는 @Primary)
        3. Main에서 Spring Context 생성, getBean으로 SpringVehicle class intantiate
        */
        var context = new AnnotationConfigApplicationContext(Config.class);
        SpringVehicle springVehicle = context.getBean(SpringVehicle.class);
        System.out.println("vehicle3");
        springVehicle.makeVehicle3();
    }
}