package interface_adapter.similar_artist;
import interface_adapter.ViewManagerModel;
import use_case.similar_artist_venue.SimilarOutputBoundary;
import use_case.similar_artist_venue.SimilarOutputData;

/**
 * The `SimilarArtistPresenter` class plays a pivotal role in the user interface layer, specifically in presenting
 * the outcomes of the similar artists and venues discovery use case to the application's views. Collaborating with the
 * `ViewManagerModel`, it efficiently manages views and communicates with the `SimilarArtistViewModel` to update the state
 * and data representation of the similar artists and venues UI.
 *
 * This presenter implements the `SimilarOutputBoundary` interface, defining methods to handle both successful and failed outcomes
 * of the similar artists and venues discovery use case. In the case of a successful outcome, it adeptly updates the `SimilarArtistViewModel`
 * with the final formatted output, triggers view updates through the `ViewManagerModel`, and ensures a seamless transition to the
 * relevant view. If the operation encounters a failure, it conveys precise error messages to the `SimilarArtistViewModel`,
 * allowing for proper handling and presentation of the encountered issues.
 *
 * The `SimilarArtistPresenter` encapsulates the logic for transforming use case outputs into a user-friendly format and orchestrates
 * the necessary updates to the user interface. By maintaining a clear separation of concerns, it significantly contributes to the modularity
 * and maintainability of the application architecture.
 *
 * Note: This class assumes the existence of a `SimilarArtistViewModel` class and a `ViewManagerModel` class within the specified package.
 * The `SimilarArtistViewModel` represents the state and data for the similar artists and venues UI, and the `ViewManagerModel` manages
 * different views within the application.
 *
 * @author Kelsang Tsomo
 */
public class SimilarArtistPresenter implements SimilarOutputBoundary {

    // The view model representing the state and data for the similar artists and venues UI
    private final SimilarArtistViewModel similarViewModel;

    // The model responsible for managing different views within the application
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a `SimilarArtistPresenter` with the given `ViewManagerModel` and `SimilarArtistViewModel`.
     *
     * @param viewManagerModel The model responsible for managing different views within the application.
     * @param similarViewModel The view model representing the state and data for the similar artists and venues UI.
     *
     * This constructor initializes the `SimilarArtistPresenter` with the required components to interact with the user interface.
     * It establishes connections with the `ViewManagerModel` for view management and the `SimilarArtistViewModel` for state and data representation.
     */
    public SimilarArtistPresenter(ViewManagerModel viewManagerModel,
                                  SimilarArtistViewModel similarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.similarViewModel = similarViewModel;
    }

    /**
     * Prepares the view for a successful outcome of the similar artists and venues discovery use case.
     *
     * @param response The data containing the results of the successful operation, including information about
     *                 similar artists and their associated venues.
     *
     * This method is invoked when the execution of the use case is successful. It updates the `SimilarArtistViewModel`,
     * triggers view updates through the `ViewManagerModel`, and ensures a clear and coherent representation for the user.
     */
    @Override
    public void prepareSuccessView(SimilarOutputData response) {
        SimilarArtistState similarArtistState = similarViewModel.getState();
        similarArtistState.setFinalFormatSimilarArtist(response.getFormatOutputSimilarArtist());
        this.similarViewModel.setState(similarArtistState);
        this.similarViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(similarViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed outcome of the similar artists and venues discovery use case.
     *
     * @param error A message explaining the cause of the failure.
     *
     * This method is called when the execution of the use case encounters an issue or fails to produce meaningful results.
     * It conveys error messages to the `SimilarArtistViewModel`, allowing it to handle and present the encountered problem.
     */
    public void prepareFailView(String error) {
        SimilarArtistState similarArtistState = similarViewModel.getState();
        similarArtistState.setPostalCodeError(error);
        similarArtistState.setFavouriteArtistsError(error);
        similarArtistState.setSimilarArtistError(error);
        similarViewModel.firePropertyChanged();
    }


}