package kwic.main;

import java.io.FileReader;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kwic.filter.Alphabetizer;
import kwic.filter.CircularShift;
import kwic.filter.Filter;
import kwic.filter.Input;
import kwic.filter.Output;
import kwic.pipe.Pipe;
import kwic.ui.TextUi;

public class Main {
    
    private HashSet<String> ignoreSet;
    private FileReader inputFileReader;
    
    private Filter input;
    private Filter circularShift;
    private Filter alphabetizer;
    private Filter output;
    
    private ExecutorService es;
    
    private void shutdown() {
        es.shutdown();
    }

    private void runFilters() {
        es = Executors.newFixedThreadPool(4);
        es.execute(input);
        es.execute(circularShift);
        es.execute(alphabetizer);
        es.execute(output);
    }

    private void createPipesAndFilters() {
        Pipe inputToCsConnector = new Pipe();
        input = new Input(null, inputToCsConnector, inputFileReader);
        
        Pipe csToAlphaConnector = new Pipe();
        circularShift = new CircularShift(inputToCsConnector, csToAlphaConnector, ignoreSet);
        
        Pipe alphaToOutputConnector = new Pipe();
        alphabetizer = new Alphabetizer(csToAlphaConnector, alphaToOutputConnector);
        
        output = new Output(alphaToOutputConnector, null);
    }

    private void getInputsFromUser() {
        TextUi textUi = new TextUi();

        textUi.displayTitle();
        textUi.initScanner();
        ignoreSet = textUi.getSetOfIgnoreWords();
        inputFileReader = textUi.getFileReader(TextUi.GET_TITLES_FILE_PROMPT);
        textUi.closeScanner();
        textUi.printIgnoreSet(ignoreSet.toString());
    }
    
    public static void main(String[] args) {
        Main mainControl = new Main();
        mainControl.getInputsFromUser();
        mainControl.createPipesAndFilters();
        mainControl.runFilters();
        mainControl.shutdown();
    }

}
