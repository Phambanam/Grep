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

    public void setCmdI() { this.cmdI = true;}
    public void setCmdR() { this.cmdR = true; }
    public void setCmdV() { this.cmdV = true; }


    private String word;
    final String fileName ="inputName.txt";

     Grep(String word,String fileName){
        this.word = word;
    }
    List<String> find() throws IOException{

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<String> result = new ArrayList<String>();
        String line  = br.readLine();
        boolean tOrF;
        while((line != null)){
        String regex = (this.cmdR) ?  word : (".*" + word + ".*") ;

        //https://www.geeksforgeeks.org/pattern-compilestring-method-in-java-with-examples/

        Pattern pattern = (this.cmdI) ? (Pattern.compile(regex,Pattern.CASE_INSENSITIVE)) : (Pattern.compile(regex));
        Matcher matcher = pattern.matcher(line);
        tOrF = matcher.matches();
        if(this.cmdV) tOrF = ! tOrF;
        if(tOrF) result.add(line);
        line = br.readLine();
        }
        return result;
    }
}
