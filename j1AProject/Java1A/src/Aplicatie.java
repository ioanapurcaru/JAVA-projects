import java.util.Scanner;

public class Aplicatie {
// aici este mainul de aici se ruleaza aplicatia
	public static void main(String[] args) {
		
		// instanta a clasei Scanner cu care urmeaza sa citesc
		Scanner scanner = new Scanner(System.in);
		String comanda = scanner.next();
		//instanta a clasei magazin pentru a putea folosi metodele din Magazin
		Magazin magazin = new Magazin();
		
		
		 while(comanda.equals("START") == false) {
	            System.out.println("Te rog introdu comanda START!");
	            comanda = scanner.next();
	            
	        }
		
		 comanda = scanner.next();
		 while(comanda.equals("EXIT") == false) {
			 System.out.println("Te rog introdu una din comenzile urmatoare"
	            		+ "pentru a perfoma actiuni:  \n"+
					 "ADAUGA_CLIENT\n"+
					 "AFISARE_CLIENTI\n"+
					 "ADAUGA_PRODUS\n"+
					 "STERGE_PRODUS\n"+
					 "AFISARE_COS\n"+
					 "STERGE_TOATE_PRODUSELE\n"+
					 "AFISARE_COMPLETA\n"
					 );
			 switch(comanda) {
			 case "ADAUGA_CLIENT":
				 System.out.println("care este numele clientului?");
			 String name = scanner.next();
			 magazin.addClient(name);
			 System.out.println("clientul a fost adaugat");
			 break;
			 
			 case "AFISARE_CLIENTI":
				 magazin.showClients();
				 break;
			 
			 case "ADAUGA_PRODUS":
				 System.out.println("carui client vrei sa ii adaugi produs?");
				 String nameclient = scanner.next();
				try {
					// verific daca clientul exista, daac nu exista arunc 
					//exceptia ClientInexistentException, scria intr-o clasa separata
					if(magazin.doesUserExists(nameclient)==false)
						throw new ClientInexistentException();
				 System.out.println("ce produs vrei sa adaugi?");
				 String tip = scanner.next();
				 if(!(tip.equals("laptop") || tip.equals("telefon") || tip.equals("televizor")) )
				 { System.out.println("te rog sa reintroduci comanda"
				 		+ " si sa alegi doar laptop, telefon sau televizor");
				 break;
				 }
				 magazin.addProduct(nameclient, tip);
				} catch (ClientInexistentException e) {
					System.out.println(e.getMessage());
				}
				
				 break;
				 
			 case "STERGE_PRODUS":
				 System.out.println("carui client vrei sa ii stergi un produs?");
				 String nameClientForDeleteProduct = scanner.next();
				 System.out.println("ce produs vrei sa stergi?");
				 String productToBeDeleted = scanner.next();
				 magazin.deteleProduct(nameClientForDeleteProduct, productToBeDeleted);
				 System.out.println("produsul a fost sters");
				 break;
				 
			 case "AFISARE_COS":
				 System.out.println("carui client vrei sa ii afisezi produs?");
				 String nameclient1 = scanner.next();
				 magazin.showClientsProduct(nameclient1);
				 break;
				 
			 case "STERGE_TOATE_PRODUSELE":
				 System.out.println("carui client vrei sa ii stergi toate produsele?");
				 String nameClientForDeleteAllProducts = scanner.next();
				 magazin.deteleAllProducts(nameClientForDeleteAllProducts);
				 System.out.println("Lista clientului " +nameClientForDeleteAllProducts+
						  " a fost golita");
				 break;
				 
				 
			 
			 }
			 // citesc mereu, daca n-as face asta nu ar continua sa asculte
			 //pentru alta comanda
			 comanda = scanner.next();
			 }
			 
		 }

	}




