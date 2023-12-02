package use_case.presale_date;

import entity.Artist;

import java.util.ArrayList;
import java.util.List;

public class PresaleInputData {

//    final private String postalCode;
//    final private ArrayList<Artist> favouriteArtist;
//
//    public PresaleInputData(String postalCode, ArrayList<Artist> favouriteArtist) {
//        this.postalCode = postalCode;
//        this.favouriteArtist = favouriteArtist;
//    }
//    String getPostalCode() {
//
//        return postalCode;
//    }
//
//    ArrayList<Artist> getFavouriteArtist() {
//
//        return favouriteArtist;
//    }

    //This use case does not have any input, it only extracts
    // different properties of events and displays them.

    private List<String> presaleDates;
    private List<String> eventUrls;

    public PresaleInputData(){
    }

    public List<String> getEventUrls() {
        return eventUrls;
    }
    public List<String> getPresaleDates() {
        return presaleDates;
    }




}
