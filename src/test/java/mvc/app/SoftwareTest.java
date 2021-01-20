package mvc.app;
import org.junit.*;

import java.util.LinkedList;

import static org.junit.Assert.*;


public class SoftwareTest {
    private Software software;
    static LinkedList<WeatherStation> oldStations;
    private static LinkedList<Report> oldReports;
    private static LinkedList<WeatherStation> newStations;
    private static LinkedList<Report> newReports;

    @BeforeClass
    public static void setupOnce(){
        oldStations = new LinkedList<>();
        oldReports = new LinkedList<>();
        newStations = new LinkedList<>();
        newReports = new LinkedList<>();
        oldStations.add(new WeatherStation("Rome"));
        oldReports.add(new Report(new WeatherStation("Rome")));
    }

    @Before
    public void setup(){
        software = new Software(1.0);
    }

    @Test
    public void NotValidTest(){
        try {
            Software notSoftware = new Software(-1);
            fail();
        } catch(NegativeVersionException ignored){}
    }

    @Test
    public void UpgradeVersionTest() {
        try {
            software.upgradeVersion();
        } catch (ArrayIndexOutOfBoundsException out) {
            fail();
        }
    }

    @Test
    public void DowngradeVersionTest(){
        if(software.getVersion() < 2.0)
            assertEquals(software.downgradeVersion(), "No");
    }

    @Test
    public void DowngradeVersionTestOk(){
        Software highsoft = new Software(3.0);
            highsoft.downgradeVersion();
            assertEquals(highsoft.getVersion(), 2.0, 0);
    }

    @Test
    public void doBackupTest(){
        try{ software.doBackup( oldStations, oldReports, newStations, newReports); }
        catch(NullPointerException e){
            fail();
        }
    }

    @Test
    public void undoBackupTest(){
        try{ software.undoBackup( oldStations, oldReports, newStations, newReports); }
        catch(NullPointerException e){
            fail();
        }
    }
}