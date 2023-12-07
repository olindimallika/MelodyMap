package interface_adapter.upcoming_shows;

import java.util.LinkedHashMap;

public class UpcomingState {
    private String postalCode = "";
    private String postalCodeError = null;
    private LinkedHashMap<String, String> upcomingShows = new LinkedHashMap<>();

    public UpcomingState(UpcomingState copy){
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;
        upcomingShows = copy.upcomingShows;
    }

    public UpcomingState(){
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

    public void setUpcomingShowsError(String postalCodeError){
        this.postalCodeError = postalCodeError;
    }


}