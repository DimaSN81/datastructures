package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedList() {
        head = tail = null;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        Node<T> newNode = new Node<T>(value);
        checkIndexOnAdd(index);
        Node<T> currentNode = getNode(index);
        if (head == null) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (currentNode == null) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            newNode.prev = currentNode.prev;
            newNode.next = currentNode;
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = null;
        IteratorList newIterator = new IteratorList();
        int counter = 0;
        if (index == counter) {
            oldValue = newIterator.newHead.value;
            newIterator.remove();
        } else {
            while (newIterator.hasNext()) {
                oldValue = newIterator.next();
                counter++;
                if (counter == index) {
                    newIterator.remove();
                    break;
                }
            }
        }
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        Node<T> currentNode = getNode(index);
        T oldValue = currentNode.value;
        currentNode.value = value;
        return oldValue;
    }

    @Override
    public void clear() {
        head = tail = null;
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
    public boolean contains(Object value) {
        boolean result = false;
        if (Objects.equals(head.value, value)) {
            result = true;
        } else {
            IteratorList iteratorList = new IteratorList();
            while (iteratorList.hasNext()) {
                iteratorList.next();
                if (Objects.equals(iteratorList.newHead.value, value)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int indexOf(T value) {
        checkOnNull(value);
        int result = -1;
        if (size != 0) {
            IteratorList iteratorList = new IteratorList();
            int counter = 0;
            if (Objects.equals(iteratorList.newHead.value, value)) {
                result = counter;
            } else {
                while (iteratorList.hasNext()) {
                    iteratorList.next();
                    counter++;
                    if (Objects.equals(iteratorList.newHead.value, value)) {
                        result = counter;
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(T value) {
        checkOnNull(value);
        int result = -1;
        if (size != 0) {
            Node<T> currentTail = tail;
            int counter = size - 1;
            while (counter >= 0) {
                if (Objects.equals(currentTail.value, value)) {
                    result = counter;
                    break;
                }
                currentTail = currentTail.prev;
                counter--;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");
        IteratorList iteratorList = new IteratorList();
        if (size != 0) {
            result.add((CharSequence) iteratorList.newHead.value);
            while (iteratorList.hasNext()) {
                iteratorList.next();
                result.add((CharSequence) iteratorList.newHead.value);
            }
        }
        return result.toString();
    }

    private Node<T> getNode(int index) {
        Node<T> result = null;
        IteratorList newIterator = new IteratorList();
        if (index == 0) {
            result = head;
        } else if (index == size - 1) {
            result = tail;
        } else if ((int) (size / 2) - 1 >= index) {
            int counter = 0;
            while (newIterator.hasNext()) {
                newIterator.next();
                counter++;
                if (counter == index) {
                    result = newIterator.newHead;
                    break;
                }
            }
        } else {
            int counter = size - 1;
            Node<T> currentTail = tail;
            while (counter >= 0) {
                currentTail = currentTail.prev;
                counter--;
                if (counter == index) {
                    result = currentTail;
                    break;
                }
            }
        }
        return result;
    }

    private class IteratorList implements Iterator<T> {
        private int index = -1;
        private Node<T> newHead = head;

        @Override
        public boolean hasNext() {
            if (index == -1) {
                index = 0;
            }
            return index != size - 1;
        }

        @Override
        public T next() {
            newHead = newHead.next;
            index++;
            return newHead.value;
        }

        @Override
        public void remove() {
            if (newHead.prev == null & newHead.next == null) {
                head = tail = null;
            } else if (newHead.prev == null & newHead.next != null) {
                newHead.next.prev = null;
                head = newHead.next;
            } else if (newHead.next == null) {
                newHead.prev.next = null;
                head = newHead.prev;
                tail = newHead.prev;
            } else {
                newHead.prev.next = newHead.next;
                newHead.next.prev = newHead.prev;
            }
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (size == 0 | index > size - 1 | index < 0) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    private void checkIndexOnAdd(int index) throws IndexOutOfBoundsException {
        if (index > size | index < 0) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    private void checkOnNull(Object value) {
        if (value == null) {
            throw new NullPointerException("");
        }
    }

    static class  Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

    }
}
