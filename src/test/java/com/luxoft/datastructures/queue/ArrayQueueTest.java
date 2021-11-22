package com.luxoft.datastructures.queue;

import com.luxoft.datastructures.stack.ArrayStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    ArrayQueue arrayQueue;

    @BeforeEach
    public void before() {
        arrayQueue = new ArrayQueue();
    }

    @Test
    public void testEnqueueAndDequeueWorkCorrectlyAndChangeSize() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        assertEquals(2, arrayQueue.size());
        assertEquals("A", arrayQueue.dequeue());
        assertEquals("B", arrayQueue.dequeue());
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testEnqueueOverInitialCapacityAndDequeueWorkCorrectlyAndChangeSize() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");

        assertEquals(3, arrayQueue.size());
        assertEquals("A", arrayQueue.dequeue());
        assertEquals("B", arrayQueue.dequeue());
        assertEquals("C", arrayQueue.dequeue());

        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testEnqueueAndPeek() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        assertEquals(2, arrayQueue.size());
        assertEquals("A", arrayQueue.peek());
        assertEquals("A", arrayQueue.peek());
        assertEquals(2, arrayQueue.size());
    }

    @Test
    public void testIsEmptyReturnTrueOnNewQueue() {
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyReturnFalseOnQueueWithData() {
        arrayQueue.enqueue("A");

        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyReturnTrueOnQueueAfterClear() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        arrayQueue.clear();

        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testContainsReturnTrue() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        assertTrue(arrayQueue.contains(new String("A")));
    }

    @Test
    public void testContainsReturnFalse() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");

        assertFalse(arrayQueue.contains("C"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyQueue() {
        assertFalse(arrayQueue.contains("C"));
    }

    @Test
    public void testThrowIllegalStateExceptionWhenDequeueOnEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            arrayQueue.dequeue();
        });
    }

    @Test
    public void testQueueToPrint() {
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");

        System.out.println(arrayQueue.toString());
    }
}
