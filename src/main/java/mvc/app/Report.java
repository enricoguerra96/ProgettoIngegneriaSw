package mvc.app;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Report {

    private String problem;
    private final WeatherStation station;
    private final LinkedList<String> result = new LinkedList<>();
    Pattern pattern;
    private String reportResult = "";
    boolean matchFound;

    public Report(WeatherStation station){
        this.station = station;
    }

    public Report(WeatherStation station, String problem){
        this.station = station;
        this.problem = problem;
    }

    public String fixProblem(String problem) {
        pattern = Pattern.compile("ABNORMAL");
        if(!(problem.equals("Temperature") || problem.equals("Pressure") ||
                problem.equals("Sunshine") || problem.equals("Rainfall") ||
                problem.equals("Wind speed") || problem.equals("Wind direction") ||
                problem.equals("temperature") || problem.equals("pressure") ||
                problem.equals("sunshine") || problem.equals("rainfall") ||
                problem.equals("wind speed") || problem.equals("wind direction")))
            return "There are no measures like this. Try again.";

        MaintenanceStation manstation = new MaintenanceStation(station);
        manstation.PeriodicalMeasureCheck(station, this.result);

        matchProblem();
        lastFix(matchFound);
        return reportResult;
    }

    public void matchProblem(){
        Matcher matcher = pattern.matcher(result.toString());
        matchFound = matcher.find();
        reportResult += "Report about " + problem + " in the station located in " +
                station.getLocation() + " received.";
    }

    public void lastFix(boolean matchFound){
        if(matchFound)
            reportResult += " Problem found. Fixing is now ongoing, try a new measure";
        else
            reportResult += " No problems found.";
    }

    public String getProblem() { return problem; }

    public void setProblem(String problem) { this.problem = problem; }

    public WeatherStation getStation() { return this.station; }

    public String getReportResult() { return this.reportResult; }

    public boolean getMatchFound() { return this.matchFound; }
}