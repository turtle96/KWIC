package kwic.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kwic.filter.CircularShift;
import kwic.filter.Filter;
import kwic.model.Data;
import kwic.pipe.Pipe;

public class CircularShiftTest {

    private Filter circularShift;
    private ExecutorService es;
    private Pipe inputPipe, outputPipe;
    
    
    @Before
    public void setup() {
        inputPipe = new Pipe();
        outputPipe = new Pipe();
        circularShift = new CircularShift(inputPipe, outputPipe, generateIgnoreSet());
        
        es = Executors.newFixedThreadPool(1);
        es.execute(circularShift);
        es.shutdown();
    }
    
    @Test
    public void test_run() {
        inputPipe.insert(new Data("The Day After Tomorrow"));
        String[] expectedOutput = { "Day After Tomorrow The", "Tomorrow The Day After" };
        delay(); // time for filter to transform input
        ArrayList<String> output = new ArrayList<String>();
        while (!outputPipe.isEmpty()) {
            output.add(outputPipe.extract().getValue());
        }
        assertTrue("Number of transformed should be equal", expectedOutput.length == output.size());
        for (int i=0; i<output.size();i++) {
            assertTrue("Transformed output should be same", expectedOutput[i].equals(output.get(i)));
        }
    }
    
    private HashSet<String> generateIgnoreSet() {
        HashSet<String> ignoreSet = new HashSet<String>();
        ignoreSet.add("the");
        ignoreSet.add("after");
        return ignoreSet;
    }
    
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @After
    public void shutdown() {
        inputPipe.insert(Data.createEndOfDataObj());
    }
    
    
}
