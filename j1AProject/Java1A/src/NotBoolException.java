
public class NotBoolException extends Exception {
	
	public NotBoolException() {
        super("trebuie sa fie true/false");
    }
	public NotBoolException(String errorMessage) {
        super(errorMessage);
    }
}
