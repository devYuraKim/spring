package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var veh = context.getBean(Vehicle.class);
        /**NoUniqueBeanDefinitionException:
         * No qualifying bean of type 'com.example.beans.Vehicle' available
         * : expected single matching bean but found 3: vehicle1,vehicle2,vehicle3*/

        var veh = context.getBean("vehicle2", Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: "+veh.getName());

    }
}