package use_case.similar_artist_venue;

import java.util.HashMap;

public class SimilarOutputData {
    private HashMap<String, String> finalFormatOutputSimilarArtist = new HashMap<>();

    public SimilarOutputData(HashMap<String, String> finalFormatOutputSimilarArtist) {
        this.finalFormatOutputSimilarArtist = finalFormatOutputSimilarArtist;
    }

    public HashMap<String, String> getFormatOutputSimilarArtist() {
        return finalFormatOutputSimilarArtist;
    }

}