package use_case.show_artist_concerts;

/**
 * The `ShowArtistOuputBoundary` interface defines methods for handling the output of
 * the "Show Artist Concerts" use case. Classes implementing this interface are responsible
 * for preparing and presenting views based on the results of the corresponding use case executions.
 */
public interface ShowArtistOuputBoundary {

    /**
     * Prepares and presents the success view for the "Show Artist Concerts" use case.
     */
    void prepareSuccessView();

    /**
     * Prepares and presents the fail view for the "Show Artist Concerts" use case based on the provided error message.
     *
     * @param error The error message indicating the cause of the failure.
     */
    void prepareFailView(String error);
}

