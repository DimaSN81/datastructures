package com.luxoft.datastructures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    ArrayStack arrayStack;
    @BeforeEach
    public void before() {
        arrayStack = new ArrayStack();
    }

    @Test
    public void testPushAndPopWorkCorrectlyAndChangeSize() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertEquals(2, arrayStack.size());
        assertEquals("B", arrayStack.pop());
        assertEquals("A", arrayStack.pop());
        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());
    }

    @Test
    public void testPushOverInitialCapacityAndPopWorkCorrectlyAndChangeSize() {
        arrayStack.push("A");
        arrayStack.push("B");
        arrayStack.push("C");

        assertEquals(3, arrayStack.size());
        assertEquals("C", arrayStack.pop());
        assertEquals("B", arrayStack.pop());
        assertEquals("A", arrayStack.pop());

        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());
    }

    @Test
    public void testPushAndPeek() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertEquals(2, arrayStack.size());
        assertEquals("B", arrayStack.peek());
        assertEquals("B", arrayStack.peek());
        assertEquals(2, arrayStack.size());
    }

    @Test
    public void testIsEmptyReturnTrueOnNewStack() {
        assertTrue(arrayStack.isEmpty());
    }

    @Test
    public void testIsEmptyReturnFalseOnStackWithData() {
        arrayStack.push("A");

        assertFalse(arrayStack.isEmpty());
    }

    @Test
    public void testIsEmptyReturnTrueOnStackAfterClear() {
        arrayStack.push("A");
        arrayStack.push("B");

        arrayStack.clear();

        assertTrue(arrayStack.isEmpty());
    }

    @Test
    public void testContainsReturnTrue() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertTrue(arrayStack.contains(new String("A")));
    }

    @Test
    public void testContainsReturnFalse() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertFalse(arrayStack.contains("C"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyStack() {
        assertFalse(arrayStack.contains("C"));
    }

    @Test
    public void testThrowIllegalStateExceptionWhenPopOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayStack.pop();
        });
    }
}