package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

/**
 * Presenter class responsible for updating the user interface after notifying about favorite artists on tour.
 * It communicates with the corresponding view models and the view manager to handle UI updates.
 */
public class NotifyPresenter implements NotifyOutputBoundary {

    /**
     * The view model for the notification functionality.
     */
    private final NotifyViewModel notifyViewModel;

    /**
     * The view model for the artist and venue information.
     */
    private final ArtistViewModel artistViewModel;

    /**
     * The view manager responsible for handling transitions between different views.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new NotifyPresenter with the provided view manager, artist view model, and notify view model.
     *
     * @param viewManagerModel The view manager responsible for handling view transitions.
     * @param artistViewModel The view model for displaying artist and venue information.
     * @param notifyViewModel The view model for handling notifications.
     */
    public NotifyPresenter(ViewManagerModel viewManagerModel,
                           ArtistViewModel artistViewModel,
                           NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.notifyViewModel = notifyViewModel;
    }

    /**
     * Updates the UI with the successful notification response.
     *
     * @param response The output data containing information about favorite artists on tour.
     */
    @Override
    public void prepareSuccessView(NotifyOutputData response){

        // Update artist state with information about artists on tour
        ArtistState artistState = artistViewModel.getState();
        artistState.setArtistsOnTour(response.getArtistOnTour());

        // Update artist view model and notify UI about the change
        this.artistViewModel.setState(artistState);
        this.artistViewModel.firePropertyChanged();

        // Activate the artist view in the view manager
        viewManagerModel.setActiveView(artistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    /**
     * Updates the UI in case of a notification failure.
     *
     * @param error The error message indicating the cause of the failure.
     */
    @Override
    public void prepareFailView(String error){

        // Update notify state with the notification failure error
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setArtistOnTourError(error);

        // Notify UI about the failure
        notifyViewModel.firePropertyChanged();
    }
}