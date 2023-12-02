package use_case.presale_date;

import entity.Artist;

import java.util.ArrayList;
public class PresaleInputData {

    final private String postalCode;
    //final private ArrayList<Artist> favouriteArtist;
    final private String favouriteArtistName;

    public PresaleInputData(String postalCode, String favouriteArtistName) {
        this.postalCode = postalCode;
        this.favouriteArtistName = favouriteArtistName;
    }
    String getPostalCode() {

        return postalCode;
    }
    String getFavouriteArtistName() {

        return favouriteArtistName;
    }

}
