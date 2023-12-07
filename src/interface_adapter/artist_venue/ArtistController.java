package interface_adapter.artist_venue;

import use_case.artist_venue.ArtistVenueInputBoundary;
import use_case.artist_venue.ArtistVenueInputData;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * The `ArtistController` class serves as the controller for managing interactions
 * between the user interface and the use case involving artists and venues.
 * It delegates requests to the corresponding use case interactor.
 */

public class ArtistController {

    /**
     * The use case interactor responsible for handling artist and venue-related actions.
     */
    final ArtistVenueInputBoundary userArtistVenueUseCaseInteractor;

    /**
     * Constructs an `ArtistController` with the specified use case interactor.
     *
     * @param userArtistVenueUseCaseInteractor The use case interactor to be associated with this controller.
     */

    public ArtistController(ArtistVenueInputBoundary userArtistVenueUseCaseInteractor) {
        this.userArtistVenueUseCaseInteractor = userArtistVenueUseCaseInteractor;
    }

    /**
     * Executes the artist and venue-related action based on the provided artist tours information.
     * Converts the input data into a format suitable for the use case and delegates the execution
     * to the associated use case interactor.
     *
     * @param artistTours A LinkedHashMap representing artist tours information where keys are artist names
     *                    and values are corresponding venue names.
     * @throws IOException If an I/O error occurs during the execution of the use case.
     */
    public void execute(LinkedHashMap<String, String> artistTours) throws IOException {

        // Create input data from the provided artist tours information
        ArtistVenueInputData artistVenueInputData = new ArtistVenueInputData(artistTours);

        // Delegate the execution to the associated use case interactor
        userArtistVenueUseCaseInteractor.execute(artistVenueInputData);
    }

}