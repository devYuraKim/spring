package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle volkswagen = new Vehicle();
        volkswagen.setName("Volkswagen");
        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;

        Supplier<Vehicle> audiSupplier = () -> {
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        };

        //조건에 따라서 bean을 생성
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println("randomNumber = " + randomNumber);

        if(randomNumber%2==0){
            context.registerBean("volkswagen", Vehicle.class, volkswagenSupplier);
        }else{
            context.registerBean("audi", Vehicle.class, audiSupplier);
        }

        //해당 bean이 생성되지 않은 경우 이 예외를 처리할 코드
        Vehicle volkswagenVehicle = null;
        Vehicle audiVehicle = null;
        try{
            volkswagenVehicle = context.getBean("volkswagen", Vehicle.class);
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("no volkswagen bean");
        }
        try{
            audiVehicle = context.getBean("audi", Vehicle.class);
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("no audi bean");
        }

        //해당 bean이 생성된 경우 bean의 이름 확인
        if(volkswagenVehicle!=null){
            System.out.println(volkswagenVehicle.getName());
        }else{
            System.out.println(audiVehicle.getName());
        }

    }
}