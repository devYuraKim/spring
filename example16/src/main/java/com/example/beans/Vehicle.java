package com.example.beans;

import com.example.services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("vehicleBean")
public class Vehicle {

    private String name="Honda";
    private final VehicleServices vehicleServices;

    //Singleton bean에 Prototype bean을 주입하면 후자는 instantiate한 해당 bean으로 고정된다
    //effectively making the prototype bean behave like a singleton within the singleton bean
    //create a new instance of the bean every time it is requested가 안 되어서 문제가 된다
    @Autowired
    public Vehicle(VehicleServices vehicleServices){
        this.vehicleServices = vehicleServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleServices getVehicleServices() {
        return vehicleServices;
    }

    public void printHello(){
        System.out.println(
                "Printing Hello from Component Vehicle Bean");
    }

    @Override
    public String toString(){
        return "Vehicle name is - "+name;
    }
}