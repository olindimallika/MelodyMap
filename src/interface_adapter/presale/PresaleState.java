package interface_adapter.presale;

import java.util.ArrayList;
import java.util.List;
public class PresaleState {
    private String postalCode = "";
    private String postalCodeError = null;
    private List<String> presaleDates = new ArrayList<>();
    private List<String> eventUrls = new ArrayList<>();
    private String finalFormatOutputPresale = "";
    //private List<String> presaleuRLS = new ArrayList<>();



    //from notify
    private String favArtists = "";
    private String favArtistsError = null;



    public PresaleState(PresaleState copy){
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;

        //notify
        favArtists = copy.favArtists;
        favArtistsError = copy.favArtistsError;

    }

    public PresaleState(){
    }

    public String getPostalCode(){
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

    public String getFormatOutputPresale() {
        return finalFormatOutputPresale;
    }



//    public List<String> getPresaleDates(){
//        return presaleDates;
//    }

    public void setFinalFormat(String finalFormatOutputPresale) {
        this.finalFormatOutputPresale = finalFormatOutputPresale;
    }


//    public void setPresale(List<String> presaleDates) {
//        this.presaleDates = presaleDates;
//    }

    public void setPresaleError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    //notify
    public String getFavArtists(){
        return favArtists;
    }
    public List<String> getPresaleDates(){
        return presaleDates;
    }

    public List<String> getEventUrls(){
        return eventUrls;

    }
    @Override
    public String toString() {
        return "SignupState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    public void setFavArtists(String favArtists) {
        this.favArtists = favArtists;
    }
    public String getFavArtistsError(){
        return favArtistsError;
    }

    public void setFavouriteArtistPresaleError(String favArtistsError){
        this.favArtistsError = favArtistsError;
    }

//
}




////below is artist with String:

//public class PresaleState {
//    private String postalCode = "";
//    private String postalCodeError = null;
//    private List<String> presaleDates = new ArrayList<>();
//
//    //from notify
//    private String favouriteArtist = "";
//    private String favouriteArtistError = null;
//
//
//
//    public PresaleState(PresaleState copy){
//        postalCode = copy.postalCode;
//        postalCodeError = copy.postalCodeError;
//
//        //notify
//        favouriteArtist = copy.favouriteArtist;
//        favouriteArtistError = copy.favouriteArtistError;
//
//    }
//
//    public PresaleState(){
//    }
//
//    public String getPostalCode(){
//        return postalCode;
//    }
//
//    public String getPostalCodeError(){
//        return postalCodeError;
//    }
//
//    public void setPostalCode(String postalCode){
//        this.postalCode = postalCode;
//    }
//
//    public void setPostalCodeError(String postalCodeError) {
//        this.postalCodeError = postalCodeError;
//    }
//
//    public List<String> getPresaleDates(){
//        return presaleDates;
//    }
//
//    public void setPresale(List<String> presaleDates) {
//        this.presaleDates = presaleDates;
//    }
//
//    public void setPresaleError(String postalCodeError) {
//        this.postalCodeError = postalCodeError;
//    }
//
//    //notify
//    public String getFavouriteArtist(){
//        return favouriteArtist;
//    }
//
//    @Override
//    public String toString() {
//        return "SignupState{" +
//                "postalCode='" + postalCode + '\'' +
//                '}';
//    }
//
//    public void setFavouriteArtist(String favouriteArtist) {
//        this.favouriteArtist = favouriteArtist;
//    }
//
//    public String getFavouriteArtistError(){
//        return favouriteArtistError;
//    }
//
//    public void setFavouriteArtistPresaleError(String favouriteArtistError){
//        this.favouriteArtistError = favouriteArtistError;
//    }
//
////
//}
