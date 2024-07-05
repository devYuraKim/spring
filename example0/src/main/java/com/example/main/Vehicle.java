package com.example.main;

//Let's assume we are manufacturing a vehicle with only two components - Speakers and Tyres.
public class Vehicle {

    //Without IoC
    //this is the approach developers used to follow before IoC
    //Problem: Tightly coupled objects - when changes are needed, the whoel code needs to be chaged
    public void makeVehicle1(){
        SonySpeakers sonySpeakers = new SonySpeakers();
        System.out.println(sonySpeakers.makeSound());
        MichelinTyres michelinTyres = new MichelinTyres();
        System.out.println(michelinTyres.rotate());
    }

    //Factory pattern
    public void makeVehicle2(){
        SpeakerFactory speakerFactory = new SpeakerFactory();
        Speakers speakers = speakerFactory.getSpeaker("bose");
        System.out.println(speakers.makeSound());

        TyreFactory tyreFactory = new TyreFactory();
        Tyres tyres = tyreFactory.getTyre("bridgestone");
        System.out.println(tyres.rotate());
    }
}