package kwic.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class TextUi {
    
    final private static String GET_IGNORE_FILENAME_PROMPT = "Enter filename of the list of words to ignore: ";
    final private static String GET_TITLES_FILENAME_PROMPT = "Enter filename of the list of input titles: ";
    
    private Scanner sc;
    
    public TextUi() {
        sc = new Scanner(System.in);
    }
    
    public void closeScanner() {
        sc.close();
    }

    public void displayTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------------------------------------------\n");
        sb.append("                              KWIC Index System\n");
        sb.append("---------------------------------------------------------------------------\n");
        System.out.println(sb.toString());
    }

    public HashSet<String> getSetOfIgnoreWords() {
        HashSet<String> ignoreSet = new HashSet<String>();
        String filename = getFilename(GET_IGNORE_FILENAME_PROMPT);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String word = br.readLine();
            while (word != null) {
                ignoreSet.add(word.toLowerCase());
                word = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to read " + filename);
        }
        
        return ignoreSet;
    }
    
    public String getFilenameOfInputTitles() {
        return getFilename(GET_TITLES_FILENAME_PROMPT);
    }
    
    public void printIgnoreSet(String s) {
        System.out.println("\nThe list of words to ignore are: " + s);
        
    }
    
    private String getFilename(String prompt) {
        String filename = "";
        
        do {
            System.out.println(prompt);
            if (sc.hasNextLine()) {
                filename = sc.nextLine();
            }
            if (isFilenameValid(filename)) {
                break;
            } else {
                System.out.println("The filename that you have entered does not exist.");
            }
        } while(true);

        return filename;
    }

    private boolean isFilenameValid(String filename) {
        File file = new File(filename);
        
        return file.exists() && file.isFile();
    }

}
