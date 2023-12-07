package interface_adapter.show_artist_concerts;

import java.util.LinkedHashMap;
import java.util.List;

public class ShowArtistState {

    private LinkedHashMap<String, List<String>> allConcerts = new LinkedHashMap<>();
    private String allConcertsError = null;

    public ShowArtistState(ShowArtistState copy) {
        allConcerts = copy.allConcerts;

    }

    public ShowArtistState() {

    }

    public LinkedHashMap<String, List<String>> getAllConcerts(){
        return allConcerts;
    }

    public void setAllConcerts(LinkedHashMap<String, List<String>> allConcerts){
        this.allConcerts = allConcerts;
    }

    public void setAllConcertsError(String allConcertsError){
        this.allConcertsError = allConcertsError;
    }

}
