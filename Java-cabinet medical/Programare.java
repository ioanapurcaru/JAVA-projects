public class Programare{

    private String nume_pacient;
    private String nume_medic;
    private String specialitate;
    
    public Programare(String nume_pacient,String nume_medic, String specialitate){
    
        this.nume_pacient = nume_pacient;
        this.nume_medic = nume_medic;
        this.specialitate = specialitate;
    }
    
    public void setNumePacient(String nume_pacient){
    
        this.nume_pacient = nume_pacient;
    }
    
    public void setNumeMedic(String nume_medic){
    
        this.nume_medic = nume_medic;
    }
    
    public void setSpecialitate(String specialitate){
    
        this.specialitate = specialitate;
    }
    
    public String getNumePacient(){
    
        return this.nume_pacient;
    }
    
    public String getNumeMedic(){
    
        return this.nume_medic;
    }
    
    public String getSpecialitate(){
    
        return this.specialitate;
    }
    
}