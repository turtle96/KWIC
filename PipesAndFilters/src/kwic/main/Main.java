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
        
        Pipe inputToCsConnector = new Pipe();
        Input input = new Input(null, inputToCsConnector, filename);
        
        Pipe csToAlphaConnector = new Pipe();
        CircularShift circularShift = new CircularShift(inputToCsConnector, csToAlphaConnector, ignoreSet);
        
        Pipe alphaToOutputConnector = new Pipe();
        Alphabetizer alphabetizer = new Alphabetizer(csToAlphaConnector, alphaToOutputConnector);
        
        Output output = new Output(alphaToOutputConnector, null);
        
        ExecutorService es = Executors.newFixedThreadPool(4);
        
        es.execute(input);
        es.execute(circularShift);
        es.execute(alphabetizer);
        es.execute(output);
        es.shutdown();
        
    }

}
