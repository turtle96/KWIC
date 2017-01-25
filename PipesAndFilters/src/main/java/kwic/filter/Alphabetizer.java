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
                sleepForXSeconds(1);
            } else {
                sleepForXSeconds(1);
                Data data = pullFromInput();
                System.out.println(getCurrentTime() + " A pulled: " + data.getValue());
                if (data.isLast()) {
                    break;
                }
                sorter.offer(data);
            }
        }
        
        pushSortedLinesToOutput();
        sendLastDataObjFlag();
        System.out.println(getCurrentTime() + " A pushed: last data");
    }

    private void pushSortedLinesToOutput() {
        while (!sorter.isEmpty()) {
            Data data = sorter.poll();
            pushToOutput(data);
            System.out.println(getCurrentTime() + " A pushed: " + data.getValue());
            sleepForXSeconds(1);
        }
    }

}
