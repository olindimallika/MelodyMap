package use_case.similar_artist_venue;

import entity.Artist;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class SimilarOutputData {
    private String finalFormatOutputSimilarArtist = "";

    private String similarArtists = "";

    public SimilarOutputData() {
        this.similarArtists = similarArtists;
    }
    public SimilarOutputData(String similarArtists) {
        this.similarArtists = similarArtists;
    }
    public String getSimilarArtists() {
        return similarArtists;
    }

    public String getFinalFormatSimilarArtist() {
        return finalFormatOutputSimilarArtist;
    }
}
