package mvc.app;
import org.junit.Test;

import static org.junit.Assert.fail;

public class WeatherStationTest {

    @Test
    public void StationTest(){
        try {
            WeatherStation station = new WeatherStation("Rome");
        }
        catch(ArrayIndexOutOfBoundsException out){
            fail();
            out.getMessage();
        }
    }
}
