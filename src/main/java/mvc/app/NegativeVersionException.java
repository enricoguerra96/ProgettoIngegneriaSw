package mvc.app;

public class NegativeVersionException extends RuntimeException{
    public NegativeVersionException(String message){
        super(message);
    }
}
