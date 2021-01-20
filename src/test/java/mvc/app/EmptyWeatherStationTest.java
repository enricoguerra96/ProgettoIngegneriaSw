package mvc.app;
import org.junit.*;
import static org.junit.Assert.*;

public class EmptyWeatherStationTest {

    private WeatherStation station;

    @Before
    public void setup(){
        station = new WeatherStation();
    }

    @Test
    public void WeatherStationTest(){
        assertEquals("", station.getLocation());
        assertNull(station.getPressure());
        assertNull(station.getSunshine());
        assertNull(station.getRainfall());
        assertNull(station.getTemperature());
        assertNull(station.getWindSpeed());
        assertNull(station.getWindDirection());
    }

    @Test
    public void SetFaultsTest(){
        try{
            station.setFaults();
        }
        catch(ArrayIndexOutOfBoundsException out){
            fail();
            out.getMessage();
        }
    }

    @Test
    public void CollectMeasuresTest(){
        try{
            station.collectMeasures();
        }
        catch(ArrayIndexOutOfBoundsException out){
            fail();
            out.getMessage();
        }
    }
}