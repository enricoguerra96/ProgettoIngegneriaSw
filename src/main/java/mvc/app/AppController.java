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
    private TechRepository repository;
    @Autowired
    private StationRepository stationRepository;
    private long techid;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "id") Long id,
                        @RequestParam(value = "password") String password,
                        Model model) {
        if(id == null || password == null)
            return "/blankfield";
        Optional< Technician> result = repository.findById(id);
        if(!result.isPresent())
            return "redirect/noaccount";
        if(!result.get().getPassword().equals(password))
            return "redirect/IncorrectPassword";
        return "home";
    }

    @RequestMapping("/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping("/signup")
    public String signup(@RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "password") String password,
                               Model model){
        if(name == null || surname == null || password == null)
            return "/blankfield";
        repository.save(new Technician(name, surname, password));
        return "redirect:/home";
    }

    @RequestMapping("home")
    public String home(Model model){
        model.addAttribute("id", repository.findById(techid));

        try {//Operazione che dà l'errore
            stationRepository.save(new WeatherStation("Rome"));
        }
        catch(Exception e){e.printStackTrace();}
        List<WeatherStation> stations = new LinkedList<>();
        for (WeatherStation ws: stationRepository.findAll()){
            stations.add(ws);
        }
        model.addAttribute("wstations", stations);

        return "home";
    }

    @RequestMapping("control")
    public String control(Model model){
        stationRepository.save(new WeatherStation("Rome"));
        stationRepository.save(new WeatherStation("Naples"));
        stationRepository.save(new WeatherStation("Milan"));
        stationRepository.save(new WeatherStation("Turin"));
        stationRepository.save(new WeatherStation("Palermo"));
        stationRepository.save(new WeatherStation("Reggio Calabria"));
        stationRepository.save(new WeatherStation("Cagliari"));
        stationRepository.save(new WeatherStation("Venice"));
        stationRepository.save(new WeatherStation("Bolzano"));
        stationRepository.save(new WeatherStation("Florence"));
        stationRepository.save(new WeatherStation("Bari"));
        stationRepository.save(new WeatherStation("Genova"));
        stationRepository.save(new WeatherStation("Bologna"));
        stationRepository.save(new WeatherStation("Perugia"));
        stationRepository.save(new WeatherStation("Taranto"));
        return "home";
    }
}