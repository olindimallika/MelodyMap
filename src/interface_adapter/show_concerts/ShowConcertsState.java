package interface_adapter.show_concerts;

import java.util.LinkedHashMap;

public class ShowConcertsState {
    private LinkedHashMap<String, String> concerts = new LinkedHashMap<>();
    private String concertsError = null;
    public ShowConcertsState(ShowConcertsState copy){
        concerts = copy.concerts;
    }

    public ShowConcertsState(){
    }

    public LinkedHashMap<String, String> getConcerts(){
        return concerts;
    }
    public String getConcertsError(){
        return concertsError;
    }

    public void setConcerts(LinkedHashMap<String, String> concerts){
        this.concerts = concerts;
    }

    public void setConcertsError(String concertsError){
        this.concertsError = concertsError;
    }
}
