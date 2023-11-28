package unq.pconc;

public class Worker extends Thread {

	private Buffer buffer;
	
	public Worker(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	
	public void run() {
		try {
			while (true) { 
				Runnable task = buffer.read();
				task.run();
			}
		} catch (InterruptedException e) {}
	}
}