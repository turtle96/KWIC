package kwic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class InputTest extends KwicInput {
    
    private static final String FILEPATH_INPUT_01 = "src\\test\\files\\input01.txt";
    private static final String FILEPATH_INPUT_02 = "src\\test\\files\\input02.txt";
    
    @Test
    public void readTitles_shouldParseInputCorrectly() throws FileNotFoundException {
        Scanner scanner = new Scanner(getFile(FILEPATH_INPUT_01));
        Input input = new KwicInput();
        input.readInput(scanner);
        
        ArrayList<ArrayList<String>> expected = prepareExpectedTitles(
                "The Day after Tomorrow", "Fast and Furious", "Man of Steel");

        assert input.getTitles().equals(expected);
    }
    
    @Test
    public void readTitles_shouldIgnoreWhiteSpaces() throws FileNotFoundException {
        Scanner scanner = new Scanner(getFile(FILEPATH_INPUT_02));
        Input input = new KwicInput();
        input.readInput(scanner);
       
        ArrayList<ArrayList<String>> expected = prepareExpectedTitles(
                "The Day after Tomorrow", "Fast and Furious", "Man of Steel");

        assert input.getTitles().equals(expected);
    }

    private ArrayList<ArrayList<String>> prepareExpectedTitles(String... strings) {
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        ArrayList<String> expectedLines = new ArrayList<>(Arrays.asList(strings)); 
        for (String line: expectedLines) {
            expected.add(new ArrayList<>(Arrays.asList(line.split(" "))));
        }
        return expected;
    }
    
    private File getFile(String filepath) {
        File file = new File(filepath);
        return file;
    }

}
