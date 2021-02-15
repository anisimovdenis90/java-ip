package com.anisimovdenis.hw1.task1;

public class Main {

    public static void main(String[] args) {
        Person person = PersonBuilder.build()
                .setFirstName("Bob")
                .setLastName("Kane")
                .setMiddleName("Johnson")
                .setCountry("Russia")
                .setAddress("Moscow")
                .setAge(20)
                .setPhone("03")
                .setGender("Male")
                .getPerson();
        System.out.println(person);
    }
}
