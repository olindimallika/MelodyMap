package use_case.presale_date;


import java.util.ArrayList;
import java.util.List;

public class PresaleOutputData {
    private String finalFormatOutputPresale = "";
    //private List<String>
    private List<String> presaleDates = new ArrayList<>();
    private List<String> eventUrls = new ArrayList<>();

    public PresaleOutputData(String finalFormatOutputPresale){
         this.finalFormatOutputPresale = finalFormatOutputPresale;

    }

    public String getFormatOutputPresale() {
        return finalFormatOutputPresale;
    }

    public List<String> getEventUrls() {
        return eventUrls;
    }
    public List<String> getPresaleDates() {
        return presaleDates;
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
