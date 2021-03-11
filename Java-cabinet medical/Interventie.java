public class Interventie extends Programare{

    int zile;
    
     public Interventie(String nume_pacient,String nume_medic, String specialitate,int zile){
        super(nume_pacient, nume_medic,specialitate);
        this.zile = zile;
        }
        
     public void setZile(int zile){
        this.zile = zile;
     }
     
     public int getZile(){
         return this.zile;
     }
     
      @Override
     public String toString(){
     
         return "Numele pacientului este " + this.getNumePacient() + 
         " si numele medicului este " + this.getNumeMedic() +" are specialitate "
         + this.getSpecialitate() + " perioada de recuperare este de  " +
         this.getZile();
     }
    
}