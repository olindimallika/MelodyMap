package use_case.presale_date;

import entity.Artist;

import java.util.ArrayList;
public class PresaleInputData {

    final private String postalCode;
    final private ArrayList<Artist> favouriteArtist;

    public PresaleInputData(String postalCode, ArrayList<Artist> favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }
    String getPostalCode() {

        return postalCode;
    }

    ArrayList<Artist> getFavouriteArtist() {

        return favouriteArtist;
    }

}
