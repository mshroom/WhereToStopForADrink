package algorithms;

/**
 * NodeWithEstimate object represents a node in a graph with additional estimate property.
 * @author mshroom
 */
public class NodeWithEstimate extends Node {
    int estimate;
    
    /**
     * Create a new NodeWithEstimate object.
     * @param index The index of the node must be unique.
     * @param distance The distance from the starting node.
     * @param estimate The estimated distance from the goal node.
     */
    public NodeWithEstimate(int index, int distance, int estimate) {
        super(index, distance);
        this.estimate = estimate;
    }
    
    @Override
    public int getEstimate() {
        return this.estimate;
    }
    
    @Override
    public boolean smaller(Node n) {
        return this.distance + this.estimate < n.getDistance() + n.getEstimate();
    }
}
