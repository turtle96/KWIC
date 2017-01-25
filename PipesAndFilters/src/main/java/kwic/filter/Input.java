package kwic.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import kwic.model.Data;
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
            sleepForXSeconds(1);
            while (line != null) {
                System.out.println(getCurrentTime() + " Input read: " + line);
                Data data = new Data(line);
                pushToOutput(data);
                System.out.println(getCurrentTime() + " Input pushed: " + line);
                sleepForXSeconds(1);
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
        System.out.println(getCurrentTime() + " Input pushed: last data");
    }

}
