package algorithms;

/**
 *
 * @author mshroom
 */
public class Node implements Comparable<Node> {
    int index;
    int distance;
    
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
    
    @Override
    public int compareTo(Node n) {
        if (this.distance < n.distance) {
            return -1;
        }
        return 1;
    }
}
