package algorithms;

/**
 *
 * @author mshroom
 */
public class NodeWithEstimate implements Comparable<NodeWithEstimate> {
    int index;
    int fromStart;
    int estimate;
    
    public NodeWithEstimate(int index, int fromStart, int estimate) {
        this.index = index;
        this.fromStart = fromStart;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int getDistance() {
        return this.fromStart;
    }
    
    @Override
    public int compareTo(NodeWithEstimate n) {
        if (this.fromStart + this.estimate < n.fromStart + n.estimate) {
            return -1;
        }
        return 1;
    }
}
