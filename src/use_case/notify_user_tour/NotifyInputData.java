package use_case.notify_user_tour;

/**
 * The {@code NotifyInputData} class represents the input data for the "Notify User Tour" use case.
 * It encapsulates information about the user's favorite artist names.
 */
public class NotifyInputData {

    /**
     * The favorite artist names provided as input.
     */
    private final String favouriteArtistNames;

    /**
     * Constructs a new {@code NotifyInputData} object with the specified favorite artist names.
     *
     * @param favouriteArtistNames The names of the user's favorite artists.
     */
    public NotifyInputData(String favouriteArtistNames) {
        this.favouriteArtistNames = favouriteArtistNames;
    }

    /**
     * Gets the favorite artist names from the input data.
     *
     * @return The names of the user's favorite artists.
     */
    public String getFavouriteArtistNames() {
        return favouriteArtistNames;
    }
}
