package interface_adapter.presale;

import use_case.presale_date.PresaleInputData;

import java.util.ArrayList;
import java.util.List;

public class PresaleState {
    private String postalCode = "";
    private String postalCodeError = null;
    private List<String> presaleDates = new ArrayList<>();

    //from notify
    private String favouriteArtist = "";
    private String favouriteArtistError = null;



    public PresaleState(PresaleState copy){
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;

        //notify
        favouriteArtist = copy.favouriteArtist;
        favouriteArtistError = copy.favouriteArtistError;

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

    public List<String> getPresaleDates(){
        return presaleDates;
    }

    public void setPresale(List<String> presaleDates) {
        this.presaleDates = presaleDates;
    }

    public void setPresaleError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    //notify
    public String getFavouriteArtist(){
        return favouriteArtist;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    public void setFavouriteArtist(String favouriteArtist) {
        this.favouriteArtist = favouriteArtist;
    }

    public String getFavouriteArtistError(){
        return favouriteArtistError;
    }

    public void setFavouriteArtistPresaleError(String favouriteArtistError){
        this.favouriteArtistError = favouriteArtistError;
    }

//
}
