import org.testng.Assert;
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
        //String str="this is a #test# and  #json['$.store.book[0].author']#";
        String exp="#(json|xml|header|cookie)\\['[^#]+'\\]#?";
        Pattern pattern= Pattern.compile(exp);
        Matcher matcher=pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
           String s=matcher.group();
           matcher.appendReplacement(sb, "dog");
        }
        matcher.appendTail(sb);
        Assert.assertEquals("this is a #test# and  dog with dog",sb.toString());
    }
}
