package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import com.example.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        System.out.println("SINGLETON");
        Vehicle vehicle1 = context.getBean(Vehicle.class);
        Vehicle vehicle2 = context.getBean(Vehicle.class);
        System.out.println("Hashcode of the object vehicle1 : " +vehicle1.hashCode());
        System.out.println("Hashcode of the object vehicle2 : " +vehicle2.hashCode());
        if(vehicle1==vehicle2){
            System.out.println("VehicleServices bean is a singleton scoped bean");
        }else{
            System.out.println("VehicleServices bean is a prototype scoped bean");
        }
        System.out.println("PROTOTYPE");
        VehicleServices vehicleServices1 = context.getBean(VehicleServices.class);
        VehicleServices vehicleServices2 = context.getBean(VehicleServices.class);
        System.out.println("Hashcode of the object vehicleServices1 : " +vehicleServices1.hashCode());
        System.out.println("Hashcode of the object vehicleServices2 : " +vehicleServices2.hashCode());
        if(vehicleServices1==vehicleServices2){
            System.out.println("VehicleServices bean is a singleton scoped bean");
        }else{
            System.out.println("VehicleServices bean is a prototype scoped bean");
        }
    }
}