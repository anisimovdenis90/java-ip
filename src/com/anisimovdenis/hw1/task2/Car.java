package com.anisimovdenis.hw1.task2;

public abstract class Car implements Moveable {

    private Engine engine;
    private String color;
    private String name;

    public Car(Engine engine, String color) {
        this.engine = engine;
        this.color = color;
    }

    public void start() {
        System.out.println("Car starting");
    }

    protected abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
