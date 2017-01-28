package kwic.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * Represents the main component, Input, of the system.
 */
public class Input extends Filter {
    
    private FileReader inputFr;
    
    public Input(Pipe input, Pipe output, FileReader fr) {
        super(input, output);
        inputFr = fr;
    }

    /**
     * Read data from a file and pushes it to its output pipe.
     * Sends a last data object when reached the end of file. 
     */
    @Override
    public void run() {
        BufferedReader br = new BufferedReader(inputFr);
        try {
            String line = br.readLine();
            while (line != null) {
                Data data = new Data(line);
                pushToOutput(data);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading file containing list of input titles");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unrecoverable error from Input::run()");
            }
        }
        
        sendLastDataObjFlag();
    }
    
}
