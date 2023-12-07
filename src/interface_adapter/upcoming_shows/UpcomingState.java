package interface_adapter.upcoming_shows;

import java.util.LinkedHashMap;

/**
 * The UpcomingState class represents the state of the upcoming shows functionality in the application.
 * It encapsulates information such as postal code, postal code errors, and upcoming shows data.
 */
public class UpcomingState {

    /**
     * The postal code associated with the upcoming shows state.
     */
    private String postalCode = "";

    /**
     * The error message related to postal code, if any.
     */
    private String postalCodeError = null;

    /**
     * A LinkedHashMap containing upcoming shows data, where the key is the concert name and the value is the event URL.
     */
    private LinkedHashMap<String, String> upcomingShows = new LinkedHashMap<>();

    /**
     * Constructs a new UpcomingState by copying the values from another UpcomingState.
     *
     * @param copy The UpcomingState to copy values from.
     */
    public UpcomingState(UpcomingState copy) {
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;
        upcomingShows = copy.upcomingShows;
    }

    /**
     * Constructs an empty UpcomingState.
     */
    public UpcomingState() {
    }

    /**
     * Gets the postal code associated with the upcoming shows state.
     *
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the error message related to postal code, if any.
     *
     * @return The postal code error message.
     */
    public String getPostalCodeError() {
        return postalCodeError;
    }

    /**
     * Sets the postal code for the upcoming shows state.
     *
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the error message related to postal code.
     *
     * @param postalCodeError The postal code error message to set.
     */
    public void setPostalCodeError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    /**
     * Sets the error message related to upcoming shows.
     *
     * @param postalCodeError The upcoming shows error message to set.
     */
    public void setUpcomingShowsError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }
}
