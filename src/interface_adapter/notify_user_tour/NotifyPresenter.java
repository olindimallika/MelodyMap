package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

public class NotifyPresenter implements NotifyOutputBoundary {
    private final NotifyViewModel notifyViewModel;
    private final ViewManagerModel viewManagerModel;

    private final ArtistViewModel artistViewModel;

    public NotifyPresenter(ViewManagerModel viewManagerModel, NotifyViewModel notifyViewModel,
                           ArtistViewModel artistViewModel){
        this.viewManagerModel = viewManagerModel;
        this.notifyViewModel = notifyViewModel;
        this.artistViewModel = artistViewModel;
    }

    @Override
    public void prepareSuccessView(NotifyOutputData response){

        ArtistState artistState = artistViewModel.getState();
        artistState.setArtistShows(response.getArtistOnTour());
        this.artistViewModel.setState(artistState);
        this.artistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(artistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setArtistOnTourError(error);
        notifyViewModel.firePropertyChanged();
    }
}
