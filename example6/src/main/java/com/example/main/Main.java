package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var veh = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: "+veh.getName());
        veh.printHello();
        context.close(); //@PreDestroy 보기 위해서 일부러 context 닫는 method 명시
    }
}