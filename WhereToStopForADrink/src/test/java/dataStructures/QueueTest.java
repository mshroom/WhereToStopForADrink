package dataStructures;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class QueueTest {

    Queue queue;

    @Before
    public void setUp() {
        this.queue = new Queue(5);
    }

    @Test
    public void theQueueIsEmptyWhenItIsCreated() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void noIntegerIsReturnedIfTheQueueIsEmpty() {
        int i = 0;
        try {
            i = this.queue.poll();
        } catch (Throwable ex) {
            i = -1;
        }
        assertEquals(-1, i);
    }

    @Test
    public void anIntegerCanBeAddedToTheQueue() throws Throwable {
        queue.add(5);
        assertEquals(5, queue.poll());
    }

    @Test
    public void anIntegerCanBeAddedToTheHeadOfTheQueue() throws Throwable {
        queue.add(5);
        queue.push(4);
        assertEquals(4, queue.poll());
    }

    @Test
    public void anIntegerCanBeAddedToTheTailOfTheQueue() throws Throwable {
        queue.add(5);
        queue.add(4);
        assertEquals(5, queue.poll());
    }

    @Test
    public void anIntegerCanBeRemovedFromTheQueue() throws Throwable {
        queue.add(5);
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void integersCanStillBeAddedToTheQueueWhenTheOriginalSizeOfTheQueueIsExceeded() throws Throwable {
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 9; i++) {
            queue.poll();
        }
        assertEquals(9, queue.poll());
    }
}
