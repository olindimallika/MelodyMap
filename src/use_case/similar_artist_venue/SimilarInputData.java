package use_case.similar_artist_venue;

import java.util.List;

public class SimilarInputData {
    private final List<String> favArtists;
    private final String postalCode;

    public SimilarInputData(String postalCode, List<String> favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }

    String getPostalCode(){ return postalCode;}
    List<String> getFavArtists() {
        return favArtists;
    }
}
