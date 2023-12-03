package use_case.similar_artist_venue;

import java.util.List;

public class SimilarInputData {
    private final String favArtists;
    private final String postalCode;

    public SimilarInputData(String postalCode, String favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }

    String getPostalCode(){ return postalCode;}
    String getFavArtists() {
        return favArtists;
    }
}
