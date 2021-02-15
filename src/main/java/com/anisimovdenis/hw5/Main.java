package com.anisimovdenis.hw5;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Dao<Student, Long> studentDao = new Dao<>(SessionFactoryBuilder.build(), Student.class);

        List<Student> students = Stream.generate(() -> new Student("Bob" + ThreadLocalRandom.current().nextInt(1000), ThreadLocalRandom.current().nextInt(1, 6)))
                .limit(100)
                .collect(Collectors.toList());

        studentDao.saveAll(students);

        List<Student> all = studentDao.getAll();
        for (Student student : all) {
            System.out.println(student);
        }

        Student student = studentDao.getById(1L).get();
        System.out.println(student);

        student.setMark(100);
        studentDao.update(student);

        Student student2 = studentDao.getById(1L).get();
        System.out.println(student2);

        studentDao.deleteAll();

        SessionFactoryBuilder.closeSessionFactory();
    }
}
