package mvc.app;
import org.junit.*;

import java.util.regex.PatternSyntaxException;
import static org.junit.Assert.*;

public class ReportTest {

    private String problem;
    private WeatherStation station;
    private Report report;
    private String result;

    @Before
    public void setup(){
        station = new WeatherStation("Rome");
        this.problem = "Problem";
        report = new Report(station, problem);
    }
    @After
    public void clean(){
        this.result = "";
    }

    @Test
    public void ReportConstructorTest(){
        assertEquals(problem, report.getProblem());
        assertEquals(station, report.getStation());
    }

    @Test
    public void ReportConstructorNoProblemTest(){
        Report statReport = new Report(station);
        assertEquals(station, statReport.getStation());
    }

    @Test
    public void ReportFixProblemPatternTest(){
        try {
            report.fixProblem(problem);
        }
        catch(PatternSyntaxException pat){ fail(); }
    }

    @Test
    public void ReportMatchProblemTest(){
        result = report.fixProblem("Temperature");
        assertTrue(result.contains("Report about " + problem + " in the station located in " +
                station.getLocation() + " received."));
    }

    @Test
    public void ReportLastFixTest(){
        report.lastFix(false);
        assertEquals(" No problems found.", report.getReportResult());
    }

    @Test
    public void ReportLastFixTrueTest(){
        report.lastFix(true);
        assertEquals(" Problem found. Fixing is now ongoing, try a new measure", report.getReportResult());
    }

    @Test
    public void setProblemTest(){
        report.setProblem("Custom");
        assertEquals(report.getProblem(), "Custom");
    }

    @Test
    public void getMatchFoundTest(){
        boolean match = report.getMatchFound();
        assertEquals(match, report.getMatchFound());
    }
}