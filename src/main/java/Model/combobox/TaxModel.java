package Model.combobox;

public class TaxModel {
    public TaxModel(){}
    
    public TaxModel(int taxId, String taxName){
        this.taxId = taxId;
        this.taxName = taxName;
    }
    
    public int getTaxId(){
        return taxId;
    }
    
     public void setTaxId(int taxId){
        this.taxId = taxId;
    }
     
     public String getTaxName(){
         return taxName;
     }
     
     public void setTaxName(String taxName){
         this.taxName = taxName; 
     }
     
     private int taxId;
     private String taxName;
}
