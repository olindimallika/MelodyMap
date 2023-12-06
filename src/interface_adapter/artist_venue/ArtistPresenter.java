package interface_adapter.artist_venue;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.upcoming_shows.UpcomingState;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import use_case.artist_venue.ArtistVenueOutputBoundary;
import use_case.artist_venue.ArtistVenueOutputData;
import use_case.upcoming_shows.UpcomingOutputData;

public class ArtistPresenter implements ArtistVenueOutputBoundary {

    private final ArtistViewModel artistViewModel;
    //private final before and after view goes here so on tour and not on tour and presale

    private final ViewManagerModel viewManagerModel;

    private final NotifyViewModel notifyViewModel;

    public ArtistPresenter(ViewManagerModel viewManagerModel, ArtistViewModel artistViewModel, NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.notifyViewModel = notifyViewModel;
    }

    @Override
    public void prepareSuccessView(ArtistVenueOutputData response) {
        ArtistState artistState = artistViewModel.getState();  /// same as previous
        this.artistViewModel.setState(artistState);
        this.artistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(artistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

        ArtistState artistState = artistViewModel.getState();
        artistState.setArtistShowsError(error);
        artistViewModel.firePropertyChanged();
    }
}
