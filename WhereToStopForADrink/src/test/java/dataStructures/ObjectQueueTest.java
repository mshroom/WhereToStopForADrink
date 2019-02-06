package dataStructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class ObjectQueueTest {

    ObjectQueue queue;

    @Before
    public void setUp() {
        this.queue = new ObjectQueue(5);
    }

    @Test
    public void theQueueIsEmptyWhenItIsCreated() {
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void noObjectIsReturnedIfTheQueueIsEmpty() {
        assertEquals(null, queue.poll());
    }

    @Test
    public void anObjectCanBeAddedToTheQueue() {
        Node node = new Node(0, 5);
        queue.add(node);
        Node same = (Node) queue.poll();
        assertEquals("0, 5", "" + same.getIndex() + ", " + same.getDistance());
    }

    @Test
    public void anObjectCanBeAddedToTheHeadOfTheQueue() {
        Node node = new Node(0, 5);
        Node node2 = new Node(1, 4);
        queue.add(node);
        queue.push(node2);
        Node first = (Node) queue.poll();
        assertEquals(1, first.getIndex());
    }

    @Test
    public void anObjectCanBeAddedToTheTailOfTheQueue() {
        Node node = new Node(0, 5);
        Node node2 = new Node(1, 4);
        queue.add(node);
        queue.add(node2);
        Node first = (Node) queue.poll();
        assertEquals(0, first.getIndex());
    }

    @Test
    public void anObjectCanBeRemovedFromTheQueue() {
        queue.add(new Node(0, 5));
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void objectsCanStillBeAddedToTheQueueWhenTheOriginalSizeOfTheQueueIsExceeded() {
        for (int i = 0; i < 10; i ++) {
            queue.add(new Node(i, 1));
        }
        for (int i = 0; i < 9; i ++) {
            queue.poll();
        }
        Node n = (Node) queue.poll();
        assertEquals(9, n.getIndex());
    }
}
