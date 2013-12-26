package core;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: jicui
 * Date: 13-12-26
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
public class OneManBand implements Performer{
    public OneManBand() {
    }

    @Override
    public void perform() throws PerformanceException {
        for(Instrument instrument:instruments){
            instrument.play();
        }
    }

    private Collection<Instrument> instruments;

    public void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }
}
