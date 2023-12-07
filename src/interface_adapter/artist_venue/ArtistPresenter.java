package interface_adapter.artist_venue;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_artist_concerts.ShowArtistState;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.upcoming_shows.UpcomingState;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import use_case.artist_venue.ArtistVenueOutputBoundary;
import use_case.artist_venue.ArtistVenueOutputData;
import use_case.upcoming_shows.UpcomingOutputData;

public class ArtistPresenter implements ArtistVenueOutputBoundary {

    private final ArtistViewModel artistViewModel;

    private final ShowArtistViewModel showArtistViewModel;
    //private final before and after view goes here so on tour and not on tour and presale

    private final ViewManagerModel viewManagerModel;

    public ArtistPresenter(ViewManagerModel viewManagerModel, ArtistViewModel artistViewModel,
                           ShowArtistViewModel showArtistViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.showArtistViewModel = showArtistViewModel;
    }

    @Override
    public void prepareSuccessView(ArtistVenueOutputData response) {
        ShowArtistState showArtistState = showArtistViewModel.getState();
        showArtistState.setAllConcerts(response.getUpcomingArtistShows());
        this.showArtistViewModel.setState(showArtistState);
        this.showArtistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showArtistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

//        ArtistState artistState = artistViewModel.getState();  /// same as previous
//        artistState.setArtistShows(response.getUpcomingArtistShows()); //getuPCOMING
//        //        showConcertsState.setConcerts(response.getUpcomingConcerts());
//        this.artistViewModel.setState(artistState);
//        this.artistViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(artistViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

        ArtistState artistState = artistViewModel.getState();
        artistState.setArtistShowsError(error);
        artistViewModel.firePropertyChanged();
    }
}