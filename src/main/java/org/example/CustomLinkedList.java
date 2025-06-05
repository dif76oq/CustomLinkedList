package org.example;

import java.util.NoSuchElementException;


public class CustomLinkedList<T> {
    private final Node<T> head = new Node<>();
    private final Node<T> tail = new Node<>();
    private int size;

    {
        head.ptrNext = tail;
        tail.ptrPrev = head;
        size = 0;
    }

    private static class Node<T> {

        private Node<T> ptrNext;
        private Node<T> ptrPrev;
        private T value;


        public Node(Node<T> ptrNext, Node<T> ptrPrev, T value) {
            this.ptrNext = ptrNext;
            this.ptrPrev = ptrPrev;
            this.value = value;
        }
        public Node() {
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(T element) {
        Node<T> newNode = new Node<>(head.ptrNext, head, element);
        newNode.ptrNext.ptrPrev = newNode;
        head.ptrNext = newNode;
        size++;
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(tail, tail.ptrPrev, element);
        newNode.ptrPrev.ptrNext = newNode;
        tail.ptrPrev = newNode;
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node<T> current = head.ptrNext;

        while (i < index) {
            current = current.ptrNext;
            i++;
        }

        Node<T> newNode = new Node<>(current, current.ptrPrev, element);
        current.ptrPrev.ptrNext = newNode;
        current.ptrPrev = newNode;
        size++;
    }

    public T getFirst() {
        if (head.ptrNext != tail) {
            return head.ptrNext.value;
        }
        throw new NoSuchElementException();
    }

    public T getLast() {
        if (tail.ptrPrev != head) {
            return tail.ptrPrev.value;
        }
        throw new NoSuchElementException();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node<T> current = head.ptrNext;

        while (i < index) {
            current = current.ptrNext;
            i++;
        }

        return current.value;
    }

    public void removeFirst() {
        if (head.ptrNext == tail) {
            throw new NoSuchElementException();
        }
        head.ptrNext = head.ptrNext.ptrNext;
        head.ptrNext.ptrPrev = head;
        size--;
    }

    public void removeLast() {
        if (tail.ptrPrev == head) {
            throw new NoSuchElementException();
        }
        tail.ptrPrev = tail.ptrPrev.ptrPrev;
        tail.ptrPrev.ptrNext = tail;
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head.ptrNext;
        int i = 0;

        while (i < index) {
            current = current.ptrNext;
            i++;
        }

        current.ptrPrev.ptrNext = current.ptrNext;
        current.ptrNext.ptrPrev = current.ptrPrev;
        size--;
    }
}
