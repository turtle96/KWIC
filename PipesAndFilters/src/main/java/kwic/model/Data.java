package kwic.model;

public class Data implements Comparable<String> {

    private String value;
    private boolean isLast;
    
    public Data(String s) {
        value = s;
        isLast = false;
    }
    
    public Data(String s, boolean flag) {
        value = s;
        isLast = flag;
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean isLast() {
        return isLast;
    }
    
    @Override
    public String toString() {
        String s = "[" + value + ", " + isLast + "]";
        return s;
    }

    @Override
    public int compareTo(String s) {
        return value.compareTo(s);
    }
    
    public static Data createEndOfDataObj() {
        return new Data("eof", true);
    }
    
}
