import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean cmdI = false;
    private boolean cmdR = false;
    private boolean cmdV = false ;



    private String word, fileName ;

    public Grep(String word,String fileName){
        this.word = word;
        this.fileName = fileName;
    }

    public void setCmdI() { this.cmdI = true;}
    public void setCmdR() { this.cmdR = true; }
    public void setCmdV() { this.cmdV = true; }

    public List<String> find() throws IOException{
        List<String> result = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line  = br.readLine();
            boolean tOrF;
            StringBuilder w = new StringBuilder();
            boolean trOf = false;

            //specialCharacter"!@#$%^&\\*(),.?\":{}|<>"
            String special ="!@#$%^&\\*(),.?\":{}|<>";

            for (int j = 0; j < word.length(); j++) {
                StringBuilder t = new StringBuilder();
                t.append(word.charAt(j));
                if(special.contains(t)) {
                    trOf = true;
                    w.append("\\").append(word.charAt(j));
                } else w.append(word.charAt(j));
            }

            while((line != null)){
                String regex;
                if(trOf)   regex =   (this.cmdR) ? word : (".*" + w.toString() + ".*");
                else regex = (this.cmdR) ?  word : (".*" + word + ".*") ;
                //https://www.geeksforgeeks.org/pattern-compilestring-method-in-java-with-examples/

                Pattern pattern = (this.cmdI) ? (Pattern.compile(regex,Pattern.CASE_INSENSITIVE))
                        : (Pattern.compile(regex));
                Matcher matcher = pattern.matcher(line);
                tOrF = matcher.matches();
                if(this.cmdV) tOrF = ! tOrF;
                if(tOrF) result.add(line);
                line = br.readLine();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
