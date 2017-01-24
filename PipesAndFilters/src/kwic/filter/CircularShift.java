package kwic.filter;

import kwic.pipe.Pipe;

public class CircularShift extends Filter implements Runnable {
    
    
    public CircularShift(Pipe input, Pipe output) {
        super(input, output);
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
                String[] data = line.split(" ");
                
                System.out.println("transformed data: " + line);
                pushToOutput(line);
            }
        }
        
        System.out.println("CircularShift has finished executing");
        
    }
    
    private void waitForXMilliSeconds(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
