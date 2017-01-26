package kwic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class InputTest extends KwicInput {
    
    private static final String WORDS_TO_IGNORE = "is the of and as a after";
    private static final String FILEPATH_INPUT_01 = "src\\test\\files\\input01.txt";
    private static final String FILEPATH_INPUT_02 = "src\\test\\files\\input02.txt";
    private static final String FILEPATH_INPUT_03 = "src\\test\\files\\input03.txt";
    
    @Test
    public void readWordsToIgnore_shouldParseInputCorrectly() throws FileNotFoundException {
        Input input = executeReadInput(FILEPATH_INPUT_01);
        
        ArrayList<String> expected = prepareExpectedWords(WORDS_TO_IGNORE);

        assert input.getWordsToIgnore().equals(expected);
    }
    
    @Test
    public void readWordsToIgnore_shouldIgnoreWhiteSpaces() throws FileNotFoundException {
        Input input = executeReadInput(FILEPATH_INPUT_02);
        
        ArrayList<String> expected = prepareExpectedWords(WORDS_TO_IGNORE);

        assert input.getWordsToIgnore().equals(expected);
    }

    @Test
    public void readTitles_shouldParseInputCorrectly() throws FileNotFoundException {
        Input input = executeReadInput(FILEPATH_INPUT_01);
        
        ArrayList<ArrayList<String>> expected = prepareExpectedTitles(
                "The Day after Tomorrow", "Fast and Furious", "Man of Steel");

        assert input.getTitles().equals(expected);
    }
    
    @Test
    public void readTitles_shouldIgnoreWhiteSpaces() throws FileNotFoundException {
        Input input = executeReadInput(FILEPATH_INPUT_02);
       
        ArrayList<ArrayList<String>> expected = prepareExpectedTitles(
                "The Day after Tomorrow", "Fast and Furious", "Man of Steel");

        assert input.getTitles().equals(expected);
    }
    
    @Test
    /** ensures read input terminates correctly at the line with only "end" and
     *  not at lines containing the word "end"
     * */
    public void readTitles_shouldTerminateReadInputAtEnd() throws FileNotFoundException {
        Input input = executeReadInput(FILEPATH_INPUT_03);
        
        ArrayList<ArrayList<String>> expected = prepareExpectedTitles(
                "The Day after Tomorrow", "Fast and Furious", "Man of Steel",
                "end of the end", "something else", "the mummy");

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
    
    private ArrayList<String> prepareExpectedWords(String string) {
        return new ArrayList<>(Arrays.asList(string.split(" ")));
    }
    
    private File getFile(String filepath) {
        File file = new File(filepath);
        return file;
    }
    
    private Input executeReadInput(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(getFile(filepath));
        Input input = new KwicInput();
        input.readInput(scanner);
        return input;
    }

}
