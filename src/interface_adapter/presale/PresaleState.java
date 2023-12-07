package interface_adapter.presale;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code PresaleState} class represents the state of the presale view.
 * It holds information related to postal code, postal code errors, presale dates, event URLs,
 * final format output for presale, favorite artists, and favorite artists errors.
 */
public class PresaleState {

    /**
     * The postal code associated with the presale view state.
     */
    private String postalCode = "";

    /**
     * The error message associated with the postal code input.
     */
    private String postalCodeError = null;

    /**
     * The list of presale dates retrieved from the presale use case.
     */
    private List<String> presaleDates = new ArrayList<>();

    /**
     * The list of event URLs associated with the presale dates.
     */
    private List<String> eventUrls = new ArrayList<>();

    /**
     * The final formatted output for the presale view.
     */
    private String finalFormatOutputPresale = "";

    // Notify related properties

    /**
     * The favorite artists associated with the presale view state.
     */
    private String favArtists = "";

    /**
     * The error message associated with the favorite artists input.
     */
    private String favArtistsError = null;

    /**
     * Constructs a new instance of {@code PresaleState} by copying the state from another instance.
     *
     * @param copy The instance to copy the state from.
     */
    public PresaleState(PresaleState copy) {
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;

        // Notify related properties
        favArtists = copy.favArtists;
        favArtistsError = copy.favArtistsError;
    }

    /**
     * Constructs a new instance of {@code PresaleState}.
     */
    public PresaleState() {
    }

    /**
     * Gets the postal code associated with the presale view state.
     *
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the error message associated with the postal code input.
     *
     * @return The postal code error message.
     */
    public String getPostalCodeError() {
        return postalCodeError;
    }

    /**
     * Sets the postal code associated with the presale view state.
     *
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the error message associated with the postal code input.
     *
     * @param postalCodeError The postal code error message to set.
     */
    public void setPostalCodeError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    /**
     * Gets the final formatted output for the presale view.
     *
     * @return The final format output.
     */
    public String getFormatOutputPresale() {
        return finalFormatOutputPresale;
    }

    /**
     * Sets the final formatted output for the presale view.
     *
     * @param finalFormatOutputPresale The final format output to set.
     */
    public void setFinalFormat(String finalFormatOutputPresale) {
        this.finalFormatOutputPresale = finalFormatOutputPresale;
    }

    /**
     * Sets the error message associated with the presale input.
     *
     * @param postalCodeError The presale error message to set.
     */
    public void setPresaleError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    // Notify related methods

    /**
     * Gets the favorite artists associated with the presale view state.
     *
     * @return The favorite artists.
     */
    public String getFavArtists() {
        return favArtists;
    }

    /**
     * Gets the list of presale dates retrieved from the presale use case.
     *
     * @return The list of presale dates.
     */
    public List<String> getPresaleDates() {
        return presaleDates;
    }

    /**
     * Gets the list of event URLs associated with the presale dates.
     *
     * @return The list of event URLs.
     */
    public List<String> getEventUrls() {
        return eventUrls;
    }

    /**
     * Overrides the default {@code toString} method to provide a string representation of the presale state.
     *
     * @return A string representation of the presale state.
     */
    @Override
    public String toString() {
        return "PresaleState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    /**
     * Sets the favorite artists associated with the presale view state.
     *
     * @param favArtists The favorite artists to set.
     */
    public void setFavArtists(String favArtists) {
        this.favArtists = favArtists;
    }

    /**
     * Gets the error message associated with the favorite artists input.
     *
     * @return The favorite artists error message.
     */
    public String getFavArtistsError() {
        return favArtistsError;
    }

    /**
     * Sets the error message associated with the favorite artists input.
     *
     * @param favArtistsError The favorite artists error message to set.
     */
    public void setFavouriteArtistPresaleError(String favArtistsError) {
        this.favArtistsError = favArtistsError;
    }
}