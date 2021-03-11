import java.util.*;
public class DemoCabinet{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Cabinet c = new Cabinet();
    boolean stopThread = false;

Thread thread = new Thread(){
   public void run() {
                
                while(true ){
                    try{
                        System.out.println(c.programari.size());
                        Thread.sleep(120000);

                    } catch (Exception e) {
                    }
                }
            }
        };
    
  thread.start();
        while(true){
        String comanda = sc.nextLine();
        String[] cuvinte = comanda.split("\\s+");
        switch(cuvinte[0]){
        case "EXIT": 
               thread.stop();
                return;
        case "AD_CONSULT":
                Integer durata = Integer.parseInt(cuvinte[4]); 
                Programare p1 = new Consultatie(cuvinte[1],cuvinte[2],
                cuvinte[3],durata);
                try{
                    c.adaugaProgramari(p1);
                }catch(PreaMulteProgramariException e){
                    System.out.println(e.getMessage());
                }
                break;
        case "AD_INTERVENTIE":
                Integer zile = Integer.parseInt(cuvinte[4]); 
                Programare p2 = new Interventie(cuvinte[1],cuvinte[2],
                cuvinte[3],zile);
                try{
                    c.adaugaProgramari(p2);
                }catch(PreaMulteProgramariException e){
                    System.out.println(e.getMessage());
                }
                break;
        case "AF_PROGRAMARI":
                 c.afisare(cuvinte[1]);
                 break;
        case "AF_PROGRAMARI_SORTATE":
                 c.afisareAlfabetic();
                 break;
        case "AF_MEDIC":
                try{
                  c.afisareMedic(cuvinte[1]);  
                }catch(PacientInexistentException e){
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
  }
 }
