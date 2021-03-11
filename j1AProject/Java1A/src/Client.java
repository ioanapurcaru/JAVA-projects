
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// clasa client care nu mostenste nimic
public class Client {
	
	private String name;
	private int anInregistrare;
	private List<Produs> produces=new ArrayList<>();
	private double totalPrice;

Client() {
		
	}
	
	public Client(String name, int anInregistrare, List<Produs> produces) {
		super();
		this.name = name;
		this.anInregistrare = anInregistrare;
		this.produces = produces;
	}
	
	public Client(String name, int anInregistrare, List<Produs> produces, double totalPrice) {
		super();
		this.name = name;
		this.anInregistrare = anInregistrare;
		this.produces = produces;
		this.totalPrice=totalPrice;
	}

	public Client(String name, int anInregistrare) {
		super();
		this.name = name;
		this.anInregistrare = anInregistrare;

	}
	public Client(String name) {
		super();
		this.name = name;

	}

	public List<Produs> getProduces() {
		return produces;
	}


	public void setProduces(List<Produs> produces) {
		this.produces = produces;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAnInregistrare() {
		return anInregistrare;
	}


	public void setAnInregistrare(int anInregistrare) {
		this.anInregistrare = anInregistrare;
	}



public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

@Override
public String toString() {
	return "Clientul cu numele " + name + ", inregistrat in " + anInregistrare ;
}
	
	Scanner scanner = new Scanner(System.in);

	
	
	public void removeAllProducts(String name) {
		Client client = new Client();
		if(client.name.equals(name)) {
			client.produces.clear();
		}
	}
	
	public void addToList(Produs p)
	{
		produces.add(p);
	}
	
	public void removeFromList(Produs p)
	{
		produces.remove(p);
	}
	
	public void removeAllFromList()
	{
		produces.clear();
	}
	

}