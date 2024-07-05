package com.example.beans;

public class Vehicle {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void printHello(){
        System.out.println("Hello from Component Vehicle");
    }
}
