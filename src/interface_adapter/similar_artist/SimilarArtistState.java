package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimilarArtistState {
    private String postalCode = "";
    private String postalCodeError = null;
    private String finalFormatSimilarArtist = "";
    private String favouriteArtistError = null;
    private HashMap<String, List<String>> similarArtists = new HashMap<>();
    private String similarArtistError = null;
    private String favouriteArtists = "";
    private String finalFormatOutputSimilarArtist = "";

    public SimilarArtistState(SimilarArtistState copy) {
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;
        similarArtists = new HashMap<>(copy.similarArtists);
        similarArtistError = copy.similarArtistError;
        favouriteArtists = copy.favouriteArtists;
//        favouriteArtists = new ArrayList<>(copy.favouriteArtists); // Copy the favourite artists
        favouriteArtistError = copy.favouriteArtistError;
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

    public String getFavouriteArtists() { // Change the return type to List<String>
        return favouriteArtists;
    }

    public void setFavouriteArtists(String favouriteArtists) { // Add this method
        this.favouriteArtists = favouriteArtists;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public String getPostalCodeError(){
        return postalCodeError;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public void setPostalCodeError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }
    public String getFinalFormatSimilarArtist() {
        return finalFormatSimilarArtist;
    }
    public void setFinalFormatSimilarArtist(String finalFormatSimilarArtist) {
        this.finalFormatSimilarArtist = finalFormatSimilarArtist;
    }
    @Override
    public String toString() {
        return "SignupState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    public void setFavouriteArtistsError(String error) {
        this.favouriteArtistError = favouriteArtistError;
    }

    public String getFormatOutputPresale() {
        return finalFormatOutputSimilarArtist;
    }
    public void setFinalFormat(String finalFormatOutputSimilarArtist) {
        this.finalFormatOutputSimilarArtist = finalFormatOutputSimilarArtist;
    }

}
