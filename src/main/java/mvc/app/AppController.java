package mvc.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    TechRepository repository;
    @Autowired
    StationRepository stationRepository;

    private final LinkedList<Report> reportList = new LinkedList<>();

    final LinkedList<WeatherStation> viewList = new LinkedList<>();

    private final String[] cities = {"Rome", "Naples", "Milan", "Turin", "Palermo",
            "Florence", "Venice", "Cagliari", "Bolzano",
            "Reggio Calabria", "Bari", "Bologna", "Perugia",
            "Taranto"};

    private final LinkedList<Report> backupReportList = new LinkedList<>();

    private final LinkedList<WeatherStation> backupViewList = new LinkedList<>();

    double index = 1.0;

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "password", required = false) String password,
                        Model model) {
        if (id == null || password == null)
            return "redirect:/blankfield";

        Optional<Technician> result = repository.findById(id);

        if (!result.isPresent())
            return "redirect:/noaccount";

        if (!result.get().getPassword().equals(password))
            return "redirect:/incorrectPassword";
        return "redirect:/home";
    }

    @RequestMapping("/blankfield")
    public String blankfield() { return "blankfield"; }

    @RequestMapping("/blankfieldsreg")
    public String blankfieldsreg() { return "blankfieldsreg"; }

    @RequestMapping("/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping("/signup")
    public String signup(@RequestParam(value = "name") String name,
                         @RequestParam(value = "surname") String surname,
                         @RequestParam(value = "password") String password,
                         Model model) {
        if (name.isEmpty() || surname.isEmpty() || password.isEmpty())
            return "redirect:/blankfieldsreg";

        Technician t = new Technician(name, surname, password);

        repository.save(t);
        return "redirect:/home";
    }

    @RequestMapping("calculate")
    public String calculate(Model model) {

        viewList.clear();

        for (String city : cities) {
            WeatherStation ws = new WeatherStation(city);
            stationRepository.save(new WeatherStation(city));
            viewList.add(ws);
        }
        return "redirect:/home";
    }

    @RequestMapping("home")
    public String home(Model model) {

        viewList.clear();

        for (String city : cities) {
            WeatherStation ws = new WeatherStation(city);
            stationRepository.save(ws);
            viewList.add(ws);
        }

        model.addAttribute("wstations", viewList);

        return "home";
    }

    @RequestMapping("incorrectPassword")
    public String incorrectPassword() {
        return "incorrectPassword";
    }

    @RequestMapping("noaccount")
    public String noaccount() {
        return "noaccount";
    }

    @RequestMapping("list")
    public String list(Model model) {

        List<Technician> techs = new LinkedList<>();
        for (Technician te : repository.findAll()) {
            techs.add(te);
        }
        model.addAttribute("techs", techs);

        return "list";
    }

    @RequestMapping("archive")
    public String archive(Model model) {

        List<WeatherStation> wstations = new LinkedList<>();
        for (WeatherStation ws : stationRepository.findAll())
            wstations.add(ws);

        model.addAttribute("wstations", wstations);
        return "archive";
    }

    @RequestMapping("periodicalcheck")
    public String periodicalcheck(Model model) {
        MaintenanceStation manstation = new MaintenanceStation(viewList);
        model.addAttribute("result", manstation.getResult());

        model.addAttribute("reports", reportList);
        return "periodicalcheck";
    }

    Optional<WeatherStation> reportedStation;
    Report report;
    String cause;
    String repResult;

    @RequestMapping("reportpage")
    public String reportpage(@RequestParam(name = "id") Long id,
                             Model model) {
        reportedStation = stationRepository.findById(id);
        return "reportpage";
    }

    @RequestMapping("report")
    public String report(@RequestParam(name = "cause") String cause,
                         Model model) {

        if(reportedStation.isPresent()) {
            report = new Report(reportedStation.get(), cause);
            this.cause = cause;
            repResult = report.fixProblem(cause);
            if(!repResult.equals("There are no measures like this. Try again.")) {
                reportList.add(report);
            }
        }

        return "redirect:/reportresult";
    }

    @RequestMapping("reportresult")
    public String reportresult(Model model){
        model.addAttribute("response", repResult);
        return "reportresult";
    }

    Software software;
    String result;

    @RequestMapping("checkversion")
    public String checkversion(Model model) {
        try {
            software = new Software(index);
        }
        catch(NegativeVersionException e){
            return"redirect:/invalidversion";
        }
        model.addAttribute("index", index);
        return "checkversion";
    }

    @RequestMapping("upgradeversion")
    public String upgradeversion(Model model) {
        software = new Software(index);
        result = software.upgradeVersion();

        if(result.equals("No"))
            return "redirect:/nonewversion";
        else {
            software.doBackup(viewList, reportList, backupViewList, backupReportList);
            this.index++;
            return "redirect:/versionresult";
        }
    }

    @RequestMapping("downgradeversion")
        public String downgradeversion(Model model){
            software = new Software(index);
            result = software.downgradeVersion();
            if(result.equals("No"))
                return "redirect:/nodowngrade";
            else{
                software.undoBackup(viewList, reportList, backupViewList, backupReportList);
                this.index--;
                return "redirect:/versionresult";
            }
        }

    @RequestMapping("versionresult")
    public String versionresult(Model model){
        model.addAttribute("result", result);
        return "versionresult";
    }

    @RequestMapping("nonewversion")
    public String nonewversion(Model model){
        return "nonewversion";
    }

    @RequestMapping("nodowngrade")
    public String nodowngrade(Model model){
        return "nodowngrade";
    }


    @RequestMapping("invalidversion")
    public String invalidversion(Model model){
        return "invalidversion";
    }


}