package mvc.app;
import org.junit.*;
import static org.junit.Assert.*;

public class TechnicianTest {

    private Technician tech;

    @Test
    public void EmptyTechnicianTest(){
       tech = new Technician();
       assertNull(tech.getName());
       assertNull(tech.getSurname());
       assertNull(tech.getPassword());
    }

    @Test
    public void NotEmptyTechnicianTest(){
        tech = new Technician("Mario", "Rossi", "Password");
        assertEquals("Mario", tech.getName());
        assertEquals("Rossi", tech.getSurname());
        assertEquals("Password", tech.getPassword());
    }
}
