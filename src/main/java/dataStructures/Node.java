package dataStructures;

/**
 * Node object represents a node in a graph.
 * @author mshroom
 */
public class Node implements Queueable {
    int index;
    int distance;
    
    /**
     * Create a new Node object.
     * @param index The index of the node must be unique.
     * @param distance The distance from the starting node.
     */
    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public int getEstimate() {
        return 0;
    }
    
    /**
     * Method checks if the node has smaller distance than the other node.
     * @param node The Node to be compared with.
     * @return True if this node has smaller distance, false otherwise.
     */
    public boolean smaller(Node node) {
        return this.distance < node.getDistance();
    }
}
