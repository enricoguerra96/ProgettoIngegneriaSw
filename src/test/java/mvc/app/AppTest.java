package mvc.app;

import net.sourceforge.jwebunit.junit.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private WebTester tester;

    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/");
    }

    //STUDY CASE 1: LOGIN AND REGISTRATION
    @Test
    @Order(1)
    public void testLoginWrong() {
        tester.beginAt("/");
        tester.assertTextPresent("Welcome to the weather station control system. Please log in:");
        tester.setTextField("id", "9999999999999");
        tester.setTextField("password", "password");
        tester.submit();
        tester.assertTextPresent("There's no such account associated with this ID.");
    }

    @Test
    @Order(2)
    public void testBlankLogin() {
        tester.beginAt("/");
        tester.assertTextPresent("Welcome to the weather station control system. Please log in:");
        tester.setTextField("id", "");
        tester.setTextField("password", "");
        tester.submit();
        tester.assertTextPresent("Username and password are required.");
    }

    @Test
    @Order(3)
    public void testLoginToRegistration() {
        tester.beginAt("/");
        tester.assertTextPresent("Welcome to the weather station control system. Please log in:");
        tester.clickLinkWithText("No account? Sign up");
        tester.assertTextPresent("Sign up to create an account. Your ID will be assigned automatically.");
    }

    @Test
    @Order(4)
    public void testRegistrationBlank() {
        tester.beginAt("/registration");
        tester.assertTextPresent("Sign up to create an account. Your ID will be assigned automatically.");
        tester.setTextField("name", "");
        tester.setTextField("password", "");
        tester.submit();
        tester.assertTextPresent("Name, surname and password required.\n");
    }

    @Test
    @Order(5)
    public void testRegistrationOK() {
        tester.beginAt("/registration");
        tester.assertTextPresent("Sign up to create an account. Your ID will be assigned automatically.");
        tester.setTextField("name", "Mario");
        tester.setTextField("surname", "Rossi");
        tester.setTextField("password", "Password");
        tester.submit();
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickLinkWithText("List of logged technicians");
        String id = tester.getElementTextByXPath("//table//td[1]");
        tester.clickLinkWithText("Go back");
        tester.clickLinkWithText("Logout");
        tester.assertTextPresent("Welcome to the weather station control system. Please log in:");
        tester.setTextField("id", id);
        tester.setTextField("password", "Password");
        tester.submit();
        tester.assertTextPresent("Welcome to the Weather stations control home.");
    }

    //STUDY CASE 2: MEASURES CHECK BY TECHNICIANS

    @Test
    @Order(6)
    public void testControlHome() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        int num = tester.getElementsByXPath("//td").size();
        Assert.assertTrue(num > 0);
        tester.clickButtonWithText("New measure");
        int newnum = tester.getElementsByXPath("//td").size();
        Assert.assertEquals(newnum, num);
    }

    @Test
    @Order(7)
    public void testHomeReport() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.assertTextInTable("t01", "Report a problem");
        tester.clickLinkWithText("Report a problem");
        tester.assertTextPresent("Problems reporting");
    }

    @Test
    @Order(8)
    public void testMonitorMeasures() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickButtonWithText("Periodical health check");
        tester.assertTextPresent("Last periodical health check for each station.");
    }

    //STUDY CASE 3: DATA ARCHIVING AND PROCESSING

    @Test
    @Order(9)
    public void testDataArchiving() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickLinkWithText("Measurements archive");
        tester.assertTextPresent("Archive of measurements");
        int num = tester.getElementsByXPath("//td").size();
        Assert.assertTrue(num > 0);
        tester.clickLinkWithText("Go back");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickLinkWithText("Measurements archive");
        tester.assertTextPresent("Archive of measurements");
        int num2 = tester.getElementsByXPath("//td").size();
        Assert.assertTrue(num2 > num);
    }

    //STUDY CASE 4: HEALTH AND FAULTS REPORTS

    @Test
    @Order(10)
    public void testHealthChecking() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickButtonWithText("Periodical health check");
        tester.assertTextPresent("Last periodical health check for each station.");
        int num = tester.getElementsByXPath("//td").size();
        Assert.assertTrue(num > 0);
        tester.assertTextPresent("STATION WITH LOCATION IN");
    }

    @Test
    @Order(11)
    public void testWrongFaultReport() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickLinkWithText("Report a problem");
        tester.assertTextPresent("Problems reporting");
        tester.clickButtonWithText("Report");
        tester.assertTextPresent("There are no measures like this. Try again.");
    }

    @Test
    @Order(12)
    public void testOKFaultReport() {
        tester.beginAt("/home");
        tester.assertTextPresent("Welcome to the Weather stations control home.");
        tester.clickLinkWithText("Report a problem");
        tester.assertTextPresent("Problems reporting");
        tester.setTextField("cause", "Temperature");
        tester.clickButtonWithText("Report");
        tester.assertTextPresent("Report about Temperature in the station located in");
        tester.assertTextPresent("received");
        tester.clickLinkWithText("Go back");
        tester.clickButtonWithText("Periodical health check");
        tester.assertTextPresent("Reports received:");
        tester.assertTablePresent("t02");
        tester.assertTextPresent("Report received: \tTemperature");
    }

    //STUDY CASE 5: SOFTWARE CHECK, UPGRADE, DOWNGRADE

    @Test
    @Order(13)
    public void testUpgradeVersion() {
        tester.beginAt("/home");
        tester.clickButtonWithText("Software version check");
        tester.assertTextPresent("Current software version:");
        //salvati la versione corrente
        double version = Double.parseDouble(tester.getElementTextByXPath("//p"));
        //vai nella pagina dopo
        tester.clickButtonWithText("Check for software upgrades");
        //salvati il risultato del controllo
        String result = tester.getElementTextByXPath("//p");
        //Se una nuova versione è disponibile e upgrade fatto, torna indientro e controlla i risultati
        if (result.contains("New version available.")) {
            tester.clickLinkWithText("Go back");
            String newversion = tester.getElementTextByXPath("//p");
            Assert.assertEquals(Double.toString(version + 1), newversion);
        } else {
            tester.assertTextPresent("There are no new software versions available.");
        }
    }

    @Test
    @Order(14)
    public void testDowngradeVersion() {
        tester.beginAt("/home");
        tester.clickButtonWithText("Software version check");
        tester.assertTextPresent("Current software version:");
        //salvati la versione corrente
        double version = Double.parseDouble(tester.getElementTextByXPath("//p"));
        //vai nella pagina dopo
        tester.clickButtonWithText("Software downgrade");
        if (version <= 1.0)
            tester.assertTextPresent("Software version is already the lowest. Can't downgrade.");
        else if (version > 1.0) {
            tester.assertTextPresent("Downgraded to version");
            tester.clickLinkWithText("Go back");
            String newVersion = tester.getElementTextByXPath("//p");
            Assert.assertEquals(Double.toString(version - 1), newVersion);
        }
    }
}