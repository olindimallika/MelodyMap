package use_case.artist_venue;

import entity.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistVenueInputData {

    final private String postalCode;
    final private List<String> favouriteArtist;

    public ArtistVenueInputData(String postalCode, List<String> favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }

    String getPostalCode() {

        return postalCode;
    }

    List<String> getFavouriteArtist() {

        return favouriteArtist;
    }
}








