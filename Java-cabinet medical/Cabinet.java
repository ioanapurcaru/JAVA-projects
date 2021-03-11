import java.util.*;
public class Cabinet{

    List<Programare> programari = new ArrayList<>();
    
    public void adaugaProgramari(Programare p) throws PreaMulteProgramariException {
    
        if(programari.size() < 70)
            this.programari.add(p);
        else
            throw new PreaMulteProgramariException("Prea multe programari");
    }
    
    public void afisare(String specialitate){
    
        for(Programare p : this.programari){
        
            if(p.getSpecialitate().equals(specialitate) == true && 
            p instanceof Consultatie){
                System.out.println(p);
            }
            if(p.getSpecialitate().equals(specialitate) == true && 
            p instanceof Interventie){
                System.out.println(p);
            }
        }
    }
    
    public void afisareAlfabetic(){
    
        Programare[] array = new Programare[this.programari.size()];
        int i,k = 0,j;
        Programare aux;
        
        for(Programare p : this.programari){
        
            array[k] = p;
            k++;
        }
        
        for(i = 0;i < k;i++){
        
            for(j = i+1;j < k;j++)
            if(array[i].getNumePacient().compareTo(array[j].getNumePacient()) >0){
            
                aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
            
        }
        for(i = 0;i<k;i++){
        if( array[i] instanceof Consultatie)
            System.out.println(array[i]);
            
        if( array[i] instanceof Interventie)
            System.out.println(array[i]);
        }
    }
    
    public void afisareMedic(String pacient) throws PacientInexistentException{
        String s = "";
        int contor = 0;
        for(Programare p : programari){
        
            if(p.getNumePacient().equals(pacient) == true){
                
                contor = 1;
                s = p.getNumeMedic();
            }
        }

        if(contor == 1){
        System.out.println(s);
            
        } else
         throw new PacientInexistentException("pacientul nu exista");
        
    }
}