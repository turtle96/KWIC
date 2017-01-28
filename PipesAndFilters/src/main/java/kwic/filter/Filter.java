package kwic.filter;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * Represents a filter that computes a task/process.
 */
public abstract class Filter implements Runnable {
    
    private Pipe inputPipe;
    private Pipe outputPipe;
    
    private int delayTime;
    
    public Filter(Pipe input, Pipe output) {
        inputPipe = input;
        outputPipe = output;
        delayTime = 500; // sets delay of filter to 500ms
    }
    
    /**
     * Initialize a Filter with delay time set to <b><i><code>delay</code></i></b> milliseconds
     * @param delay length of time(in milliseconds) filter will sleep for when delay() method is called
     */
    public Filter(Pipe input, Pipe output, int delay) {
        inputPipe = input;
        outputPipe = output;
        delayTime = delay;
    }
    
    /**
     * Push data out to the pipe connected to the next filter.
     */
    public void pushToOutput(Data data) {
        outputPipe.insert(data);
    }
    
    /**
     * Pull data from the pipe connected to the previous filter.
     */
    public Data pullFromInput() {
        return inputPipe.extract();
    }
    
    /**
     * Returns true if there is no incoming data.
     */
    public boolean isInputEmpty() {
        return inputPipe.isEmpty();
    }
    
    /**
     * Sends a flag signaling that there is no more data to be sent.
     */
    public void sendLastDataObjFlag() {
        pushToOutput(Data.createEndOfDataObj());
    }
    
    /**
     * Pause the filter's execution for a set amount of time. The default is 500 milliseconds.
     */
    public void delay() {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @Override
    public abstract void run();
    
}
