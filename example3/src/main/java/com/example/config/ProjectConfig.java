package com.example.config;

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean(name="audi")
    Vehicle vehicle1(){
        Vehicle veh = new Vehicle();
        veh.setName("Audi 8");
        return veh;
    }

    @Bean(name="honda")
    Vehicle vehicle2(){
        Vehicle veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }

    @Bean(name="ferrari")
    Vehicle vehicle3(){
        Vehicle veh = new Vehicle();
        veh.setName("Ferrari");
        return veh;
    }


}
