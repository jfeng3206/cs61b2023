package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private synthesizer.BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR/frequency);
        buffer = new ArrayRingBuffer(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        // Dequeue everything in the buffer
        while(!buffer.isEmpty()){
            buffer.dequeue();
        }

        double r = Math.random()-0.5;
        buffer.enqueue(r);

        while(!buffer.isFull()){
            double toAdd = Math.random() - 0.5;
            if (toAdd != buffer.peek()) {
                buffer.enqueue(toAdd);
            } else {
                continue;
            }
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
            double a = buffer.dequeue();
            double sample = (a+buffer.peek())*0.5d*DECAY;
            buffer.enqueue(sample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
