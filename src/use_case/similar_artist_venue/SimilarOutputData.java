package use_case.similar_artist_venue;

import entity.Artist;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class SimilarOutputData {
    private String finalFormatOutputSimilarArtist = "";

    private HashMap<String, List<String>> similarArtists = new HashMap<>();

    public SimilarOutputData() {
        this.similarArtists = similarArtists;
    }
    public SimilarOutputData(HashMap<String, List<String>> similarArtists) {
        this.similarArtists = similarArtists;
    }
    public HashMap<String, List<String>> getSimilarArtists() {
        return similarArtists;
    }

    public String getFormatOutputSimilarArtist() {
        return finalFormatOutputSimilarArtist;
    }
}
