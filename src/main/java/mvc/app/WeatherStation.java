package mvc.app;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

@Entity
public class WeatherStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final String location;
    //private List<String> measures = new LinkedList<>();
    private String temperature="3";
    private String pression;
    private String rainfall;
    private String sunshine;

    private final DecimalFormat df = new DecimalFormat("#.##");
    private final String[] directions = { "N", "NW", "W", "SW", "S", "SE", "E", "NE" };

    public WeatherStation(String location){
        this.location=location;
        //collectMeasures();
    }

    private void collectMeasures(){
        this.temperature=(" °C");
       /* measures.clear();
        measures.add(df.format(Math.random() * 41) + " °C");
        measures.add(df.format(Math.random() * 1028) + " hPa");
        measures.add(df.format(Math.random() * 6.3) + " h/day");
        measures.add(df.format(Math.random() * 16) + " mm");
        measures.add(df.format(Math.random() * 36) + " km/h");
        int result = (int) (Math.random() * 8);
        measures.add(directions[result]);*/
    }

   /* public void collectTemperature(){
        int min=0;
        int max=40;
        measures.put(Measure.Temperature,df.format(Math.random() * (max - min + 1) + min) + " °C");
    }

    public void collectPressure(){
        int min=1020;
        int max=1027;
        measures.put(Measure.Pressure,df.format(Math.random() * (max - min + 1) + min) + " hPa");
    }

    public void collectSunshine(){
        double min=5.5;
        double max=6.3;
        measures.put(Measure.Sunshine,df.format(Math.random() * (max - min + 1) + min) + " h7/day");
    }

    public void collectRainfall(){
        int min=0;
        int max=15;
        measures.put(Measure.Rainfall,df.format(Math.random() * (max - min + 1) + min) + " mm");
    }

    public void collectWindSpeed(){
        int min=0;
        int max=35;
        measures.put(Measure.WindSpeed,df.format(Math.random() * (max - min + 1) + min) + " km/h");
    }

    public void collectWindDirection(){
        int min=0;
        int max=7;
        int result= (int) (Math.random() * (max - min + 1)) + min;
        measures.put(Measure.WindDirection, directions[result]);
    }
*/
}