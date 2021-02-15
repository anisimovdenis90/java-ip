package com.anisimovdenis.hw2;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.data = (T[]) new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(T value) {
        checkAndGrow();
        data[size++] = value;
        return true;
    }

    @Override
    public void add(int index, T value) {
        checkIndexRange(index);
        checkAndGrow();
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return data[index];
    }

    @Override
    public T set(int index, T value) {
        checkIndexRange(index);
        T oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T removedValue = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[--size] = null;
        return removedValue;
    }

    @Override
    public boolean remove(T value) {
        int index = indexOf(value);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public int indexOf(T value) {
        if (data == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    private void checkIndexRange(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, newCapacity());
        }
    }

    private int newCapacity() {
        return (size << 1) + 1;
    }

    @Override
    public String toString() {
        return "MyArrayList{" + Arrays.toString(Arrays.copyOf(data, size)) + "}";
    }
}
