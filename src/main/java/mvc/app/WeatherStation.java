package mvc.app;
import javax.persistence.*;

@Entity
public class WeatherStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String location;
    private String temperature;
    private String pressure;
    private String sunshine;
    private String rainfall;
    private String windSpeed;
    private String windDirection;

    public final String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    private final boolean[] faultprob = {true, true, true, true, false, true, true, true, true, true};
    private boolean batteryState;
    private boolean hardwareState;
    private boolean signalState;

    protected WeatherStation(){
        this.location="";
    }

    public WeatherStation(String location){
        this.location=location;
        collectMeasures();
        setFaults();
    }

    public Long getId() { return id; }

    public String getLocation() {
        return location;
    }

    void collectMeasures(){
        this.temperature=(Math.round(Math.random() * 41)) + " °C";
        this.pressure=(Math.round(Math.random() * 1028)) + " °hPa";
        this.sunshine=(Math.round(Math.random() * 6.3)) + " h/day";
        this.rainfall=(Math.round(Math.random() * 16)) + " mm";
        this.windSpeed=(Math.round(Math.random() * 36)) + " km/h";

        int result = (int) (Math.random() * 8);
        this.windDirection=(directions[result]);
    }

    void setFaults(){
        int result = (int) (Math.random() * 10);
        this.batteryState = faultprob[result];

        result = (int) (Math.random() * 10);
        this.hardwareState = faultprob[result];

        result = (int) (Math.random() * 10);
        this.signalState = faultprob[result];

        result = (int) (Math.random() * 10);
        this.signalState = faultprob[result];
    }

    public String getTemperature() { return temperature; }

    public void setTemperature(String temperature) { this.temperature = temperature; }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) { this.pressure = pressure; }

    public String getSunshine() {
        return sunshine;
    }

    public void setSunshine(String sunshine) { this.sunshine = sunshine; }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) { this.rainfall = rainfall; }

    public String getWindSpeed() { return windSpeed;
    }

    public void setWindSpeed(String windSpeed) { this.windSpeed = windSpeed; }

    public boolean getBatteryState() { return batteryState; }

    public boolean getHardwareState() { return hardwareState; }

    public boolean getSignalState() { return signalState; }

    public String getWindDirection() { return windDirection; }
}