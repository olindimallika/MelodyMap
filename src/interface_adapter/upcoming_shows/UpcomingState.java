package interface_adapter.upcoming_shows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpcomingState {
    private String postalCode = "";
    private String postalCodeError = null;
    private String upcomingShows = "";

    public UpcomingState(UpcomingState copy){
        postalCode = copy.postalCode;
        postalCodeError= copy.postalCodeError;
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

    public String getUpcomingShows(){
        return upcomingShows;
    }

    public void setUpcomingShows(String upcomingShows) {
        this.upcomingShows = upcomingShows;
    }

    public void setUpcomingShowsError(String postalCodeError){
        this.postalCodeError = postalCodeError;
    }


    @Override
    public String toString() {
        return "SignupState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

}

