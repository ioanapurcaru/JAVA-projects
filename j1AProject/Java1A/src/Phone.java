
public class Phone extends Produs {
	private int batteryDimension;
	private int pixels;
	

	public Phone( double price, int nrProducts, String producer, int batteryDimension, int pixels) {
		super( price, nrProducts, producer);
		this.batteryDimension = batteryDimension;
		this.pixels = pixels;
	}
	public int getBatteryDimension() {
		return batteryDimension;
	}
	public void setBatteryDimension(int batteryDimension) {
		this.batteryDimension = batteryDimension;
	}
	public int getPixels() {
		return pixels;
	}
	public void setPixels(int pixels) {
		this.pixels = pixels;
	}
}
