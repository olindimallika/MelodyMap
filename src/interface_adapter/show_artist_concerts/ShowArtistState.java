package interface_adapter.show_artist_concerts;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The `ShowArtistState` class represents the state of information related to displaying
 * an artist's concerts. It includes a LinkedHashMap containing details of all concerts
 * and an error message for handling exceptional cases.
 */
public class ShowArtistState {

    /**
     * A LinkedHashMap containing details of all concerts, where keys are artist names
     * and values are lists of corresponding concert details.
     */
    private LinkedHashMap<String, List<String>> allConcerts = new LinkedHashMap<>();

    /**
     * Error message associated with the retrieval of all concerts.
     */
    private String allConcertsError = null;

    /**
     * Constructs a `ShowArtistState` by copying the contents of another `ShowArtistState` instance.
     *
     * @param copy The `ShowArtistState` instance to be copied.
     */
    public ShowArtistState(ShowArtistState copy) {
        allConcerts = copy.allConcerts;
    }

    /**
     * Default constructor for creating an empty `ShowArtistState`.
     */
    public ShowArtistState() {

    }

    /**
     * Retrieves the details of all concerts from the state.
     *
     * @return A LinkedHashMap containing details of all concerts.
     */
    public LinkedHashMap<String, List<String>> getAllConcerts() {
        return allConcerts;
    }

    /**
     * Sets the details of all concerts in the state.
     *
     * @param allConcerts A LinkedHashMap containing details of all concerts.
     */
    public void setAllConcerts(LinkedHashMap<String, List<String>> allConcerts) {
        this.allConcerts = allConcerts;
    }

    /**
     * Sets the error message associated with the retrieval of all concerts.
     *
     * @param allConcertsError The error message for all concerts.
     */
    public void setAllConcertsError(String allConcertsError) {
        this.allConcertsError = allConcertsError;
    }
}
