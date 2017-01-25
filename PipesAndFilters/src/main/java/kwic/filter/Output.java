package kwic.filter;

import kwic.model.Data;
import kwic.pipe.Pipe;

public class Output extends Filter {

    public Output(Pipe input, Pipe output) {
        super(input, output);
    }

    @Override
    public void run() {
        System.out.println("\nThe KWIC Index contains: ");
        
        while (true) {
            if (isInputEmpty()) {
                sleepForXSeconds(1);
            } else {
                sleepForXSeconds(1);
                Data data = pullFromInput();
                System.out.println(getCurrentTime() + " Output pulled: " + data.getValue());
                if (data.isLast()) {
                    break;
                }
                //System.out.println(data.getValue());
            }
        }
    }

}
