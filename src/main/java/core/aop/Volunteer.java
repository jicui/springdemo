package core.aop;/** * Created with IntelliJ IDEA. * User: jicui * Date: 12/29/13 * Time: 6:02 PM * To change this template use File | Settings | File Templates. */public class Volunteer implements Thinker {    private String thoughts;    public void thinkOfSomething(String thoughts) {        this.thoughts = thoughts;    }    public String getThoughts() {        return thoughts;    }}