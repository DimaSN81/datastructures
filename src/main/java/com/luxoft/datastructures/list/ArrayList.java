package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[10];
    }

    public ArrayList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexOnAdd(index);
        ensureCapacity();
        for (int i = size - 1; i > index - 1; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        checkOnNull(value);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        checkOnNull(value);
        for (int i = size - 1; i > -1; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");

        for (int i = 0; i < size; i++) {
            result.add((CharSequence) array[i]);
        }
        return result.toString();
    }

    private void ensureCapacity() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5) + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    private void checkOnNull(T value) {
        if (value == null) {
            throw new NullPointerException("");
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index > size - 1 | index < 0) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    private void checkIndexOnAdd(int index) throws IndexOutOfBoundsException {
        if (index > size | index < 0) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private int counter = -1;
        private boolean isRemovable = false;

        @Override
        public boolean hasNext() {
            return counter < size - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            isRemovable = true;
            return array[++counter];
        }

        @Override
        public void remove() {
            if (!isRemovable) {
                throw new NullPointerException();
            }
            ArrayList.this.remove(counter);
            counter--;
            isRemovable = false;
        }
    }


}
