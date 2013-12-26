package core;



/**
 * Created with IntelliJ IDEA.
 * User: jicui
 * Date: 13-12-26
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public class Instrumentalist implements Performer {
    public Instrumentalist(){}

    @Override
    public void perform() throws PerformanceException {
       System.out.print("playing "+song+" now..");
        instrument.play();
    }
    private String song;
    private Instrument instrument;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
