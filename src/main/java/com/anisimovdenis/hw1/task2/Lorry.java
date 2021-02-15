package com.anisimovdenis.hw1.task2;

public class Lorry extends Car implements Stopable {

    public Lorry(Engine engine, String color) {
        super(engine, color);
    }

    public void move(){
        System.out.println("Car is moving");
    }

    public void stop(){
        System.out.println("Car is stop");
    }

    @Override
    public void open() {

    }
}
