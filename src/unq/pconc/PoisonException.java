package unq.pconc;

public class PoisonException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PoisonException(String message) {
        super(message);
    }
}