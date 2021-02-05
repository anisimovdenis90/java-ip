package com.anisimovdenis.hw1.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>(Arrays.asList(new Rectangle(), new Circle(), new Triangle()));
        shapes.forEach(System.out::println);
    }
}
