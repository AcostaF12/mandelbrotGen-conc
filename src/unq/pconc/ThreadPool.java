package unq.pconc;

public class ThreadPool {
	
	private int numberThreads;
	private Buffer buffer;
	private Thread[] threads;
	
	public ThreadPool(int numberThreads, int numberTasks, int tamañoBuffer) {
		super();
		this.threads = new Thread[numberThreads];
		this.buffer = new Buffer(tamañoBuffer);
		this.numberThreads = numberThreads;
		
		for (int i = 0; i < numberThreads; i++) {
			this.threads[i] = new Worker(buffer);
			this.threads[i].start();
		}
	}
	
	public void launch(Runnable task) {
		try {
			buffer.write(task);			
		} catch (InterruptedException e) {}
	}
	
	public void stop() {
        for (int i = 0; i < numberThreads; i++) {
            launch(new PoisonPill());
        }
    }
}