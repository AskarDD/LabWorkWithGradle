
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonResources {
    private ConcurrentHashMap<String,String> dictionary;
    private static volatile boolean stop = false;
    private static String duplicate;
    private static String incorrect;

    public boolean getStop() { return stop; }
    public String getDuplicateWord() {return duplicate;}
    public String getIncorrectWord() {return incorrect;}
    public ConcurrentHashMap getDictionary () { return dictionary; }

    Pattern p = Pattern.compile("[^А-я0-9!?.,]");
    Matcher m;
    public void append(String line) {
        m = p.matcher(line);
        if (m.find()) {
            stop = true;
            incorrect = line;
            return;
        }
        if (dictionary.put(line,line) != null){
            stop = true;
            duplicate = line;
            return;
        }
    }

    public CommonResources() {
        dictionary = new ConcurrentHashMap<>();
    }

}
