import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean cmdI ;
    private boolean cmdR ;
    private boolean cmdV ;
    private String word, fileName;

    public Grep(boolean cmdI, boolean cmdR, boolean cmdV, String word, String fileName) {
        this.cmdI = cmdI;
        this.cmdR = cmdR;
        this.cmdV = cmdV;
        this.word = word;
        this.fileName = fileName;
    }

    public List<String> find() throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            boolean tOrF;

            while ((line != null)) {
                String regex;
                 regex = (this.cmdR) ? word : ( Pattern.quote(word) );

                //https://www.geeksforgeeks.org/pattern-compilestring-method-in-java-with-examples/

                Pattern pattern = (this.cmdI) ? (Pattern.compile(regex, Pattern.CASE_INSENSITIVE))
                        : (Pattern.compile(regex));
                Matcher matcher = pattern.matcher(line);
                tOrF = matcher.find();
                if (this.cmdV) tOrF = !tOrF;
                if (tOrF) result.add(line);
                line = br.readLine();
            }
            return result;
        }
    }
}
