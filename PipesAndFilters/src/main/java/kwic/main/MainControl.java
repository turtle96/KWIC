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

/**
 * System: Pipes and Filters.<br>
 * 
 * <p>
 * Represents the main control of the system. 
 * It creates filters, connects them with pipes, and initiate the running of all filters.
 * It also issues the command to shutdown the system.
 * </p>
 */
public class MainControl {
    
    private HashSet<String> ignoreSet;
    private FileReader inputFileReader;
    
    private Filter input;
    private Filter circularShift;
    private Filter alphabetizer;
    private Filter output;
    
    private ExecutorService es;
    
    /**
     * Shutdown the system.
     */
    private void shutdown() {
        es.shutdown();
    }
    
    /**
     * Force shutdown the system.
     */
    private void shutdownNow() {
        es.shutdownNow();
    }

    /**
     * Initiate the system.
     */
    private void init() {
        final int NUM_OF_FILTERS = 4;
        es = Executors.newFixedThreadPool(NUM_OF_FILTERS);
        es.execute(input);
        es.execute(circularShift);
        es.execute(alphabetizer);
        es.execute(output);
    }

    /**
     * Create filters and connect them with pipes to form a system.
     */
    private void createPipesAndFilters() {
        Pipe inputToCsConnector = new Pipe();
        input = new Input(null, inputToCsConnector, inputFileReader);
        
        Pipe csToAlphaConnector = new Pipe();
        circularShift = new CircularShift(inputToCsConnector, csToAlphaConnector, ignoreSet);
        
        Pipe alphaToOutputConnector = new Pipe();
        alphabetizer = new Alphabetizer(csToAlphaConnector, alphaToOutputConnector);
        
        output = new Output(alphaToOutputConnector, null);
    }

    /**
     * Get filenames of input data from the user.
     */
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
        MainControl mainControl = new MainControl();
        mainControl.getInputsFromUser();
        mainControl.createPipesAndFilters();
        try {
            mainControl.init();
            mainControl.shutdown();
        } catch (Exception e) {
            // catch any unhandled exceptions and shutdown all threads immediately
            mainControl.shutdownNow();
        }
    }

}
