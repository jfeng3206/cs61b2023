package synthesizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object [capacity];
        this.capacity = capacity;
        this.fillCount=0;
        this.first = 0;
        this.last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ringbuffer is full!");
        }
        else {
            rb[this.last] = x;
            this.fillCount++;
            this.last = (this.last+1)%this.capacity;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        T x;
        if (isEmpty()) {
            throw new RuntimeException("Ringbuffer is empty!");
        }
        else {
            x = rb[this.first];
            rb[this.first] = null;
            this.fillCount--;
            this.first = (this.first+1)%this.capacity;
        }
        return x;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        T x;
        if (isEmpty()) {
            throw new RuntimeException("Ringbuffer is empty!");
        } else{
            x = rb[this.first];
        }
        return x;
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public interface Iterable<T> {
        Iterator<T> iterator();
    }

    public Iterator<T> iterator(){
        return new keyIterator();
    };


    private class keyIterator implements Iterator<T> {
        int ptr = -1;
        List<T> l = new ArrayList<T>();

        private keyIterator() {
            ptr = 0;
            while (!isEmpty()) {
                l.add(dequeue());
            }
            for (int i = 0;i<capacity;i++) {
                enqueue(l.get(i));
            }
            if (!isEmpty()) {
                ptr=0;
            }
        }
        public boolean hasNext() {
            if (ptr==-1||ptr==capacity) {
                return false;
            }
            return true;
        }
        public T next() {
            T temp = l.get(ptr);
            ptr++;
            return temp;
        }
    }
}
