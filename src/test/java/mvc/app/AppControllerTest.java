package mvc.app;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

public class AppControllerTest {

    private AppController controller;
    Technician tech;
    Model model;

    @Before
    public void setup() {
        tech = new Technician("Mario", "Rossi", "Password");
        controller = new AppController();
    }

    @Test
    public void indexTest(){
        String ind = controller.index();
        assertEquals(ind, "login");
    }


    @Test
    public void noAccountTest(){
        String ind = controller.noaccount();
        assertEquals(ind, "noaccount");
    }

    @Test
    public void BlankFieldTest(){
        String ind = controller.blankfield();
        assertEquals(ind, "blankfield");
    }
    @Test
    public void BlankFieldRegTest(){
        String ind = controller.blankfieldsreg();
        assertEquals(ind, "blankfieldsreg");
    }

    @Test
    public void RegistrationTest(){
        String ind = controller.registration();
        assertEquals(ind, "registration");
    }

    @Test
    public void signupNullTest(){
        String ind = controller.signup("","","", model);
        assertEquals(ind, "redirect:/blankfieldsreg");
    }

    @Test
    public void IncorrectPasswordTest(){
        String ind = controller.incorrectPassword();
        assertEquals(ind, "incorrectPassword");
    }


    @Test
    public void NoDowngradeTest(){
        String ind = controller.nodowngrade(model);
        assertEquals(ind, "nodowngrade");
    }

    @Test
    public void NoNewVersionTest(){
        String ind = controller.nonewversion(model);
        assertEquals(ind, "nonewversion");
    }

    @Test
    public void InvalidVersionTest(){
        String ind = controller.invalidversion(model);
        assertEquals(ind, "invalidversion");
    }
}
