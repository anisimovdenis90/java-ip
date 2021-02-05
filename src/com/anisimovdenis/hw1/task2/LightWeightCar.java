package com.anisimovdenis.hw1.task2;

public class LightWeightCar extends Car {

    public LightWeightCar(Engine engine, String color) {
        super(engine, color);
    }

    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

}
