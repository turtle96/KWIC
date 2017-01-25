package kwic.filter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * A class representing a filter that computes a task/process.
 * @author Vivian
 *
 */
public abstract class Filter implements Runnable {
    
    private Pipe inputPipe;
    private Pipe outputPipe;
    
    public Filter(Pipe input, Pipe output) {
        inputPipe = input;
        outputPipe = output;
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
     * Returns true if there is no incoming data
     */
    public boolean isInputEmpty() {
        return inputPipe.isEmpty();
    }
    
    public void sendLastDataObjFlag() {
        pushToOutput(Data.createEndOfDataObj());
    }
    
    public void sleepForXSeconds(int x) {
        try {
            int seconds = x * 1000;
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public abstract void run();
    
    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    
}
