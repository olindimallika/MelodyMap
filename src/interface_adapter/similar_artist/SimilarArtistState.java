package interface_adapter.similar_artist;

import entity.Artist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimilarArtistState {
    private String postalCode = "";
    private HashMap<String, List<String>> similarArtists = new HashMap<>();
    private String similarArtistError = null;
    public SimilarArtistState(SimilarArtistState copy) {
        similarArtists = copy.similarArtists;
        similarArtistError = copy.similarArtistError;
    }
    public SimilarArtistState() {

    }
    public HashMap<String, List<String>> getSimilarArtists() {
        return similarArtists;
    }
    public void setSimilarArtists(HashMap<String, List<String>> similarArtists) {
        this.similarArtists = similarArtists;
    }
    public void setSimilarArtistError(String similarArtistError) {
        this.similarArtistError = similarArtistError;
    }
    public String getSimilarArtistError() {
        return similarArtistError;
    }

}
