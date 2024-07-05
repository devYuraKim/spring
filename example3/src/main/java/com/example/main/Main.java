package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var veh1 = context.getBean("audi", Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: "+veh1.getName());

        var veh2 = context.getBean("honda", Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: "+veh2.getName());

        var veh3 = context.getBean("ferrari", Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: "+veh3.getName());

    }
}