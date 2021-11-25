package com.luxoft.datastructures.list;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
        head = tail = null;
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        Node newNode = new Node(value);
        checkIndexOnAdd(index);
        IteratorList newIterator = new IteratorList();
        if (size == 0) {
            head = tail = newNode;
        } else if ((int) (size / 2) - 1 >= index) {
            int counter = 0;
            if (index == counter) {
                newNode.prev = null;
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                while (newIterator.hasNext()) {
                    newIterator.next();
                    counter++;
                    if (counter == index) {
                        newNode.prev = newIterator.newHead.prev;
                        newNode.next = newIterator.newHead;
                        newIterator.newHead.prev = newNode;
                        break;
                    }
                }
            }
        } else {
            int counter = size;
            if (size == index) {
                tail.next = newNode;
                newNode.prev = tail;
                newNode.next = null;
                tail = newNode;
            } else {
                Node currentTail = tail;
                while (counter > 0) {
                    counter--;
                    if (counter == index) {
                        newNode.prev = currentTail.prev;
                        newNode.next = currentTail;
                        currentTail.prev.next = newNode;
                        currentTail.prev = newNode;
                        break;
                    }
                    currentTail = currentTail.prev;
                }
            }
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object oldValue = null;
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
    public Object get(int index) {
        Object result = null;
        checkIndex(index);
        IteratorList newIterator = new IteratorList();
        if (index == 0) {
            result = head.value;
        } else if (index == size - 1) {
            result = tail.value;
        } else if ((int) (size / 2) - 1 >= index) {
            int counter = 0;
            while (newIterator.hasNext()) {
                newIterator.next();
                counter++;
                if (counter == index) {
                    result = newIterator.newHead.value;
                    break;
                }
            }
        } else {
            int counter = size - 1;
            Node currentTail = tail;
            while (counter >= 0) {
                currentTail = currentTail.prev;
                counter--;
                if (counter == index) {
                    result = currentTail.value;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Object set(Object value, int index) {
        checkIndex(index);
        Object oldValue = null;
        IteratorList newIterator = new IteratorList();
        if (index == 0) {
            oldValue = head.value;
            head.value = value;
        } else if (index == size - 1) {
            oldValue = tail.value;
            tail.value = value;
        } else if ((int) (size / 2) - 1 >= index) {
            int counter = 0;
            while (newIterator.hasNext()) {
                newIterator.next();
                counter++;
                if (counter == index) {
                    oldValue = newIterator.newHead.value;
                    newIterator.newHead.value = value;
                    break;
                }
            }
        } else {
            int counter = size - 1;
            Node currentTail = tail;
            while (counter >= 0) {
                currentTail = currentTail.prev;
                counter--;
                if (counter == index) {
                    oldValue = currentTail.value;
                    currentTail.value = value;
                    break;
                }
            }
        }
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
        if (head.equals(value)) {
            result = true;
        } else {
            IteratorList iteratorList = new IteratorList();
            while (iteratorList.hasNext()) {
                iteratorList.next();
                if (iteratorList.newHead.value.equals(value)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object value) {
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
    public int lastIndexOf(Object value) {
        checkOnNull(value);
        int result = -1;
        if (size != 0) {
            Node currentTail = tail;
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

    private class IteratorList implements Iterator {
        private int index = -1;
        private Node newHead = head;
        private Node newTail = tail;

        @Override
        public boolean hasNext() {
            if (index == -1) {
                index = 0;
            }
            return index != size - 1;
        }

        @Override
        public Object next() {
            newHead = newHead.next;
            index++;
            return newHead.value;
        }

        @Override
        public void remove() {
            Object oldValue = newHead.value;
            if (newHead.prev == null & newHead.next == null) {
                head = tail = null;
            } else if (newHead.prev == null & newHead.next != null) {
                newHead.next.prev = null;
                head = newHead.next;
            } else if (newHead.prev != null & newHead.next == null) {
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

    class Node {
        Object value;
        Node next;
        Node prev;

        public Node(Object value) {
            this.value = value;
        }

    }
}
