package main.kwic.filter;

import java.util.PriorityQueue;

import main.kwic.pipe.Pipe;

public class Alphabetizer extends Filter {
    
    private PriorityQueue<String> sorter;

    public Alphabetizer(Pipe input, Pipe output) {
        super(input, output);
        sorter = new PriorityQueue<String>();
    }

    @Override
    public void run() {
        while (true) {
            if (isInputEmpty()) {
                waitForXMilliSeconds(1);
            } else {
                String line = pullFromInput();
                if (line.equals("eof")) {
                    break;
                }
                sorter.offer(line);
            }
        }
        
        pushSortedLinesToOutput();
        pushToOutput("eof");
    }

    private void pushSortedLinesToOutput() {
        while (!sorter.isEmpty()) {
            String line = sorter.poll();
            pushToOutput(line);
        }
    }

}
