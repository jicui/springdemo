import com.beust.jcommander.*;
import com.beust.jcommander.validators.PositiveInteger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by jicui on 10/26/14.
 */
@Test
public class JCommandTest {
    @Parameters(commandDescription = "Happy Mock Command Line")
    private static class HappyMockCommand{
        @Parameter(names = {"-h","--help"}, help = true)
        private boolean help;
    }


    @Parameters(commandDescription = "start mock server")
    private static class CommandStart{
        @Parameter(names = {"-s","-ssl","--https"},description="mock server start as https server")
        protected boolean ssl=false;
        @Parameter(names = {"-p","--port"}, description="mock server http port",validateWith = PositiveInteger.class)
        protected int port=6666;
        @Parameter(names = {"-f","--file"}, description="specific a path to the mock specs")
        protected File fileAdaptor;
        @Parameter(names = {"-m","--mongo"}, description="specific a path to mongoDB connect properties")
        protected File mongoAdaptor;
        @Parameter(names = {"-t","--track"}, description="mock server log level,support 'INFO,DEBUG,ERROR,TRACE'" ,validateWith = LOGLevelValidator.class)
        protected String track="INFO";
        @Parameter(names = {"-k","--key"}, description="specific the auth token to stop the mock instance",required = true)
        protected String authToken;
        @Parameter(names = {"-h","--help"}, help = true)
        private boolean help;

    }

    @Parameters(commandDescription = "stop mock server")
    private static class CommandStop{
        @Parameter(names = {"-k","--key"}, description="specific the auth token to stop the mock instance",required = true)
        protected String authToken;
    }


    public static class LOGLevelValidator implements IParameterValidator {

        @Override
        public void validate(String name, String value) throws ParameterException {
            if(!value.toUpperCase().equals("INFO")&&!value.toUpperCase().equals("DEBUG")&&!value.toUpperCase().equals("ERROR")&&!value.toUpperCase().equals("TRACE")){
                throw new ParameterException("Parameter " + name + " should be valid log level (found " + value +"),support 'debug,info,error,trace'");
            }
        }
    }

    @Test
    void test_start_parse(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);

        jc.parse("start", "-ssl", "-p", "8888", "-f", "/opt/env/mock.specs", "--key", "mockany", "-t", "DEBUG");
        Assert.assertEquals(jc.getParsedCommand(), "start");
        Assert.assertEquals(commandStart.port,8888);
        Assert.assertEquals(commandStart.fileAdaptor,new File("/opt/env/mock.specs"));
        Assert.assertEquals(commandStart.track,"DEBUG");
    }

    @Test
    void test_start_parse_fail_in_negative_port(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);

        try{
            jc.parse("start", "-ssl", "-p", "-6666", "-f", "/opt/env/mock.specs", "--key", "mockany","-t","DEBUG");
        }catch(ParameterException p){
            Assert.assertEquals(p.getMessage(),"Parameter -p should be positive (found -6666)");
        }
    }

    @Test
    void test_start_parse_fail_in_invalid_track(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);

        try{
            jc.parse("start", "-ssl", "-p", "6666", "-f", "/opt/env/mock.specs", "--key", "mockany","-t","HALO");
        }catch(ParameterException p){
            Assert.assertEquals(p.getMessage(),"Parameter -t should be valid log level (found HALO),support 'debug,info,error,trace'");
        }
    }

    @Test
    void test_stop_parse(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);
        jc.parse("stop", "-k", "mockany");
        Assert.assertEquals(jc.getParsedCommand(), "stop");
        Assert.assertEquals(commandStop.authToken,"mockany");
    }

    @Test
    void test_help_parse(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        jc.setProgramName("java -jar happmock_happywork_1.0_snapshot.jar");
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);
        jc.parse("--help");
        jc.usage();
        Assert.assertTrue(happyMockCommand.help);
    }

    @Test
    void test_generate_usage_for_start_parse(){
        HappyMockCommand happyMockCommand=new HappyMockCommand();
        JCommander jc = new JCommander(happyMockCommand);
        jc.setProgramName("happmock_happywork_1.0_snapshot.jar");
        CommandStart commandStart=new CommandStart();
        CommandStop commandStop=new CommandStop();
        jc.addCommand("start",commandStart);
        jc.addCommand("stop",commandStop);
        jc.parse("start", "-h");
        Assert.assertEquals(jc.getParsedCommand(), "start");
        jc.usage("start");

    }

}
