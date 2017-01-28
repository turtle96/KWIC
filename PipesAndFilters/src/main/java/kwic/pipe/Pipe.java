package kwic.pipe;

import java.util.concurrent.ConcurrentLinkedQueue;

import kwic.model.Data;

/**
 * Represents a pipe that connects two filters together
 */
public class Pipe {
    
    private ConcurrentLinkedQueue<Data> buffer;
    
    public Pipe() {
        buffer = new ConcurrentLinkedQueue<Data>();
    }
    
    /**
     * Extract data from the pipe's buffer
     */
    public Data extract() {
        return buffer.poll();
    }
    
    /**
     * Insert data into the pipe's buffer
     */
    public void insert(Data data) {
        buffer.offer(data);
    }
    
    /**
     * Returns true if there is no data in the pipe
     */
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

}
