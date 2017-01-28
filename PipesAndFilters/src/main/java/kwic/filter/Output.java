package kwic.filter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * Represents the main component, Output, of the system.
 */
public class Output extends Filter {

    public Output(Pipe input, Pipe output) {
        super(input, output);
    }

    /**
     * Read data from input pipe. 
     * Write data to file.
     */
    @Override
    public void run() {
        System.out.println("The KWIC Index System contains:");
        
        String outputFileName = "output.txt";
        BufferedWriter bw = null;
        
        try {
            bw = new BufferedWriter(new FileWriter(outputFileName));
            while (true) {
                if (isInputEmpty()) {
                    delay();
                } else {
                    Data data = pullFromInput();
                    if (data.isLast()) {
                        break;
                    }
                    bw.write(data.getValue());
                    bw.newLine();
                    System.out.println(data.getValue());
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing to output file.");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Unrecoverable error from Output::run()");
            }
        }
        
    }

}
