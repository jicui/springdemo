import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jicui on 10/19/14.
 */
@Test
public class RegexReplacementTest
{

    @Test
    public void test_extract(){
    String str="this is a #test# and  #json['$.store.book[0].author']# with #xml['//bookstore/book[author=\"Erik T. Ray\"]/title/text()']#";
        String exp="#(json|xml|header|cookie)\\[.*\\]#?";
        Pattern pattern= Pattern.compile(exp);
        Matcher matcher=pattern.matcher(str);
        while(matcher.find()){
           String s=matcher.group();
           System.out.println(s+" start="+matcher.start()+" end="+matcher.end());
        }

    }
}
