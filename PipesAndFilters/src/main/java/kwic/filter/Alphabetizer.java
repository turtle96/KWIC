package kwic.filter;

import java.util.PriorityQueue;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * Represents the main component, Alphabetizer, of the system.
 */
public class Alphabetizer extends Filter {
    
    private PriorityQueue<Data> sorter;

    public Alphabetizer(Pipe input, Pipe output) {
        super(input, output);
        sorter = new PriorityQueue<Data>();
    }

    /**
     * Reads data from input pipe. 
     * Sorts data in alphabetical order.
     * Only starts pushing transformed data to output pipe when last data flag has been received.
     */
    @Override
    public void run() {
        while (true) {
            if (isInputEmpty()) {
                delay();
            } else {
                Data data = pullFromInput();
                if (data.isLast()) {
                    break;
                }
                sorter.offer(data);
            }
        }
        pushSortedLinesToOutput();
        sendLastDataObjFlag();
    }

    /**
     * Pushes sorted data to output pipe.
     */
    private void pushSortedLinesToOutput() {
        while (!sorter.isEmpty()) {
            Data data = sorter.poll();
            pushToOutput(data);
        }
    }

}
