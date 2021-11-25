package com.luxoft.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private List<String> list;

    @BeforeEach
    public void before() {
        list = getList();
    }

    protected abstract List<String> getList();

    @Test
    public void testCheckForAddingToTopOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D", 0);

        assertEquals(4, list.size());
        assertEquals("A", list.get(1));
    }

    @Test
    public void testCheckForAddingToMiddleOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D", 1);

        assertEquals(4, list.size());
        assertEquals("B", list.get(2));
    }

    @Test
    public void testCheckForAddingToTheEndOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D", 3);

        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals("D", list.get(3));
    }

    @Test
    public void testCheckForAddingAbroadList() {
        list.add("A");
        list.add("B");
        list.add("C");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("D", 4);
        });
    }

    @Test
    public void testCheckDeletionFromTopOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.remove(0);

        assertEquals(3, list.size());
        assertEquals("B", list.get(0));
    }

    @Test

    public void testCheckDeletionFromMiddleOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.remove(2);

        assertEquals(3, list.size());
        assertEquals("D", list.get(2));
    }

    public void testCheckDeletionFromEndOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.remove(3);

        assertEquals(3, list.size());
        assertEquals("C", list.get(2));
    }

    @Test
    public void testCheckDeletionBeyondTheLimits() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });
    }

    @Test
    public void testCheckReceiveValueOfIndexFromTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals("D", list.get(3));
    }

    @Test
    public void testCheckReceiveValueOfIndexOutsideTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    public void testCheckForCorrectlyReplacingTheListValueOfTheListIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals("B", list.set("F", 1));
        assertEquals("F", list.get(1));
    }

    @Test
    public void testCheckForCorrectlyСleaningTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertFalse(list.isEmpty());

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testCheckForCorrectDefinitionOfTheListOfList() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testCheckingTheDefinitionIsEmptyList() {
        assertTrue(list.isEmpty());

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertFalse(list.isEmpty());
    }

    @Test
    public void testCheckingTheSearchValueInTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertTrue(list.contains("B"));
        assertTrue(list.contains("D"));
        assertFalse(list.contains("F"));
    }

    @Test
    public void testCheckingSearchValueFromBeginningOfListAndReceivingItsIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(2, list.indexOf("C"));
        assertEquals(0, list.indexOf("A"));

        Assertions.assertThrows(NullPointerException.class, () -> {
            list.indexOf(null);
        });
    }

    @Test
    public void testCheckingSearchValueFromEndOfListAndReceivingItsIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(1, list.lastIndexOf("B"));
        assertEquals(3, list.lastIndexOf("D"));

        Assertions.assertThrows(NullPointerException.class, () -> {
            list.lastIndexOf(null);
        });
    }

    @Test
    public void testCheckingListConversionInFormattedString() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals("[A,B,C,D]", list.toString());
    }

}
