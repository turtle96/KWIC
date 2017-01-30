package kwic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/** Implements Output interface to display indexed list and write output to file
 * */
public class KwicOutput implements Output {

    private static final Logger LOGGER = Logger.getLogger(KwicOutput.class.getName());
    
	public static final String FILEPATH = ".\\output\\";
	public static final String OUTPUT_FILENAME = "output.txt";

    @Override
	public void displayIndexAndWriteToFile(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses) {
	    TextUI.printIndexOutputMessage();
	    
		String index = printIndex(titles, addresses);
		
		writeToFile(index);
	}

    private void writeToFile(String index) {
        
        try {
            Files.write(Paths.get(FILEPATH + OUTPUT_FILENAME), index.getBytes());
            TextUI.printOutputSavedMessage();
        } catch (IOException e) {
            LOGGER.warning("File write error");
        }
    }

    private String printIndex(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses) {
        StringBuilder index = new StringBuilder();
        
        for (Address address: addresses) {
			ArrayList<String> title = titles.get(address.getLineIndex());
			int titleLength = title.size();
			int startIndex = address.getStartIndex();
			
            String displayTitle = title.get(startIndex);
			int i = (startIndex + 1) % titleLength;
			
			while (i != startIndex) {
				displayTitle = displayTitle + " " + title.get(i);
				i++;
				if (i==titleLength) {
                    i %= titleLength;
                }
			}
			
			System.out.println(displayTitle.trim());
			
			index.append(displayTitle.trim() + "\r\n");  //for some reason \n cannot be written to file
			                                             //so \r\n is used instead
        }

        return index.toString();
    }

}
