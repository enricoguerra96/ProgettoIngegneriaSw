package mvc.app;

public class Person {

    private String firstName;
    private String lastName;

    protected Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String getName(){
        return this.firstName;
    }

    private String getSurname(){
        return this.lastName;
    }
}
