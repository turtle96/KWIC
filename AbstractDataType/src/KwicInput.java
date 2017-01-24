import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class KwicInput implements Input {
    
    private ArrayList<ArrayList<String>> titles;
    private ArrayList<String> wordsToIgnore;
    
    private static final Logger LOGGER = Logger.getLogger(KwicInput.class.getName());

    @Override
    public void readInput() {
        Scanner scanner = new Scanner(System.in);

        readWordsToIgnore(scanner);
        readTitles(scanner);
        
        scanner.close();
    }

    private void readTitles(Scanner scanner) {
        System.out.println("Enter titles to index (press enter after each title, and type 'end' to finish): ");
        titles = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("end")) {
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
        System.out.println("Enter words to ignore (in a single line with each word separated by a space):");
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
