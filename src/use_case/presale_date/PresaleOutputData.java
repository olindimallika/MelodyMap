package use_case.presale_date;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class PresaleOutputData {
    private final List<String> presaleDates;

    public PresaleOutputData(List<String> presaleDates){
        this.presaleDates = presaleDates;
    }

    public List<String> getPresaleDates(){
        return presaleDates;
    }


}
