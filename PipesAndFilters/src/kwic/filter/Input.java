package kwic.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import kwic.pipe.Pipe;

public class Input extends Filter implements Runnable {
    
    private String filename;
    
    public Input(Pipe input, Pipe output, String filename) {
        super(input, output);
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                pushToOutput(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to read " + filename);
        }
    }
    
}
