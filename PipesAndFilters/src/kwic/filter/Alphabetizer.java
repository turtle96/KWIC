package kwic.filter;

import java.util.PriorityQueue;

import kwic.pipe.Pipe;

public class Alphabetizer extends Filter implements Runnable {
    
    private PriorityQueue<String> buffer;

    public Alphabetizer(Pipe input, Pipe output) {
        super(input, output);
        buffer = new PriorityQueue<String>();
    }

    @Override
    public void run() {
//        while (true) {
//            if (inputPipe.isEmpty()) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("Error: " + e.getMessage());
//                }
//            } else {
//                buffer.offer(inputPipe.extract());
//            }
//        }
    }

}
