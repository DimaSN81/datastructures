package com.luxoft.datastructures.list;

public class LinkedList implements List{
    private Node node;
    private int size;

    public LinkedList() {
        array = new Object[10];
    }

    @Override
    public void add(Object value) {

    }

    @Override
    public void add(Object value, int index) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object value) {
        return 0;
    }

    class Node{
        Object value;
        Node next;
        Node prev;

        public Node(Object value){
            this.value = value;
        }

    }
}
