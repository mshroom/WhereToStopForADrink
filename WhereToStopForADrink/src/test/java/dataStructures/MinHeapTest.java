package dataStructures;

import algorithms.Node;
import algorithms.NodeWithEstimate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class MinHeapTest {

    MinHeap minHeap;

    @Before
    public void setUp() {
        this.minHeap = new MinHeap(10);
    }

    @Test
    public void theHeapIsEmptyWhenItIsCreated() {
        assertTrue(minHeap.isEmpty());
    }
    
    @Test
    public void noNodeIsReturnedWhenTheHeapIsEmpty() {
        assertEquals(null, minHeap.poll());
    }
            
    @Test
    public void aNodeCanBeAddedToTheHeap() {
        Node node = new Node(0, 5);
        minHeap.add(node);
        Node same = minHeap.poll();
        assertEquals("0, 5", "" + same.getIndex() + ", " + same.getDistance());
    }

    @Test
    public void aNodeCanBeRemovedFromTheHeap() {
        Node node = new Node(0, 5);
        minHeap.add(node);
        minHeap.poll();
        assertTrue(minHeap.isEmpty());
    }
    
    @Test
    public void theSmallestNodeIsTheFirstToGetOut() {
        minHeap.add(new Node(0, 5));
        minHeap.add(new Node(1, 3));
        minHeap.add(new Node(2, 4));
        Node smallest = minHeap.poll();
        assertEquals(1, smallest.getIndex());
    }
    
    @Test
    public void theSmallestNodeWithEstimateIsTheFirstToGetOut() {
        minHeap.add(new NodeWithEstimate(0, 5, 2));
        minHeap.add(new NodeWithEstimate(1, 3, 4));
        minHeap.add(new NodeWithEstimate(2, 4, 2));
        Node smallest = minHeap.poll();
        assertEquals(2, smallest.getIndex());   
    }

}
