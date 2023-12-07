package use_case.artist_venue;


/**
 * The `ArtistVenueOutputBoundary` interface defines methods for handling the output of artist and venue-related use cases.
 * Classes implementing this interface are responsible for preparing and presenting views based on the results
 * of the corresponding use case executions.
 */
public interface ArtistVenueOutputBoundary {

    /**
     * Prepares and presents the success view based on the provided upcoming concerts data.
     *
     * @param upcomingConcert The output data containing information about upcoming concerts.
     */
    void prepareSuccessView(ArtistVenueOutputData upcomingConcert);

    /**
     * Prepares and presents the fail view based on the provided error message.
     *
     * @param error The error message indicating the cause of the failure.
     */
    void prepareFailView(String error);
}
