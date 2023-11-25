package use_case.artist_venue;

import entity.TestArtist;

import java.util.ArrayList;

public class ArtistVenueInputData {

    final private String postalCode;
    final private ArrayList<TestArtist> favouriteArtist;

    public ArtistVenueInputData(String postalCode, ArrayList<TestArtist> favouriteArtist) {
        this.postalCode = postalCode;
        this.favouriteArtist = favouriteArtist;
    }

    String getPostalCode() {

        return postalCode;
    }

    ArrayList<TestArtist> getFavouriteArtist() {

        return favouriteArtist;
    }
}





