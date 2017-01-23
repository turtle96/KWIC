import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Logger;

public class KwicInput implements Input {
    
    private ArrayList<ArrayList<String>> titles;
    private ArrayList<String> wordsToIgnore;
    
    private static final Logger LOGGER = Logger.getLogger(KwicInput.class.getName());

    @Override
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        
        //TODO exception handling
        System.out.println("Enter words to ignore (in a single line with each word separated by a space):");
        wordsToIgnore = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        wordsToIgnore.removeAll(Arrays.asList("", " "));
        
        LOGGER.info("Words to ignore: " + wordsToIgnore.toString());
        
        System.out.println("Enter titles to index (press enter after each title, and type 'end' to finish): ");
        ArrayList<String> titles = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            titles.add(input);
        }
        
        LOGGER.info("Titles: " + titles.toString());
        
        scanner.close();
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
