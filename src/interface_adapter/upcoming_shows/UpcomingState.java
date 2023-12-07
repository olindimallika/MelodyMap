package interface_adapter.upcoming_shows;

import java.util.LinkedHashMap;

/**
 * The UpcomingState class represents the state of the upcoming shows functionality in the application.
 * It encapsulates information such as postal code, postal code errors, and upcoming shows data.
 */
public class UpcomingState {

    /**
     * The postal code entered by the user.
     */
    private String postalCode = "";

    /**
     * An error message related to the postal code input.
     */
    private String postalCodeError = null;

    /**
     * A mapping of upcoming shows where the key is the show name, and the value is the associated link.
     */
    private LinkedHashMap<String, String> upcomingShows = new LinkedHashMap<>();

    /**
     * An error message related to the postal code input.
     */
    private String upcomingShowsError = null;

    /**
     * Constructs a new UpcomingState by copying the values from another UpcomingState instance.
     *
     * @param copy The UpcomingState instance to copy values from.
     */
    public UpcomingState(UpcomingState copy){
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;
        upcomingShows = copy.upcomingShows;
        upcomingShowsError = copy.upcomingShowsError;
    }

    /**
     * Constructs a new, empty UpcomingState.
     */
    public UpcomingState(){
    }

    /**
     * Gets the postal code stored in the UpcomingState.
     *
     * @return The postal code entered by the user.
     */
    public String getPostalCode(){
        return postalCode;
    }

    /**
     * Gets the error message related to the postal code input.
     *
     * @return The error message for the postal code.
     */
    public String getPostalCodeError(){
        return postalCodeError;
    }

    /**
     * Sets the postal code in the UpcomingState.
     *
     * @param postalCode The postal code entered by the user.
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**
     * Sets the error message related to the postal code input.
     *
     * @param postalCodeError The error message for the postal code.
     */
    public void setPostalCodeError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    /**
     * Sets the error message for upcoming shows.
     *
     * @param upcomingShowsError The error message for upcoming shows.
     */
    public void setUpcomingShowsError(String upcomingShowsError){
        this.upcomingShowsError = upcomingShowsError;
    }


}