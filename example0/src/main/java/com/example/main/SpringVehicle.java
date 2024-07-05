package com.example.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tyres나 Speakers를 바꾸려고 할 때, SpringVehicles에서 바꿀 코드가 전혀 없음
@Component
public class SpringVehicle {

    @Autowired
    private Speakers speakers;
    @Autowired
    private Tyres tyres;

    public void makeVehicle3(){
        System.out.println(speakers.makeSound());
        System.out.println(tyres.rotate());
    }
}
