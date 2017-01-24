package kwic.main;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kwic.filter.Alphabetizer;
import kwic.filter.CircularShift;
import kwic.filter.Input;
import kwic.filter.Output;
import kwic.pipe.Pipe;
import kwic.ui.TextUi;

public class Main {
    
    public static void main(String[] args) {
        
        TextUi textUi = new TextUi();
        
        textUi.displayTitle();
        HashSet<String> ignoreSet = textUi.getSetOfIgnoreWords();
        String filename = textUi.getFilenameOfInputTitles();
        textUi.closeScanner();
        textUi.printIgnoreSet(ignoreSet.toString());
        
        ExecutorService es = Executors.newFixedThreadPool(4);
        
        Pipe inputToCircularShiftConnector = new Pipe();
        Input input = new Input(null, inputToCircularShiftConnector, filename);
        
        Pipe circularShiftToAlphabetizerConnector = new Pipe();
        CircularShift circularShift = new CircularShift(inputToCircularShiftConnector, circularShiftToAlphabetizerConnector, ignoreSet);
        
        es.execute(input);
        es.execute(circularShift);
//        es.execute(new Alphabetizer());
//        es.execute(new Output());
        es.shutdown();
        
    }

}
