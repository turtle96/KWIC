package kwic.pipe;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A class representing a pipe that connects 2 filters together
 * @author Vivian
 *
 */
public class Pipe {
    
    private ConcurrentLinkedQueue<String> buffer;
    
    public Pipe() {
        buffer = new ConcurrentLinkedQueue<String>();
    }
    
    /**
     * Return a string from the pipe's buffer
     */
    public String extract() {
        return buffer.poll();
    }
    
    /**
     * Insert a string in the pipe's buffer
     */
    public void insert(String s) {
        buffer.offer(s);
    }
    
    /**
     * Returns true if there is no data in the pipe
     */
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

}
