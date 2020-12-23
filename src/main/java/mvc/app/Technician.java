package mvc.app;
import javax.persistence.*;

@Entity
public class Technician extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String password;

    public Technician(){}

    public Technician(String firstName, String lastName) {
        super(firstName, lastName);
    }

    protected Technician(String firstName, String lastName, String password){
        super(firstName, lastName);
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    protected String getPassword(){ return this.password; }
}