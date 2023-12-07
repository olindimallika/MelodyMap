package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;

/**
 * The `SimilarArtistPresenter` class is responsible for presenting the output of the similar artists and venues discovery
 * use case to the user interface. It collaborates with the `ViewManagerModel` to update the view and communicates with
 * the `SimilarArtistViewModel` to ensure a coherent representation of the discovered information.
 *
 * This presenter implements the `SimilarOutputBoundary` interface, defining methods to handle both successful and failed outcomes
 * of the similar artists and venues discovery use case. Upon a successful outcome, it updates the `SimilarArtistViewModel` and
 * triggers view updates through the `ViewManagerModel`. In case of failure, it conveys error messages to the `SimilarArtistViewModel`.
 *
 * The `SimilarArtistPresenter` plays a crucial role in maintaining the integrity of the user interface, ensuring that the presentation
 * accurately reflects the results and handles errors gracefully. It serves as a mediator between the underlying logic and the user interface,
 * promoting separation of concerns and modularity within the application.
 *
 * @author Kelsang Tsomo
 */
public class SimilarArtistController {

    // The use case interactor responsible for executing the similar artists and venues discovery use case
    private SimilarInputBoundary userSimilarUseCaseInteractor;

    /**
     * Constructs a `SimilarArtistController` with the provided use case interactor.
     *
     * @param userSimilarUseCaseInteractor The use case interactor responsible for handling the similar artists and venues discovery logic.
     *
     * This constructor initializes the `SimilarArtistController` with the specified use case interactor, allowing the controller
     * to delegate the execution of the similar artists and venues discovery use case to the appropriate interactor.
     */
    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;

    }

    /**
     * Executes the similar artists and venues discovery use case based on the provided user input.
     *
     * @param postalCode The postal code provided by the user for location-based search.
     * @param favouriteArtists The list of favorite artists provided by the user.
     * @throws Exception if an error occurs during the execution of the use case.
     *
     * This method serves as a bridge between the user interface and the underlying use case logic. It receives user input, creates
     * a corresponding input data object, and delegates the execution to the use case interactor. Any errors encountered during
     * the execution are propagated as exceptions.
     */
    public void execute(String postalCode, String favouriteArtists) throws Exception {
        // Creating input data object from user input
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
    }
}