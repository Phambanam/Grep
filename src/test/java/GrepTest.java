
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GrepTest {


    @org.junit.jupiter.api.Test
    public void find() throws IOException {
        //   Вывод, содержащий указанное слово

        Grep grep = new Grep("I’m", "inputName.txt");
        List<String> Word = new ArrayList<String>();
        Word.add("I’m a generous and easy-going person but when it comes to work , a competitive perfectionist.");
        Word.add("I’m also an optimistic and outgoing person so I have many friends and other social relationships. ");
        assertEquals(grep.find(),Word);

        //Вывод строк, содержащих указанное слово, игнорировать регистр слов
        Grep grep1 = new Grep("HavE","inputName.txt");
        List<String> IgnoreCase = new ArrayList<String>();
        grep1.setCmdI();
        IgnoreCase.add("I have a big family with six people. I have two sisters and a brother. My sisters are older and my brother is younger than me. ");
        IgnoreCase.add("I’m also an optimistic and outgoing person so I have many friends and other social relationships. ");
        IgnoreCase.add("I enjoy reading, writing and doing math. I decided to become a Civil Engineer because I have always been fascinated by bridges, buildings, and skyscrapers.");
        assertEquals(grep1.find(),IgnoreCase);

        //Вывод строк, содержащих указанное регулярное выражение
        Grep grep2 = new Grep(".*(I’m).*|.*(father).*", "inputName.txt");
        List<String> regex = new ArrayList<String>();
        grep2.setCmdR();
        regex.add("My father is a teacher at a secondary school. He has worked for 35 years in the field and he is my biggest role model in life. My mother is a housewife.");
        regex.add("I’m a generous and easy-going person but when it comes to work , a competitive perfectionist.");
        regex.add("I’m also an optimistic and outgoing person so I have many friends and other social relationships. ");
        assertEquals(grep2.find(),regex);

        // Вывод строк, не содержащих указанное слово
        Grep grep3 = new Grep("I","inputName.txt");
        grep3.setCmdV();
        List<String> inverts = new ArrayList<String>();
        inverts.add("My father is a teacher at a secondary school. He has worked for 35 years in the field and he is my biggest role model in life. My mother is a housewife.");
        inverts.add("A degree in Civil Engineering enables me to achieve my goals and also gives me an opportunity to make a difference in the community.");
        assertEquals(grep3.find(),inverts);

    }

}