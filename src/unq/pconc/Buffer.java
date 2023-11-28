package unq.pconc;

public class Buffer {

	private Runnable[] data;
	private int begin = 0, end = 0, dimension;
	
	public Buffer(int dimension) {
		super();
		this.dimension = dimension;
		this.data = new Runnable[dimension + 1];
	}
	
	public synchronized void write(Runnable r) throws InterruptedException {
		while (isFull()) { wait(); }
		data[begin] = r;
		begin = next(begin);
		notifyAll();
	}
	
	public synchronized Runnable read() throws InterruptedException {
		while (isEmpty()) { wait(); }
		Runnable result = data[end];
		end = next(end);
		notifyAll();
		return result;
	}
	
	private boolean isEmpty() { return begin == end; }
	private boolean isFull() { return next(begin) == end; }
	private int next(int i) { return (i+1) % (dimension+1); }
}