package use_case.artist_venue;

import java.io.IOException;

/**
 * The `ArtistVenueInputBoundary` interface defines the contract for input boundaries
 * in the context of artist and venue-related use cases. Classes implementing this interface
 * are responsible for handling input and orchestrating the execution of the corresponding use case.
 */
public interface ArtistVenueInputBoundary {

    /**
     * Executes the artist and venue-related use case based on the provided input data.
     *
     * @param artistVenueInputData The input data containing information for the use case execution.
     * @throws IOException If an I/O error occurs during the execution of the use case.
     */
    void execute(ArtistVenueInputData artistVenueInputData) throws IOException;
}
