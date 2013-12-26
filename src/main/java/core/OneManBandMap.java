package core;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jicui
 * Date: 13-12-26
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class OneManBandMap implements Performer {
    public OneManBandMap() {
    }
    private Map<String,Instrument> instruments;
    @Override
    public void perform() throws PerformanceException {
        for(String key:instruments.keySet()){
            System.out.println(key+":");
            Instrument instrument=instruments.get(key);
            instrument.play();
        }
    }

    public Map<String, Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Map<String, Instrument> instruments) {
        this.instruments = instruments;
    }
}
