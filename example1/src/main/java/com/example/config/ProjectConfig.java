package com.example.config;

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    Vehicle vehicle(){
        Vehicle veh = new Vehicle();
        veh.setName("Audi 8");
        System.out.println(veh.getName());
        return veh;
    }

    @Bean
    String hello(){
        return "Hello World";
    }

    @Bean
    Integer number(){
        return 16;
    }


}
