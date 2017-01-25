package kwic.filter;

import java.util.PriorityQueue;

import kwic.model.Data;
import kwic.pipe.Pipe;

public class Alphabetizer extends Filter {
    
    private PriorityQueue<Data> sorter;

    public Alphabetizer(Pipe input, Pipe output) {
        super(input, output);
        sorter = new PriorityQueue<Data>();
    }

    @Override
    public void run() {
        while (true) {
            if (isInputEmpty()) {
                sleepForXMilliSeconds(1);
            } else {
                Data data = pullFromInput();
                if (data.isLast()) {
                    sendLastDataObjFlag();
                    break;
                }
                sorter.offer(data);
            }
        }
        
        pushSortedLinesToOutput();
        sendLastDataObjFlag();
    }

    private void pushSortedLinesToOutput() {
        while (!sorter.isEmpty()) {
            Data data = sorter.poll();
            pushToOutput(data);
        }
    }

}
