package use_case.presale_date;


public class PresaleOutputData {
    private String finalFormatOutputPresale = "";

    public PresaleOutputData(String finalFormatOutputPresale){
         this.finalFormatOutputPresale = finalFormatOutputPresale;

    }

    public String getFormatOutputPresale() {
        return finalFormatOutputPresale;
    }

//    private final List<String> presaleDates;
//
//    public PresaleOutputData(List<String> presaleDates){
//        this.presaleDates = presaleDates;
//    }
//
//    public List<String> getPresaleDates(){
//        return presaleDates;
//    }




}
