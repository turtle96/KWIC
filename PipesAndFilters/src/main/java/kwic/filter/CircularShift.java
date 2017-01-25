package kwic.filter;

import java.util.HashSet;

import kwic.model.Data;
import kwic.pipe.Pipe;

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
                sleepForXSeconds(1);
            } else {
                sleepForXSeconds(1);
                Data data = pullFromInput();
                System.out.println(getCurrentTime() + " CS pulled: " + data.getValue());
                if (data.isLast()) {
                    sendLastDataObjFlag();
                    System.out.println(getCurrentTime() + " CS pushed: last data");
                    break;
                }
                shiftTitle(data.getValue());
            }
        }
    }
    
    private void shiftTitle(String line) {
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (ignoreSet.contains(words[i].toLowerCase())) {
                continue;
            } else {
                String shiftedLine = buildTitleFromIthWord(words, i);
                pushToOutput(new Data(shiftedLine));
                System.out.println(getCurrentTime() + " CS pushed: " + shiftedLine);
                sleepForXSeconds(1);
            }
        }
        
    }

    private String buildTitleFromIthWord(String[] words, int i) {
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
