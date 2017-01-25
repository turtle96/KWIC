package main.kwic.filter;

import java.util.HashSet;

import main.kwic.pipe.Pipe;

public class CircularShift extends Filter {
    
    private HashSet<String> ignoreSet;    
    
    public CircularShift(Pipe input, Pipe output, HashSet<String> ignoreSet) {
        super(input, output);
        this.ignoreSet = ignoreSet;
    }

    @Override
    public void run() {
        while (true) {
            if (isInputEmpty()) {
                waitForXMilliSeconds(1);
            } else {
                String line = pullFromInput();
                if (line.equals("eof")) {
                    pushToOutput(line);
                    break;
                }
                shiftTitle(line);
            }
        }
    }
    
    private void shiftTitle(String line) {
        String[] data = line.split(" ");
        for (int i = 0; i < data.length; i++) {
            if (ignoreSet.contains(data[i].toLowerCase())) {
                continue;
            } else {
                String shiftedLine = buildTitleFromIthWord(data, i);
                pushToOutput(shiftedLine);
            }
        }
        
    }

    private String buildTitleFromIthWord(String[] data, int i) {
        StringBuilder sb = new StringBuilder();
        final int NUM_OF_WORDS = data.length;
        int count = 0;
        int index = i;
        while (count < NUM_OF_WORDS) {
            sb.append(data[index] + " ");
            index++;
            if (index >= NUM_OF_WORDS) {
                index = 0;
            }
            count++;
        }
        return sb.toString().trim();
    }

}
