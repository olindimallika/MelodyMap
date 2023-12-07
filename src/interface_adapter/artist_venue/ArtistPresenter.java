package interface_adapter.artist_venue;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_artist_concerts.ShowArtistState;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;
import use_case.artist_venue.ArtistVenueOutputBoundary;
import use_case.artist_venue.ArtistVenueOutputData;

/**
 * The `ArtistPresenter` class acts as a presenter for artist and venue-related functionality,
 * implementing the `ArtistVenueOutputBoundary` interface to handle output from use cases.
 * It prepares and updates various views associated with artist information.
 */
public class ArtistPresenter implements ArtistVenueOutputBoundary {

    /**
     * The view model responsible for managing artist-related information.
     */
    private final ArtistViewModel artistViewModel;

    /**
     * The view model for showing detailed information about an artist's concerts.
     */
    private final ShowArtistViewModel showArtistViewModel;
    //private final before and after view goes here so on tour and not on tour and presale

    /**
     * The model for managing the state and changes in different views.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs an `ArtistPresenter` with the specified view manager model, artist view model,
     * and show artist view model.
     *
     * @param viewManagerModel      The model responsible for managing views and their transitions.
     * @param artistViewModel       The view model for artist-related information.
     * @param showArtistViewModel   The view model for showing detailed information about an artist's concerts.
     */
    public ArtistPresenter(ViewManagerModel viewManagerModel, ArtistViewModel artistViewModel,
                           ShowArtistViewModel showArtistViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.showArtistViewModel = showArtistViewModel;
    }

    /**
     * Prepares and updates the success view based on the output data from the artist venue use case.
     * Sets the upcoming artist shows in the show artist view model and updates the active view accordingly.
     *
     * @param response The output data containing information about upcoming artist shows.
     */

    @Override
    public void prepareSuccessView(ArtistVenueOutputData response) {
        ShowArtistState showArtistState = showArtistViewModel.getState();
        showArtistState.setAllConcerts(response.getUpcomingArtistShows());
        this.showArtistViewModel.setState(showArtistState);
        this.showArtistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showArtistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the fail view in case of an error during the artist venue use case execution.
     * Sets the error message in the artist view model and notifies observers about the change.
     *
     * @param error The error message indicating the cause of the failure.
     */

    @Override
    public void prepareFailView(String error) {

        ArtistState artistState = artistViewModel.getState();
        artistState.setArtistShowsError(error);
        artistViewModel.firePropertyChanged();
    }
}