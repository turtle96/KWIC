package kwic.model;

public class Data implements Comparable<String> {

    private String value;
    private boolean isLast;
    
    public Data(String s, boolean flag) {
        value = s;
        isLast = flag;
    }
    
    public boolean isLast() {
        return isLast;
    }
    
    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(String s) {
        return value.compareTo(s);
    }
    
    public static Data createEndOfDataObj() {
        return new Data("eof", true);
    }
    
}
