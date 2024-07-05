package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var veh = context.getBean(Vehicle.class);
        System.out.println("Vehical name from Spring Context is "+veh.getName());

        var hello = context.getBean(String.class);
        System.out.println(hello);

        var integer = context.getBean(Number.class);
        System.out.println(integer);

    }
}