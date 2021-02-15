package com.anisimovdenis.hw2;

public interface MyList<T> {

    boolean add(T value);

    void add(int index, T value);

    T get(int index);

    T set(int index, T value);

    T remove(int index);

    boolean remove(T value);

    int indexOf(T value);

    boolean isEmpty();

    boolean contains(T value);
}
