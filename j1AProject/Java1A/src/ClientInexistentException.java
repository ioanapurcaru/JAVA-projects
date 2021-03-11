// este folosita pentru a da exceptie in caz canu exista client
public class ClientInexistentException extends Exception {
	
	public ClientInexistentException() {
        super("acest client nu exista");
    }
	public ClientInexistentException(String errorMessage) {
        super(errorMessage);
    }
}
