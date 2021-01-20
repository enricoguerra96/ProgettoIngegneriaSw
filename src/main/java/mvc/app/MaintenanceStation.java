package mvc.app;
import java.util.LinkedList;

public class MaintenanceStation {

    private LinkedList<WeatherStation> stations = new LinkedList<>();

    private final LinkedList<String> result = new LinkedList<>();

    public MaintenanceStation(LinkedList<WeatherStation> stations) {
        this.stations = stations;
        for(WeatherStation st: stations){
            PeriodicalHealthCheck(st, this.result);
            PeriodicalMeasureCheck(st, this.result);
        }
    }

    public MaintenanceStation(WeatherStation station) {
        stations.add(station);
        for(WeatherStation st: stations){
            PeriodicalHealthCheck(st, this.result);
            PeriodicalMeasureCheck(st, this.result);
        }
    }

    void PeriodicalHealthCheck(WeatherStation station, LinkedList<String> result) {
        result.add(" \n");
        result.add("STATION WITH LOCATION IN " + station.getLocation() + ":");
        if (station.getLocation().equals("")) {
            result.add("Location not found. Communication with station failed.");
        }
        if (!station.getBatteryState())
            result.add("Batteries: NOT OK. Batteries fixing routine initiated.");
        else
            result.add("Batteries: OK.");
        if (!station.getHardwareState())
            result.add("Hardware: NOT OK. A manteinance team has been deployed.");
        else
            result.add("Hardware: OK");
        if (!station.getSignalState())
            result.add("Signal: NOT OK. Signal fixing routine initiated.");
        else
            result.add("Signal: OK.");
        }

    void PeriodicalMeasureCheck(WeatherStation station, LinkedList<String> result) {

        int ind = station.getTemperature().indexOf(" ");
        if (Integer.parseInt(station.getTemperature().substring(0, ind)) < -20 ||
                Integer.parseInt(station.getTemperature().substring(0, ind)) > 50)
            result.add("Temperature is IRREGULAR. Check for hardware problems.");
        else
            result.add("Temperature is NORMAL.");

        ind = station.getPressure().indexOf(" ");
        if (Integer.parseInt(station.getPressure().substring(0, ind)) < 0 ||
                Integer.parseInt(station.getPressure().substring(0, ind)) > 1028)
            result.add("Pressure is IRREGULAR. Check for hardware problems.");
        else
            result.add("Pressure is NORMAL.");

        ind = station.getSunshine().indexOf(" ");
        if (Integer.parseInt(station.getSunshine().substring(0, ind)) < 0 ||
                Integer.parseInt(station.getSunshine().substring(0, ind)) > 6.3)
            result.add("Sunshine is IRREGULAR. Check for hardware problems.");
        else
            result.add("Sunshine is NORMAL.");

        ind = station.getRainfall().indexOf(" ");
        if (Integer.parseInt(station.getRainfall().substring(0, ind)) < 0 ||
                Integer.parseInt(station.getRainfall().substring(0, ind)) > 16)
            result.add("Rainfall is IRREGULAR. Check for hardware problems.");
        else
            result.add("Rainfall is NORMAL.");

        ind = station.getWindSpeed().indexOf(" ");
        if (Integer.parseInt(station.getWindSpeed().substring(0, ind)) < 0 ||
                Integer.parseInt(station.getWindSpeed().substring(0, ind)) > 36)
            result.add("Wind speed is IRREGULAR. Check for hardware problems.");
        else
            result.add("Wind speed is NORMAL.");
    }

    public LinkedList<String> getResult() { return result; }
}