package interface_adapter.show_artist_concerts;

import interface_adapter.ViewManagerModel;
import use_case.show_artist_concerts.ShowArtistOuputBoundary;

/**
 * The `ShowArtistPresenter` class acts as a presenter for displaying information about an artist's concerts,
 * implementing the `ShowArtistOuputBoundary` interface to handle output from the corresponding use case.
 * It prepares and updates the view associated with showing artist concerts.
 */
public class ShowArtistPresenter implements ShowArtistOuputBoundary {

    /**
     * The view model responsible for managing the state and presentation of artist concerts.
     */
    private final ShowArtistViewModel showArtistViewModel;

    /**
     * The model for managing the state and changes in different views.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a `ShowArtistPresenter` with the specified view manager model and show artist view model.
     *
     * @param viewManagerModel      The model responsible for managing views and their transitions.
     * @param showArtistViewModel   The view model for displaying information about an artist's concerts.
     */
    public ShowArtistPresenter(ViewManagerModel viewManagerModel, ShowArtistViewModel showArtistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.showArtistViewModel = showArtistViewModel;
    }

    /**
     * Prepares and updates the success view by switching to the "Olindis" view.
     * Updates the state of the show artist view model and notifies observers about the change.
     */
    @Override
    public void prepareSuccessView() {
        // on success switch to Olindis

        ShowArtistState showArtistState = showArtistViewModel.getState();
        this.showArtistViewModel.setState(showArtistState);
        this.showArtistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showArtistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the fail view in case of an error during the show artist use case execution.
     * Sets the error message in the show artist view model and notifies observers about the change.
     *
     * @param error The error message indicating the cause of the failure.
     */
    @Override
    public void prepareFailView(String error) {
        ShowArtistState showArtistState = showArtistViewModel.getState();
        showArtistState.setAllConcertsError(error);
        showArtistViewModel.firePropertyChanged();
    }
}
