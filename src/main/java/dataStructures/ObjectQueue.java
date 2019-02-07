package dataStructures;

/**
 *
 * @author mshroom
 */
public class ObjectQueue {
    Queueable[] queue;
    int head;
    int tail;
    int size;

    /**
     * Create an empty ObjectQueue.
     *
     * @param size The starting size of the queue must be at least 1. The size
     * will be automatically increased if the queue is full.
     */
    public ObjectQueue(int size) {
        this.queue = new Queueable[size];
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
     * @param queueable Object to be added.
     */
    public void add(Queueable queueable) {
        this.size ++;        
        queue[this.tail] = queueable;
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
     * @param queueable Object to be added.
     */    
    public void push(Queueable queueable) {
        this.size ++;        
        if (this.head > 0) {
            queue[this.head - 1] = queueable;
            this.head --;
        } else {
            queue[queue.length - 1] = queueable;
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
    public Queueable poll() {
        this.size --;
        Queueable first = this.queue[this.head];
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
        Queueable[] newQueue = new Queueable[this.queue.length * 2];
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
    
    private void setAttributes(Queueable[] newQueue, int newHead, int newTail, int newSize) {
        this.queue = newQueue;
        this.head = newHead;
        this.tail = newTail;
        this.size = newSize;
    }
    
    public ObjectQueue copy() {
        ObjectQueue copy = new ObjectQueue(10);
        copy.setAttributes(queue.clone(), head, tail, size);
        return copy;
    }
    
    public int getSize() {
        return this.size;
    }
}
