package mvc.app;
import javax.persistence.*;

@Entity
public class Technician{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String password;

    public Technician(){}

    protected Technician(String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


    public String getName(){ return this.firstName; }

    public String getSurname(){ return this.lastName; }

    public long getId() {
        return this.id;
    }

    public String getPassword(){ return this.password; }

}