public class Consultatie extends Programare{

     private int durata;
     
     public Consultatie(String nume_pacient,String nume_medic, String specialitate,int durata){
        super(nume_pacient, nume_medic,specialitate);
        this.durata = durata;
        }
        
     public void setDurata(int durata){
        this.durata = durata;
     }
     
     public int getDurata(){
         return this.durata;
     }
     
     @Override
     public String toString(){
     
         return "Numele pacientului este " + this.getNumePacient() + 
         " si numele medicului este " + this.getNumeMedic() +" are specialitate "
         + this.getSpecialitate() + " durata consultatiei este de  " +
         this.getDurata();
     }
    
}