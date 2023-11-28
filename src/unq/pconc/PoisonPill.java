package unq.pconc;

public class PoisonPill extends Task {

	@Override
	public void run() throws PoisonException {
		throw new PoisonException("Finalizando ejecucion Thread: " + Thread.currentThread().getId());	
	}
}