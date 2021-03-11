public class Laptop extends Produs {
	
	private int nrCPU;
	private boolean hasTouchscreen;
	
	
	public Laptop( double price, int nrProducts, String producer, int nrCPU, boolean hasTouchscreen) {
		super( price, nrProducts, producer);
		this.nrCPU = nrCPU;
		this.hasTouchscreen = hasTouchscreen;
	}
	
	public int getNrCPU() {
		return nrCPU;
	}
	public void setNrCPU(int nrCPU) {
		this.nrCPU = nrCPU;
	}
	public boolean isHasTouchscreen() {
		return hasTouchscreen;
	}
	public void setHasTouchscreen(boolean hasTouchscreen) {
		this.hasTouchscreen = hasTouchscreen;
	}

}
