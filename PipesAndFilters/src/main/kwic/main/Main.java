package main.kwic.main;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.kwic.filter.Alphabetizer;
import main.kwic.filter.CircularShift;
import main.kwic.filter.Filter;
import main.kwic.filter.Input;
import main.kwic.filter.Output;
import main.kwic.pipe.Pipe;
import main.kwic.ui.TextUi;

public class Main {
    
    public static void main(String[] args) {
        
        TextUi textUi = new TextUi();
        
        textUi.displayTitle();
        HashSet<String> ignoreSet = textUi.getSetOfIgnoreWords();
        String filename = textUi.getFileReaderOfInputTitles();
        textUi.closeScanner();
        textUi.printIgnoreSet(ignoreSet.toString());
        
        Pipe inputToCsConnector = new Pipe();
        Filter input = new Input(null, inputToCsConnector, filename);
        
        Pipe csToAlphaConnector = new Pipe();
        Filter circularShift = new CircularShift(inputToCsConnector, csToAlphaConnector, ignoreSet);
        
        Pipe alphaToOutputConnector = new Pipe();
        Filter alphabetizer = new Alphabetizer(csToAlphaConnector, alphaToOutputConnector);
        
        Filter output = new Output(alphaToOutputConnector, null);
        
        ExecutorService es = Executors.newFixedThreadPool(4);
        
        es.execute(input);
        es.execute(circularShift);
        es.execute(alphabetizer);
        es.execute(output);
        es.shutdown();
        
    }

}
