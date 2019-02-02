package dataStructures;

import control.Place;

/**
 *
 * @author mshroom
 */
public class PlaceQueue {
    Place[] queue;
    int head;
    int tail;
    int size;

    /**
     * Create an empty NodeQueue object.
     *
     * @param size The starting size of the queue must be at least 1. The size
     * will be automatically increased if the queue is full.
     */
    public PlaceQueue(int size) {
        this.queue = new Place[size];
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
     * Method adds an integer to the end of the queue.
     *
     * @param integer Integer to be added.
     */
    public void add(Place node) {
        this.size ++;        
        queue[this.tail] = node;
        this.tail++;
        if (this.tail == this.queue.length) {
            this.tail = 0;
        }
        if (this.full()) {
            this.doubleSize();
        }
    }

    /**
     * Method adds an integer to the head of the queue.
     *
     * @param integer Integer to be added.
     */    
    public void push(Place node) {
        this.size ++;        
        if (this.head > 0) {
            queue[this.head - 1] = node;
            this.head --;
        } else {
            queue[queue.length - 1] = node;
            this.head = queue.length - 1;
        }
        if (this.full()) {
            this.doubleSize();
        }
    }
    /**
     * Method returns the integer at the head of the queue and removes it from
     * the queue.
     *
     * @return the integer at the head of the queue.
     */
    public Place poll() {
        this.size --;
        Place first = this.queue[this.head];
        this.head++;
        if (this.head == this.queue.length) {
            this.head = 0;
        }
        return first;
    }

    /**
     * Method doubles the size of the queue.
     */
    private void doubleSize() {
        Place[] newQueue = new Place[this.queue.length * 2];
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
    
    private void setAttributes(Place[] newQueue, int newHead, int newTail, int newSize) {
        this.queue = newQueue;
        this.head = newHead;
        this.tail = newTail;
        this.size = newSize;
    }
    
    public PlaceQueue copy() {
        PlaceQueue copy = new PlaceQueue(10);
        copy.setAttributes(queue.clone(), head, tail, size);
        return copy;
    }
    
    public int getSize() {
        return this.size;
    }
}
