package kwic.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import kwic.pipe.Pipe;

public class Input extends Filter {
    
    private FileReader inputFr;
    
    public Input(Pipe input, Pipe output, FileReader fr) {
        super(input, output);
        inputFr = fr;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(inputFr);
        try {
            String line = br.readLine();
            while (line != null) {
                pushToOutput(line);
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
    }

}
