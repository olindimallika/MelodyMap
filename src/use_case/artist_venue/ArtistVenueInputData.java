package use_case.artist_venue;

import entity.Artist;

import java.util.ArrayList;

public class ArtistVenueInputData {

    final private String postalCode;
    final private ArrayList<Artist> favouriteArtist;

    public ArtistVenueInputData(String postalCode, ArrayList<Artist> favouriteArtist) {
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








