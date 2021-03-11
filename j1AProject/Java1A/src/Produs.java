public class Produs  {
	
	private double price;
	private int nrProducts;
	private String producer;
	
	
	public Produs(double price, int nrProducts, String producer) {
		super();

		this.price = price;
		this.nrProducts = nrProducts;
		this.producer = producer;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNrProducts() {
		return nrProducts;
	}
	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}

	
	@Override
	public String toString() {
		return "produsul cu pretul=" + price + ", in numar de=" + nrProducts + ", producator=" + producer;
	}
}
