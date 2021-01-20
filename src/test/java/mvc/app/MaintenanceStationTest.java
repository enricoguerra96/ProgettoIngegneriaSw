package mvc.app;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class MaintenanceStationTest {

    private MaintenanceStation manstation;
    private WeatherStation station;
    private LinkedList<String> result;

    @Before
    public void setup(){
        station = new WeatherStation("Rome");
        manstation = new MaintenanceStation(station);
        result = new LinkedList<>();
    }

    @After
    public void clean(){
        result.clear();
    }

    @Test
    public void MaintenanceConstructorListTest(){
        try {
            LinkedList<WeatherStation> stations = new LinkedList<>();
            stations.add(new WeatherStation("Rome"));
            stations.add(new WeatherStation("Turin"));
            manstation = new MaintenanceStation(stations);
        }
        catch(Exception e){ fail(); }
    }

    @Test
    public void MaintenanceConstructorTest(){
        try {
            manstation = new MaintenanceStation(station);
        }
        catch(Exception e){ fail(); }
    }

    @Test
    public void PeriodicalMeasureFormatTest(){
        try {
            manstation.PeriodicalMeasureCheck(station, result);
        }
        catch(NumberFormatException num){
            fail();
            num.getMessage();
        }
    }

    @Test
    public void PeriodicalMeasureIndexTest(){
        try {
            manstation.PeriodicalMeasureCheck(station, result);
        }
        catch(IndexOutOfBoundsException ind){
            fail();
            ind.getMessage();
        }
    }

    @Test
    public void PeriodicalMeasureResultOKTest(){
        manstation.PeriodicalMeasureCheck(station, result);
        assertTrue(result.contains("Temperature is NORMAL."));
    }

    @Test
    public void PeriodicalMeasureResultNotOKTest(){
        station.setTemperature("-100 °C");
        station.setPressure("-1000 °hPa");
        station.setRainfall("1000 mm");
        station.setSunshine("-100 h/day");
        station.setWindSpeed("-100 km/h");

        manstation.PeriodicalMeasureCheck(station, result);
        assertTrue(result.contains("Temperature is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Pressure is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Rainfall is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Sunshine is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Wind speed is IRREGULAR. Check for hardware problems."));
    }

    @Test
    public void PeriodicalMeasureResultNotOKPlusTest(){
        station.setTemperature("1000 °C");
        station.setPressure("10000 °hPa");
        station.setRainfall("1000 mm");
        station.setSunshine("1000 h/day");
        station.setWindSpeed("1000 km/h");

        manstation.PeriodicalMeasureCheck(station, result);
        assertTrue(result.contains("Temperature is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Pressure is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Rainfall is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Sunshine is IRREGULAR. Check for hardware problems."));
        assertTrue(result.contains("Wind speed is IRREGULAR. Check for hardware problems."));
    }


    @Test
    public void PeriodicalHealthEmptyTest() {
        WeatherStation emptystation = new WeatherStation();
        manstation.PeriodicalHealthCheck(emptystation, result);
        assertEquals(result.get(2), "Location not found. Communication with station failed.");
    }

    @Test
    public void getResultTest(){
        LinkedList<String> result = manstation.getResult();
        assertFalse(result.isEmpty());
    }
}