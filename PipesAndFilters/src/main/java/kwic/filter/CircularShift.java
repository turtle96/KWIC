package kwic.filter;

import java.util.HashSet;

import kwic.model.Data;
import kwic.pipe.Pipe;

/**
 * Represents the main component, Circular Shift, of the system.
 */
public class CircularShift extends Filter {
    
    private HashSet<String> ignoreSet;    
    
    public CircularShift(Pipe input, Pipe output, HashSet<String> ignoreSet) {
        super(input, output);
        this.ignoreSet = ignoreSet;
    }

    /**
     * Read data from input pipe. 
     * Perform circular shift on the data. 
     * Push transformed data to output pipe.
     */
    @Override
    public void run() {
        while (true) {
            if (isInputEmpty()) {
                delay();
            } else {
                Data data = pullFromInput();
                if (data.isLast()) {
                    sendLastDataObjFlag();
                    break;
                }
                shiftInput(data.getValue());
            }
        }
    }
    
    /**
     * Performs circular shift on input string.
     */
    private void shiftInput(String line) {
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (ignoreSet.contains(words[i].toLowerCase())) {
                continue;
            } else {
                String shiftedLine = buildOutputFromIthWord(words, i);
                pushToOutput(new Data(shiftedLine));
            }
        }
        
    }

    /**
     * Build output string starting from the i-th index of the input array.
     */
    private String buildOutputFromIthWord(String[] words, int i) {
        StringBuilder sb = new StringBuilder();
        final int NUM_OF_WORDS = words.length;
        int count = 0;
        int index = i;
        while (count < NUM_OF_WORDS) {
            sb.append(words[index] + " ");
            index++;
            if (index >= NUM_OF_WORDS) {
                index = 0;
            }
            count++;
        }
        return sb.toString().trim();
    }

}
