package kwic.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kwic.filter.Alphabetizer;
import kwic.filter.CircularShift;
import kwic.filter.Input;
import kwic.filter.Output;
import kwic.pipe.Pipe;

public class Main {
    
    public static void main(String[] args) {
        
        ExecutorService es = Executors.newFixedThreadPool(4);
        
        Pipe inputToCircularShiftConnector = new Pipe();
        Input input = new Input(null, inputToCircularShiftConnector, "input.txt");
        
        Pipe circularShiftToAlphabetizerConnector = new Pipe();
        CircularShift circularShift = new CircularShift(inputToCircularShiftConnector, circularShiftToAlphabetizerConnector);
        
        es.execute(input);
        es.execute(circularShift);
//        es.execute(new Alphabetizer());
//        es.execute(new Output());
        es.shutdown();
        
    }

}
