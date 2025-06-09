package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> customLinkedList;

    @BeforeEach
    public void initCustomLinkedList() {
        customLinkedList = new CustomLinkedList<>();
    }

    @Nested
    class AddingTests {
        @Test
        public void addFirstElementTest() {
            int number = 15;

            customLinkedList.addFirst(number);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(number, customLinkedList.get(0)),
                    () -> Assertions.assertEquals(number, customLinkedList.getFirst())
            );
        }

        @Test
        public void addLastElementTest() {
            int number = 15;

            customLinkedList.addLast(number);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(number, customLinkedList.get(customLinkedList.size() - 1)),
                    () -> Assertions.assertEquals(number, customLinkedList.getLast())
            );

        }

        @Test
        public void addElementWithInvalidIndexTest() {
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.add(-1, 10)),
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.add(customLinkedList.size()+1, 10))
            );
        }

        @Test
        public void addElementWithCorrectIndexTest() {
            customLinkedList.add(0, 1);
            customLinkedList.addLast(2);
            customLinkedList.add(2, 3);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, customLinkedList.get(0)),
                    () -> Assertions.assertEquals(2, customLinkedList.get(1)),
                    () -> Assertions.assertEquals(3, customLinkedList.get(2))
            );
        }

        @Test
        public void addElementInEmptyListTest() {
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.add(1, 100)),
                    () -> Assertions.assertDoesNotThrow(() -> customLinkedList.add(0, 100))
            );
        }
    }

    @Nested
    class GettingTests {

        @ParameterizedTest
        @ValueSource(ints = {-40, 0, 100000})
        public void getFirstElementTest(int a) {
            customLinkedList.addFirst(a);

            Assertions.assertEquals(a, customLinkedList.getFirst());
        }

        @ParameterizedTest
        @ValueSource(ints = {-40, 0, 100000})
        public void getLastElementTest(int a) {
            customLinkedList.addLast(a);

            Assertions.assertEquals(a, customLinkedList.getLast());
        }

        @Test
        public void getElementByInvalidIndexTest() {
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.get(-1)),
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.get(customLinkedList.size()))
            );
        }
        @Test
        public void getElementByCorrectIndexTest() {
            customLinkedList.addFirst(1);
            customLinkedList.addLast(3);
            customLinkedList.add(1, 2);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, customLinkedList.get(0)),
                    () -> Assertions.assertEquals(2, customLinkedList.get(1)),
                    () -> Assertions.assertEquals(3, customLinkedList.get(2))
            );
        }

        @Test
        public void getElementFromEmptyListTest() {
            Assertions.assertAll(
                    () -> Assertions.assertThrows(NoSuchElementException.class, () -> customLinkedList.getFirst()),
                    () -> Assertions.assertThrows(NoSuchElementException.class, () -> customLinkedList.getLast()),
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.get(0))
            );
        }

    }

    @Nested
    class RemovingTests {

        @ParameterizedTest
        @CsvSource("15,16")
        public void removeFirstElementTest(int a, int b) {
            customLinkedList.addFirst(a);
            customLinkedList.addLast(b);
            Assertions.assertEquals(a, customLinkedList.getFirst());

            customLinkedList.removeFirst();

            Assertions.assertEquals(b, customLinkedList.getFirst());
        }

        @ParameterizedTest
        @CsvSource("15,16")
        public void removeLastElementTest(int a, int b) {
            customLinkedList.addFirst(a);
            customLinkedList.addLast(b);
            Assertions.assertEquals(b, customLinkedList.getLast());

            customLinkedList.removeLast();

            Assertions.assertEquals(a, customLinkedList.getLast());
        }

        @Test
        public void removeElementByInvalidIndexTest() {
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.remove(-1)),
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.remove(customLinkedList.size()))
            );
        }

        @Test
        public void removeElementByCorrectIndexTest() {
            customLinkedList.addFirst(1);
            customLinkedList.addLast(3);
            customLinkedList.add(1, 2);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, customLinkedList.get(0)),
                    () -> Assertions.assertEquals(2, customLinkedList.get(1)),
                    () -> Assertions.assertEquals(3, customLinkedList.get(2)),

                    () -> {
                        customLinkedList.remove(0);
                        Assertions.assertEquals(2, customLinkedList.getFirst());
                    },
                    () -> {
                        customLinkedList.remove(1);
                        Assertions.assertEquals(2, customLinkedList.getLast());
                    },
                    () -> {
                        customLinkedList.remove(0);
                        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.get(0));
                    }

            );
        }
    }

    @Nested
    class SizeTests {

        @Test
        public void newListShouldHaveZeroSizeTest() {
            Assertions.assertEquals(0, customLinkedList.size());
        }

        @Test
        public void shouldIncreaseSizeWhenElementsAddedTest() {
            int input = 1;
            int sizeBefore = customLinkedList.size();

            Assertions.assertAll(
                    () -> {
                        customLinkedList.addFirst(input);

                        Assertions.assertEquals(sizeBefore + 1, customLinkedList.size());
                    },
                    () -> {
                        customLinkedList.addLast(input);

                        Assertions.assertEquals(sizeBefore + 2, customLinkedList.size());
                    },
                    () -> {
                        customLinkedList.add(1, input);

                        Assertions.assertEquals(sizeBefore + 3, customLinkedList.size());
                    }
            );
        }

        @Test
        public void shouldDecreaseSizeWhenElementsRemovedTest()
        {
            customLinkedList.addFirst(1);
            customLinkedList.addFirst(2);
            customLinkedList.addFirst(3);
            int sizeBefore = customLinkedList.size();

            Assertions.assertAll(
                    () -> {
                        customLinkedList.removeFirst();

                        Assertions.assertEquals(sizeBefore-1, customLinkedList.size());
                    },
                    () -> {
                        customLinkedList.removeLast();

                        Assertions.assertEquals(sizeBefore-2, customLinkedList.size());
                    },
                    () -> {
                        customLinkedList.remove(0);

                        Assertions.assertEquals(sizeBefore-3, customLinkedList.size());
                    }
            );
        }

        @Test
        public void shouldNotChangeAfterUsingIncorrectIndexText() {
            int sizeBefore = customLinkedList.size();

            Assertions.assertAll(
                    () ->Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.add(sizeBefore+1, 0)),
                    () -> Assertions.assertEquals(sizeBefore, customLinkedList.size()),
                    () -> Assertions.assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.remove(sizeBefore)),
                    () -> Assertions.assertEquals(sizeBefore, customLinkedList.size())
            );
        }

    }
}
