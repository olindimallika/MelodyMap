package use_case.similar_artist_venue;

import java.util.List;

public class SimilarInputData {
    private String favArtists;
    private String postalCode;

    public SimilarInputData(String postalCode, String favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }

    public SimilarInputData() {

    }

    String getPostalCode(){ return postalCode;}
    String getFavArtists() {
        return favArtists;
    }
}
