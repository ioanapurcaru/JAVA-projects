import java.util.*;

public class Magazin {

	List<Client> clients = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	public void addClient(String name) {
	       //Returns a value that is the result of subtracting 1900 from the year that contains or begins with the instant in time represented by this Date object, as interpreted in the local time zone.
		// cutted because is deprecated, but still works
		 Date d=new Date();  

			int year=d.getYear();
	        int currentYear=year+1900;    
	           Client newClient = new Client(name, currentYear);
	           clients.add(newClient);
	        
	    }
	
	/* verif daca lista de clienti este goala
	/daca nu ii afisez pe fiecare in parte
	parcurg lista cu index care este de tipul CLIENT
	*/
	 public void showClients() {
	        if(clients.isEmpty()) {
	            System.out.println("There are no clients!");
	        }
	        for(Client index : clients) {
	            System.out.println(index.toString());
	        }
	    }
	 
	 // folosesc getProduces ca sa iau lista asignata
	 // clientului gasit la if
	 public void showClientsProduct(String name)
		{
		 for(Client client : clients) {
	            if(client.getName().equals(name))
	            {
	            	if(client.getProduces()==null || client.getProduces().isEmpty()) {
    		            System.out.println("There are no products!");
    		            break;
    		        }
	            	for(Produs produs : client.getProduces()) {
	    				System.out.println(produs.toString());
	    			}
	            }
	        }
		}
	 
	 // aici fac verificari pentru fielduri ca sa fiu sigur ca este
	 //introdusa o valoare corecta altfel scriu ceva in consola
		public void addProduct(String name,String tip)
		{
			for(Client client : clients) {
				if(client.getName().equals(name)){ {
			if(tip.equals("laptop"))
			{
				System.out.println("Te rog introdu pretul, numarul de produse, "
						+ "producatorul,numarul de procesoare"
						+ " si daca are touchscreen");
// scanner.next citeste stringul de pe urmatorul rand
				double price=0;
				try {
					String priceString = scanner.next();
					 price=Double.parseDouble(priceString);  
				}
			 catch (Exception e) {
				price = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
			
	            int nrProducts = 0;
	        	try {
					String nrProductsString = scanner.next();
					nrProducts=Integer.parseInt(nrProductsString);  
				}
			 catch (Exception e) {
				 nrProducts = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	           
	            String producer = scanner.next();
	            int e1 = 0;
	            try {
					String eiString = scanner.next();
					e1=Integer.parseInt(eiString);  
				}
			 catch (Exception e) {
				 e1 = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	            // aici fac verificari sa fiu sigur ca stringul e true sau false
	            //altfel arunc exceptie din NotBoolException 
	            // returnez stringul de acolo
	            boolean e2 = false;
	            try {
					String e2String = scanner.next();
					if(!(e2String.equals("true") || e2String.equals("false")) )
						throw new NotBoolException();
					e2=Boolean.parseBoolean(e2String);  
				}
			 catch (Exception e) {
				 e2 = false;
				System.out.println("Acest field trebuie sa fie de forma true/false");
				break;
			}
	            
				Produs laptop = new Laptop(  price,  nrProducts,  producer,  e1,  e2);
				client.addToList(laptop);
				 System.out.println("laptopul a fost adaugat");
			}
			
			if(tip.equals("telefon"))
			{
				System.out.println("Te rog introdu pretul, numarul de produse, "
						+ "producatorul,dimeniunea bateriei si numarul de pixeli");
				double price=0;
				try {
					String priceString = scanner.next();
					 price=Double.parseDouble(priceString);  
				}
			 catch (Exception e) {
				price = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
			
	            int nrProducts = 0;
	        	try {
					String nrProductsString = scanner.next();
					nrProducts=Integer.parseInt(nrProductsString);  
				}
			 catch (Exception e) {
				 nrProducts = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	           
	            String producer = scanner.next();
	            int e1 = 0;
	        	try {
					String e1String = scanner.next();
					e1=Integer.parseInt(e1String);  
				}
			 catch (Exception e) {
				 e1 = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	        	  int e2 = 0;
		        	try {
						String e2String = scanner.next();
						e2=Integer.parseInt(e2String);  
					}
				 catch (Exception e) {
					 e2 = 0;
					System.out.println("Caracterele nu sunt valide pentru acest field");
					break;
				}
	            
				Produs phone = new Phone(  price,  nrProducts,  producer,  e1,  e2);
				client.addToList(phone);
			}
			
			if(tip.equals("televizor"))
			{
				System.out.println("Te rog introdu pretul, numarul de produse, "
						+ "producatorul,diagonala si daca este smart");
				double price=0;
				try {
					String priceString = scanner.next();
					 price=Double.parseDouble(priceString);  
				}
			 catch (Exception e) {
				price = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
			
	            int nrProducts = 0;
	        	try {
					String nrProductsString = scanner.next();
					nrProducts=Integer.parseInt(nrProductsString);  
				}
			 catch (Exception e) {
				 nrProducts = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	           
	            String producer = scanner.next();
	            double e1=0;
				try {
					String e1String = scanner.next();
					 e1=Double.parseDouble(e1String);  
				}
			 catch (Exception e) {
				e1 = 0;
				System.out.println("Caracterele nu sunt valide pentru acest field");
				break;
			}
	            boolean e2 = false;
	            try {
					String e2String = scanner.next();
					if(!(e2String.equals("true") || e2String.equals("false")) )
						throw new NotBoolException();
					e2=Boolean.parseBoolean(e2String);  
				}
			 catch (Exception e) {
				 e2 = false;
				System.out.println("Acest field trebuie sa fie de forma true/false");
				break;
			}
	            
				Produs television = new Television(  price,  nrProducts,  producer,  e1,  e2);
				client.addToList(television);
			}
			}
		}
		}
		}

		// ca sa stegi un produs cauti mai intai clientul dupa nume
		//apoi produsul dupa producator, fiecare in lista lui
		public void deteleProduct(String name,String producer) {
			for(Client client : clients) {
				if(client.getName().equals(name)){ 
			for(Produs produs : client.getProduces() ) {
				if(produs.getProducer().equals(producer)) {
					client.removeFromList(produs);
					break;
				}
			}
				}
				}
		}
		
		public void deteleAllProducts(String name) {
			for(Client client : clients) {
				if(client.getName().equals(name)){ 
					client.removeAllFromList();
					break;
				}
				}
		}
		
		// am un price local pe care-l calculez mereu in functie
		// de pretul produselor unui anumit client
		public void displayAllCoustomers() {
			for(Client client : clients) {
				double price =0;
				for(Produs produs : client.getProduces() ) {
					price=price+produs.getPrice();
				}
				System.out.println( client.toString() + " are lista de cumparaturi: " 
						+ client.getProduces());
				if(client.getAnInregistrare()<2020)
					price=price - (0.1*price);
				System.out.println("Cu suma totala: "+ price);
			}
			}
		
		// verific daca clientul cu numele dat de la tastatura exista
		// daca exista returnez true, altfel false
		public boolean doesUserExists(String name) {
			boolean isClient=false;
			for(Client client : clients) {
				if(client.getName().equals(name))
					isClient=true;
			}
			if(isClient==true)
				return true;
			else
				return false;
			}
		
		
		
		
		
		
		
		
	
}
	
	 

