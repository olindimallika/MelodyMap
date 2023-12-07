package interface_adapter.artist_venue;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The `ArtistState` class represents the state of artist-related information, including artists on tour
 * and details of artist shows. It also includes error messages for handling exceptional cases.
 */
public class ArtistState {

    /**
     * A LinkedHashMap containing information about artists currently on tour,
     * where keys are artist names and values are corresponding venue names.
     */
    private LinkedHashMap<String, String> artistsOnTour = new LinkedHashMap<>();

    /**
     * A LinkedHashMap containing details of artist shows, where keys are artist names
     * and values are lists of corresponding concert details.
     */
    private LinkedHashMap<String, List<String>> artistShows = new LinkedHashMap<>();

    /**
     * Error message associated with the retrieval of artists currently on tour.
     */
    private String artistsOnTourError = null;

    /**
     * Error message associated with the retrieval of artist show details.
     */
    private String artistShowsError = null;

    /**
     * Constructs an `ArtistState` by copying the contents of another `ArtistState` instance.
     *
     * @param copy The `ArtistState` instance to be copied.
     */
    public ArtistState(ArtistState copy) {
        artistsOnTour = copy.artistsOnTour;
        artistsOnTourError = copy.artistsOnTourError;
        artistShows = copy.artistShows;
        artistShowsError = copy.artistShowsError;
    }

    /**
     * Default constructor for creating an empty `ArtistState`.
     */
    public ArtistState() {

    }

    /**
     * Sets the artists currently on tour in the state.
     *
     * @param artistsOnTour A LinkedHashMap containing information about artists on tour.
     */
    public void setArtistsOnTour(LinkedHashMap<String, String> artistsOnTour) {
        this.artistsOnTour = artistsOnTour;
    }

    /**
     * Retrieves the artists currently on tour from the state.
     *
     * @return A LinkedHashMap containing information about artists on tour.
     */
    public LinkedHashMap<String, String> getArtistsOnTour() {
        return artistsOnTour;
    }

    /**
     * Retrieves the error message associated with the retrieval of artists on tour.
     *
     * @return The error message for artists on tour, or null if no error occurred.
     */
    public String getArtistsOnTourError() {
        return artistsOnTourError;
    }

    /**
     * Retrieves the details of artist shows from the state.
     *
     * @return A LinkedHashMap containing details of artist shows.
     */
    public LinkedHashMap<String, List<String>> getArtistShows() {
        return artistShows;
    }

    /**
     * Retrieves the error message associated with the retrieval of artist show details.
     *
     * @return The error message for artist shows, or null if no error occurred.
     */
    public String getArtistShowsError() {
        return artistShowsError;
    }

    /**
     * Sets the details of artist shows in the state.
     *
     * @param artistShows A LinkedHashMap containing details of artist shows.
     */
    public void setArtistShows(LinkedHashMap<String, List<String>> artistShows) {
        this.artistShows = artistShows;
    }

    /**
     * Sets the error message associated with the retrieval of artist show details.
     *
     * @param artistShowsError The error message for artist shows.
     */
    public void setArtistShowsError(String artistShowsError) {
        this.artistShowsError = artistShowsError;
    }
}
