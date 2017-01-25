package kwic.pipe;

import java.util.concurrent.ConcurrentLinkedQueue;

import kwic.model.Data;

/**
 * A class representing a pipe that connects 2 filters together
 * @author Vivian
 *
 */
public class Pipe {
    
    private ConcurrentLinkedQueue<Data> buffer;
    
    public Pipe() {
        buffer = new ConcurrentLinkedQueue<Data>();
    }
    
    /**
     * Return a string from the pipe's buffer
     */
    public Data extract() {
        return buffer.poll();
    }
    
    /**
     * Insert a string in the pipe's buffer
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
