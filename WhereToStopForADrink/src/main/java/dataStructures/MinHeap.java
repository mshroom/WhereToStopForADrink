package dataStructures;

/**
 * MinHeap is a priority queue with for Node objects. The size of the queue is predefined and
 * cannot be altered. Nodes can be added to the queue and
 * the smallest one is the first to be removed. Node sizes are defined and compared
 * in the Node class and its subclasses.
 * @author mshroom
 */
public class MinHeap {
    
    private Node[] heap;
    private int heapSize;
    
    public MinHeap(int size) {
        this.heap = new Node[size + 1];
        this.heapSize = 0;
    }
    /**
     * Adds a Node into the heap.
     * @param node Node object to be added.
     */
    public void add(Node node) {
        this.heapSize ++;
        int i = this.heapSize;
        while ((i > 1) && !(this.heap[parent(i)].smaller(node))) {
            this.heap[i] = this.heap[parent(i)];
            i = parent(i);
        }
        this.heap[i] = node;
    }
    
    /**
     * Removes and returns the smallest Node object in the heap.
     * @return The smallest Node..
     */
    public Node poll() {
        if (this.isEmpty()) {
            return null;
        }
        Node min = this.heap[1];
        this.heap[1] = this.heap[this.heapSize];
        this.heapSize --;
        this.heapify(1);
        return min;        
    }
    
    /**
     * Checks if the heap is empty.
     * @return true if the heap contains no Nodes.
     */
    public boolean isEmpty() {
        return this.heapSize == 0;
    }
    
    private int parent(int i) {
        return i / 2;
    }
    
    private int left(int i) {
        return 2 * i;
    }
    
    private int right(int i) {
        return (2 * i) + 1;
    }
    
    /**
     * Checks if the Nodes in the heap are in the right order and rearranges the Nodes if necessary.
     * @param i The index of the Node which sub-heap is to be checked.
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        
        if (r <= this.heapSize) {
            int smallest = l;
            if (this.heap[r].smaller(this.heap[l])) {
                smallest = r;
            }
            if (!this.heap[i].smaller(this.heap[smallest])) {
                Node mem = this.heap[i];
                this.heap[i] = this.heap[smallest];
                this.heap[smallest] = mem;
                this.heapify(smallest);
            }
        } else if ((l == this.heapSize) && !(this.heap[i].smaller(this.heap[l]))) {
                Node mem = this.heap[i];
                this.heap[i] = this.heap[l];
                this.heap[l] = mem;
        }
    }
    
}
