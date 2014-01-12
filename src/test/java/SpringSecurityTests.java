import dbaccess.Spitter;import junit.framework.Assert;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.test.context.ActiveProfiles;import org.springframework.test.context.ContextConfiguration;import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;import org.testng.annotations.Test;import secure.SecuredObject;/** * Created with IntelliJ IDEA. * User: jicui * Date: 1/11/14 * Time: 7:45 PM * To change this template use File | Settings | File Templates. */@ContextConfiguration("/security/security.xml")@ActiveProfiles("dev")public class SpringSecurityTests extends AbstractTestNGSpringContextTests {    @Autowired    private SecuredObject secured;    @Test    public void testSecurity(){        Spitter spitter = new Spitter();        spitter.setId(1L);        spitter.setEmail("jicui@ebay.com");        spitter.setFullName("Jason Cui");        spitter.setUserName("jicui");        spitter.setPassword("test123");        try{            secured.addSpitter(spitter);//throw exception as there are no authentication to the access        } catch (Exception e){            Assert.assertTrue(true);        }    }}