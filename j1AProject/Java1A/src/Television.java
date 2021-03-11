
// clasa televizor mosteneste produs
public class Television  extends Produs{
	private double diagonaleSize;
	private boolean smart;
	
	
	public Television( double price, int nrProducts, String producer, double diagonaleSize, boolean smart) {
		super( price, nrProducts, producer);
		this.diagonaleSize = diagonaleSize;
		this.smart = smart;
	}
	
	//getter si setter pentru a putea accesa extern atributele private
	public double getDiagonaleSize() {
		return diagonaleSize;
	}
	public void setDiagonaleSize(double diagonaleSize) {
		this.diagonaleSize = diagonaleSize;
	}
	public boolean isSmart() {
		return smart;
	}
	public void setSmart(boolean smart) {
		this.smart = smart;
	}
	
}
