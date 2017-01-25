package kwic.test.ui;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import kwic.ui.TextUi;

public class TextUiTest {

    private String validTestFileName = "src/test/data/testFile.txt";
    private String invalidTestFileName = "src/test/data/missingFile.txt";
    private TextUi ui;
    
    
    @Before
    public void setup() {
        ui = new TextUi();
        
    }
    
    @Test
    public void getFileReader_validIgnoreTestFileReturnsNotNull_assertTrue() {
        ui.initScanner(validTestFileName);
        FileReader fr = ui.getFileReader("prompt for test");
        ui.closeScanner();
        assertTrue("FileReader should not be null", fr != null);
    }
    
    @Test
    public void getFileReader_invalidIgnoreTestFileReturnsNull_assertTrue() {
        ui.initScanner(invalidTestFileName);
        FileReader fr = ui.getFileReader("prompt for test");
        ui.closeScanner();
        assertTrue("FileReader should not be null", fr == null);
    }
    
}
