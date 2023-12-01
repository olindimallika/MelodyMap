package use_case.similar_artist_venue;

import java.util.HashMap;
import java.util.ArrayList;

public class SimilarOutputData {
    private final HashMap<String, ArrayList<String>> similarArtists;

    public SimilarOutputData(HashMap<String, ArrayList<String>> similarArtists) {
        this.similarArtists = similarArtists;
    }

    public HashMap<String, ArrayList<String>> getSimilarArtists() {
        return similarArtists;
    }
}
