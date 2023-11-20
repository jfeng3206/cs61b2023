package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnque() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());

        arb.enqueue(10);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        arb.enqueue(6);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(1);
        assertFalse(arb.isEmpty());
        assertTrue(arb.isFull());

        assertEquals(10,arb.peek());

        arb.dequeue();
        arb.dequeue();
        assertFalse(arb.isFull());
        assertEquals(8,arb.fillCount());
        assertEquals(10,arb.capacity());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
