package main.kwic.filter;

import main.kwic.pipe.Pipe;

public class Output extends Filter {

    public Output(Pipe input, Pipe output) {
        super(input, output);
    }

    @Override
    public void run() {
        System.out.println("\nThe KWIC Index contains: ");
        
        while (true) {
            if (isInputEmpty()) {
                waitForXMilliSeconds(1);
            } else {
                String line = pullFromInput();
                if (line.equals("eof")) {
                    break;
                }
                System.out.println(line);
            }
        }
        
    }

}
