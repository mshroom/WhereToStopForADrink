package dataStructures;

import java.lang.reflect.Array;

/**
 * A generic type queue with dynamically growing size. The queue supports both
 * first-in-first-out and last-in-first-out operations.
 *
 * @author mshroom
 */
public class Queue<T> {

    T[] queue;
    int head;
    int tail;
    int size;

    /**
     * Create a Queue with given array.
     *
     * @param queue An empty array of generic type with which the queue will be
     * initialized. The size of the array will be automatically increased if the
     * queue is full.
     */
    public Queue(T[] queue) {
        this.queue = queue;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * Method tells if the queue is empty.
     *
     * @return true if the queue is empty.
     */
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    /**
     * Method tells if the queue is full.
     *
     * @return true if the queue is full.
     */
    private boolean full() {
        int next = this.tail + 1;
        if (next == this.queue.length) {
            next = 0;
        }
        return this.head == next;
    }

    /**
     * Method adds an object to the end of the queue.
     *
     * @param object Object to be added.
     */
    public void add(T object) {
        this.size++;
        queue[this.tail] = object;
        this.tail++;
        if (this.tail == this.queue.length) {
            this.tail = 0;
        }
        if (this.full()) {
            this.doubleSize();
        }
    }

    /**
     * Method adds an object to the head of the queue.
     *
     * @param object Object to be added.
     */
    public void push(T object) {
        this.size++;
        if (this.head > 0) {
            queue[this.head - 1] = object;
            this.head--;
        } else {
            queue[queue.length - 1] = object;
            this.head = queue.length - 1;
        }
        if (this.full()) {
            this.doubleSize();
        }
    }

    /**
     * Method returns the object at the head of the queue and removes it from
     * the queue.
     *
     * @return The object at the head of the queue.
     */
    public T poll() {
        this.size--;
        T first = this.queue[this.head];
        this.head++;
        if (this.head == this.queue.length) {
            this.head = 0;
        }
        return first;
    }

    /**
     * Method returns the object at the head of the queue but does not remove it
     * from the queue.
     *
     * @return The object at the head of the queue.
     */
    public T peek() {
        return this.queue[this.head];
    }

    /**
     * Method doubles the size of the queue.
     */
    private void doubleSize() {
        T[] newQueue = (T[]) Array.newInstance(queue[head].getClass(), this.queue.length * 2);
        int copy = this.head;
        int newTail = 0;
        for (int i = 0; i < this.queue.length; i++) {
            newQueue[i] = this.queue[copy];
            if (copy == this.tail) {
                newTail = i;
                break;
            }
            copy++;
            if (copy == this.queue.length) {
                copy = 0;
            }

        }
        this.queue = newQueue;
        this.tail = newTail;
        this.head = 0;
    }

    private void setAttributes(int newHead, int newTail, int newSize) {
        this.head = newHead;
        this.tail = newTail;
        this.size = newSize;
    }

    /**
     * Creates a copy of this queue.
     *
     * @return a new Queue object
     */
    public Queue<T> copy() {
        T[] newQ = queue.clone();
        Queue copy = new Queue(newQ);
        copy.setAttributes(head, tail, size);
        return copy;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Tells if the given object is in the queue.
     * @param t Object to check.
     * @return true if the object is in the queue, false otherwise.
     */
    public boolean contains(T t) {
        if (head < tail) {
            for (int i = head; i < tail; i++) {
                if (queue[i].equals(t)) {
                    return true;
                }
            }
        } else if (head > tail) {
            for (int i = head; i < queue.length; i++) {
                if (queue[i].equals(t)) {
                    return true;
                }
            }
            for (int i = 0; i < tail; i ++) {
                if (queue[i].equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }
}
