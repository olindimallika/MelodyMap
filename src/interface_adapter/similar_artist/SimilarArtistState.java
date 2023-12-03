package interface_adapter.similar_artist;

import java.util.HashMap;
import java.util.List;

public class SimilarArtistState {
    private String postalCode = "";
    private HashMap<String, List<String>> similarArtists = new HashMap<>();
    private String similarArtistError = null;
    private String favouriteArtists;

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

    public void setPostalCode(String text) {
        this.postalCode = text;
    }

    public void setFavouriteArtists(String favouriteArtists) {
        this.favouriteArtists = favouriteArtists;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getFavouriteArtists() {
        return favouriteArtists;
    }
}
