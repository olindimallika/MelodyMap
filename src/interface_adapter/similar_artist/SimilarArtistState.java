package interface_adapter.similar_artist;

import java.util.HashMap;
import java.util.List;

/**
 * The `SimilarArtistState` class represents the state of the user interface related to the similar artists and venues functionality.
 * It encapsulates information such as postal code, errors related to postal code input, a formatted representation of similar artists,
 * errors related to favorite artists, a map of similar artists, errors related to similar artists, and the list of favorite artists.
 * Instances of this class serve as a comprehensive container for maintaining and manipulating the state of the UI within the application.
 *
 * This class provides methods to access and modify various elements of the state, ensuring the integrity of the information stored.
 * Additionally, it includes a copy constructor, allowing the creation of new instances with the same state as an existing object.
 *
 * Note: It is assumed that this class operates within the context of a larger application where it interfaces with other components
 * involved in the similar artists and venues feature. The functionality and behavior of this class are closely tied to the corresponding
 * user interface and business logic layers of the application.
 *
 * @author Kelsang Tsomo
 */
public class SimilarArtistState {
    private String postalCode = "";
    private String postalCodeError = null;
    private HashMap<String, String> finalFormatSimilarArtist = new HashMap<>();
    private String favouriteArtistError = null;
    private HashMap<String, List<String>> similarArtists = new HashMap<>();
    private String similarArtistError = null;
    private String favouriteArtists = "";

    /**
     * Constructs a new `SimilarArtistState` object with the same state as the provided `copy` instance.
     *
     * @param copy The `SimilarArtistState` instance to copy the state from.
     *
     * This constructor facilitates the creation of a new object with the same state as an existing `SimilarArtistState`.
     * It ensures that the state is copied in a manner that preserves the immutability of the original object.
     */
    public SimilarArtistState(SimilarArtistState copy) {
        postalCode = copy.postalCode;
        postalCodeError = copy.postalCodeError;
        similarArtists = new HashMap<>(copy.similarArtists);
        similarArtistError = copy.similarArtistError;
        favouriteArtists = copy.favouriteArtists;
        favouriteArtistError = copy.favouriteArtistError;
    }

    /**
     * Default constructor for the `SimilarArtistState` class.
     *
     * This constructor creates an instance of `SimilarArtistState` with default values.
     */
    public SimilarArtistState() {
    }

    /**
     * Retrieves the map of similar artists associated with their respective venues.
     *
     * @return A `HashMap` containing artist names as keys and lists of venue names as values.
     *
     * This method allows external components to access the current mapping of similar artists to their associated venues.
     */
    public HashMap<String, List<String>> getSimilarArtists() {
        return similarArtists;
    }


    /**
     * Sets the map of similar artists associated with their respective venues.
     *
     * @param similarArtists A `HashMap` containing artist names as keys and lists of venue names as values.
     *
     * This method allows external components to update the mapping of similar artists within the state.
     */
    public void setSimilarArtists(HashMap<String, List<String>> similarArtists) {
        this.similarArtists = similarArtists;
    }


    /**
     * Sets the error message related to similar artists.
     *
     * @param similarArtistError A `String` representing the error message related to similar artists.
     *
     * This method allows external components to set or update the error message related to similar artists within the state.
     */
    public void setSimilarArtistError(String similarArtistError) {
        this.similarArtistError = similarArtistError;
    }

    /**
     * Retrieves the error message related to similar artists, if any.
     *
     * @return A `String` representing the error message related to similar artists.
     *
     * This method provides access to the error message associated with similar artists, allowing for error handling and presentation.
     */
    public String getSimilarArtistError() {
        return similarArtistError;
    }

    /**
     * Retrieves the list of favorite artists.
     *
     * @return A `String` representing the list of favorite artists.
     *
     * This method allows external components to access the current list of favorite artists stored in the state.
     */
    public String getFavouriteArtists() { // Change the return type to List<String>
        return favouriteArtists;
    }

    /**
     * Sets the list of favorite artists.
     *
     * @param favouriteArtists A `String` representing the list of favorite artists.
     *
     * This method allows external components to update the list of favorite artists within the state.
     */
    public void setFavouriteArtists(String favouriteArtists) { // Add this method
        this.favouriteArtists = favouriteArtists;
    }

    /**
     * Retrieves the postal code stored in the state.
     *
     * @return A `String` representing the postal code.
     *
     * This method allows external components to access the current postal code stored in the state.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Retrieves the postal code error message.
     *
     * @return The postal code error message.
     */
    public String getPostalCodeError(){
        return postalCodeError;
    }

    /**
     * Sets the postal code value.
     *
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**
     * Sets the postal code error message.
     *
     * @param postalCodeError The postal code error message to set.
     */
    public void setPostalCodeError(String postalCodeError) {
        this.postalCodeError = postalCodeError;
    }

    /**
     * Retrieves the final formatted similar artists map.
     *
     * @return The map containing final formatted similar artist information.
     */
    public HashMap<String, String> getFinalFormatSimilarArtist() {
        return finalFormatSimilarArtist;
    }

    /**
     * Sets the final formatted similar artists map.
     *
     * @param finalFormatSimilarArtist The map containing final formatted similar artist information.
     */
    public void setFinalFormatSimilarArtist(HashMap<String, String> finalFormatSimilarArtist) {
        this.finalFormatSimilarArtist = finalFormatSimilarArtist;
    }

    /**
     * Provides a string representation of the SignupState object.
     *
     * @return A string containing information about the SignupState object.
     */
    @Override
    public String toString() {
        return "SignupState{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }

    /**
     * Sets the error message for favorite artists.
     *
     * @param error The error message for favorite artists.
     */
    public void setFavouriteArtistsError(String error) {
        this.favouriteArtistError = favouriteArtistError;
    }

}