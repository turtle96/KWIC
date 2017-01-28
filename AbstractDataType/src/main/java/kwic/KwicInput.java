package kwic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Read user input and save titles and wordsToIgnore
 * */
public class KwicInput implements Input {
    
    private ArrayList<ArrayList<String>> titles;    //array of titles, each title array holds an array of words
    private ArrayList<String> wordsToIgnore;
    
    private static final Logger LOGGER = Logger.getLogger(KwicInput.class.getName());
    
    public KwicInput() {
        LOGGER.setLevel(Level.OFF);
    }
    
    @Override
    public void readInput(Scanner scanner) {
        TextUI.printEnterWordsToIgnoreMessage();
        readWordsToIgnore(scanner);
        
        TextUI.printEnterTitlesMessage();
        readTitles(scanner);
        
        scanner.close();
    }

    private void readTitles(Scanner scanner) {
        titles = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            else if (!input.trim().isEmpty()) {
            	ArrayList<String> toAdd = new ArrayList<>(Arrays.asList(input.split(" ")));
            	toAdd.removeAll(Arrays.asList("", " "));
                titles.add(toAdd);
            }
        }
        
        LOGGER.info("Titles: " + titles.toString());
    }

    private void readWordsToIgnore(Scanner scanner) {
        wordsToIgnore = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        wordsToIgnore.removeAll(Arrays.asList("", " "));
        
        LOGGER.info("Words to ignore: " + wordsToIgnore.toString());
    }

    @Override
    public ArrayList<ArrayList<String>> getTitles() {
        return titles;
    }

    @Override
    public ArrayList<String> getWordsToIgnore() {
        return wordsToIgnore;
    }

}
