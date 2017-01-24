package kwic.filter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
            Scanner sc = new Scanner(new FileReader(filename));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                System.out.println(line);
                pushToOutput(line);
            }
            sc.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        System.out.println("Input has finished executing");
    }
    
}
