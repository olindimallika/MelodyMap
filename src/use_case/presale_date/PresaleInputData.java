package use_case.presale_date;

import entity.Artist;

import java.util.ArrayList;
public class PresaleInputData {
    final private String postalCode;
    final private String favArtists;
    //final private String favouriteArtistName;
    public PresaleInputData(String postalCode, String favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }
    public String getPostalCode() {

        return postalCode;
    }
    public String getFavArtists() {

        return favArtists;
    }

}
