package dataStructures;

import algorithms.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class NodeQueueTest {

    NodeQueue queue;

    @Before
    public void setUp() {
        this.queue = new NodeQueue(5);
    }

    @Test
    public void theQueueIsEmptyWhenItIsCreated() {
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void noNodeIsReturnedIfTheQueueIsEmpty() {
        assertEquals(null, queue.poll());
    }

    @Test
    public void aNodeCanBeAddedToTheQueue() {
        Node node = new Node(0, 5);
        queue.add(node);
        Node same = queue.poll();
        assertEquals("0, 5", "" + same.getIndex() + ", " + same.getDistance());
    }

    @Test
    public void aNodeCanBeAddedToTheHeadOfTheQueue() {
        Node node = new Node(0, 5);
        Node node2 = new Node(1, 4);
        queue.add(node);
        queue.push(node2);
        Node first = queue.poll();
        assertEquals(1, first.getIndex());
    }

    @Test
    public void aNodeCanBeAddedToTheTailOfTheQueue() {
        Node node = new Node(0, 5);
        Node node2 = new Node(1, 4);
        queue.add(node);
        queue.add(node2);
        Node first = queue.poll();
        assertEquals(0, first.getIndex());
    }

    @Test
    public void aNodeCanBeRemovedFromTheQueue() {
        queue.add(new Node(0, 5));
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void nodesCanStillBeAddedToTheQueueWhenTheOriginalSizeOfTheQueueIsExceeded() {
        for (int i = 0; i < 10; i ++) {
            queue.add(new Node(i, 1));
        }
        for (int i = 0; i < 9; i ++) {
            queue.poll();
        }
        assertEquals(9, queue.poll().getIndex());
    }
}
