package mk.ukim.finki.NP.lab5.Queue;

import java.lang.reflect.Array;

public class Queue<T> {
    private T[] elements;
    private int front;
    private int rear;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public Queue() {
        this.elements = createArray(DEFAULT_CAPACITY);
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(T element) {
        if (size == elements.length) {
            resize();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return element;
    }

    public T peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return elements[front];
    }

    public T inspect() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return elements[rear];
    }

    public int count() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        T[] newElements = createArray(newCapacity);

        int count = 0;
        for (int i = front; count < size; i++) {
            newElements[count++] = elements[i % elements.length];
        }

        elements = newElements;
        front = 0;
        rear = size - 1;
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int length) {
        return (T[]) Array.newInstance(Object.class, length);
    }
}
