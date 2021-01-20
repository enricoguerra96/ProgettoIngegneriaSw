package mvc.app;

import java.util.LinkedList;

public class Software {

    private double version;
    private final boolean[] probUpdate =
            { true, false, false, true, true, false, true, false, true , false, true};

    public Software(double version){
        this.version = version;
        if(version < 0.0)
            throw new NegativeVersionException("Version must be positive.\n");
    }

    public String upgradeVersion(){
        int result = (int) (Math.random() * 9);
        if( !probUpdate[result] ) {
            this.version += 1.0;
            return "New version available. \n Backup done.\n upgraded to " + this.version;
        }
        else
            return "No";
    }

    public String downgradeVersion(){
        if(version < 2.0)
            return "No";

        this.version -= 1.0;
        return "Downgraded to version " + this.version;
    }

    public void doBackup(LinkedList<WeatherStation> oldStations,
                          LinkedList<Report> oldReports,
                          LinkedList<WeatherStation> newStations,
                          LinkedList<Report> newReports){
        newStations.addAll(oldStations);
        newReports.addAll(oldReports);
    }

    public void undoBackup(LinkedList<WeatherStation> oldStations,
    LinkedList<Report> oldReports,
    LinkedList<WeatherStation> newStations,
    LinkedList<Report> newReports){
        oldStations.addAll(newStations);
        oldReports.addAll(newReports);
    }

    public double getVersion(){ return this.version; }
}
