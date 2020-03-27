import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class LauncherGrep {
 @Option(name = "-i",usage = "Ignore Case")
   private boolean iCase;

 @Option(name = "-r", usage = "Search with regex")
   private boolean regex;

 @Option(name = "-v", usage = " inverts the filter condition")
   private boolean inverts;

 @Argument( metaVar = "word", usage = "input argument")
   private  String word;

 @Argument( metaVar = "fileName", index = 1, usage = "input file name")
   private String fileName;

public static void main(String[] args){
   new LauncherGrep().launch(args);
}

   private void launch(String[] args) {
       CmdLineParser parser = new CmdLineParser(this);
      try{
         parser.parseArgument(args);
      } catch (CmdLineException e) {
        System.err.println(e.getMessage());
        System.err.println("grep  -i -r -v word inputName.txt");
        parser.printUsage(System.err);
        return;
      }

      Grep grep = new Grep(word,fileName);
      if(iCase) grep.setCmdI();
      if(regex) grep.setCmdR();
      if (inverts) grep.setCmdV();

      try{
         for (String line : grep.find()) System.out.println(line);
      }catch(IOException e){ e.printStackTrace(); }
   }
}
