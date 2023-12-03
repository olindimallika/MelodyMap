package use_case.artist_venue;

import entity.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistVenueInputData {

    final private String postalCode;
    final private String favouriteArtist;

    public ArtistVenueInputData(String postalCode, String favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }

    String getPostalCode() {

        return postalCode;
    }

    String getFavouriteArtist() {

        return favouriteArtist;
    }
}








