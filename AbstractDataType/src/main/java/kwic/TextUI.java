package kwic;

/** Class to handle text UI when printing to console
 * */
public class TextUI {
    private static final String DECORATOR = "\n============================================================================\n";
    private static final String WELCOME_MESSAGE = "                        Welcome to KWIC Index System";
    
    private static final String ENTER_WORDS_TO_IGNORE = "Enter words to ignore:";
    private static final String ENTER_WORDS_TO_IGNORE_MESSAGE_01 = "Each word should be separated by a space";
    private static final String ENTER_WORDS_TO_IGNORE_MESSAGE_02 = "Press 'Enter' when you are done";
    
    private static final String ENTER_TITLES = "Enter titles to index:";
    private static final String ENTER_TITLES_MESSAGE_01 = "For multiple titles, press 'Enter' after each title";
    private static final String ENTER_TITLES_MESSAGE_02 = "Type 'end' and press 'Enter' when you are done";
    
    private static final String INDEX_OUTPUT_MESSAGE = "Your KWIC-indexed titles:";
    
    private static final String BULLET_POINT = "\n * ";
    
    public static void printWelcomeMessage() {
        StringBuilder message = new StringBuilder();
        message.append(DECORATOR);
        message.append(WELCOME_MESSAGE);
        message.append(DECORATOR);
        
        System.out.println(message);
    }
    
    public static void printEnterWordsToIgnoreMessage() {
        StringBuilder message = new StringBuilder();

        message.append(ENTER_WORDS_TO_IGNORE);
        message.append(BULLET_POINT + ENTER_WORDS_TO_IGNORE_MESSAGE_01);
        message.append(BULLET_POINT + ENTER_WORDS_TO_IGNORE_MESSAGE_02);
        
        System.out.println(message);
    }
    
    public static void printEnterTitlesMessage() {
        StringBuilder message = new StringBuilder();
        message.append(DECORATOR + "\n");
        message.append(ENTER_TITLES);
        message.append(BULLET_POINT + ENTER_TITLES_MESSAGE_01);
        message.append(BULLET_POINT + ENTER_TITLES_MESSAGE_02);
        
        System.out.println(message);
    }
    
    public static void printIndexOutputMessage() {
        System.out.println(DECORATOR + INDEX_OUTPUT_MESSAGE + DECORATOR);
    }
}
