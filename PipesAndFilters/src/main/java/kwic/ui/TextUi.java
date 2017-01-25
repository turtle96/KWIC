package kwic.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class TextUi {
    
    final private static String GET_IGNORE_FILENAME_PROMPT = "Enter filename of the list of words to ignore: ";
    final private static String GET_TITLES_FILENAME_PROMPT = "Enter filename of the list of input titles: ";
    
    private Scanner sc;
    
    public TextUi() {}
    
    public void initScanner() {
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

    /**
     * Reads the list of words to ignore from a file and return a HashSet containing that list
     */
    public HashSet<String> getSetOfIgnoreWords() {
        HashSet<String> ignoreSet = new HashSet<String>();
        FileReader fr = getFileReader(GET_IGNORE_FILENAME_PROMPT);
        BufferedReader br = new BufferedReader(fr);

        try {
            String word = br.readLine();
            while (word != null) {
                ignoreSet.add(word.toLowerCase());
                word = br.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error occurred while reading file containing list of words to ignore");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unrecoverable error from TextUi::getSetOfIgnoreWords()");
            }
        }
        
        return ignoreSet;
    }
    
    /**
     * Returns the FileReader object of the input file
     */
    public FileReader getFileReaderOfInputTitles() {
        FileReader fr = getFileReader(GET_TITLES_FILENAME_PROMPT);
        return fr;
    }
    
    /**
     * Print to console the list of words to ignore
     */
    public void printIgnoreSet(String s) {
        System.out.println("\nThe list of words to ignore are: \n" + s);
        
    }
    
    /**
     * Asks the user for a valid file name and returns a FileReader object of that file
     */
    private FileReader getFileReader(String prompt) {
        FileReader fr = null;
        try {
            do {
                String fileName = getFileName(prompt);
                fr = new FileReader(fileName);
            } while (fr == null);
        } catch (FileNotFoundException fnfe) {
            System.out.println("The file name you have entered is missing or invalid.");
            fr = null;
        }
        
        return fr;
    }
    
    /**
     * Returns the file name entered by the user
     */
    private String getFileName(String prompt) throws FileNotFoundException {
        String filename = "";
        
        System.out.println(prompt);
        if (sc.hasNextLine()) {
            filename = sc.nextLine();
        }
        if (filename.isEmpty()) {
            throw new FileNotFoundException();
        }
        
        return filename;
    }

}
