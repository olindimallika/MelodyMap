package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
//
public class NotifyPresenter implements NotifyOutputBoundary {
    private final NotifyViewModel notifyViewModel;
    private final ArtistViewModel artistViewModel;
    private final ViewManagerModel viewManagerModel;

    public NotifyPresenter(ViewManagerModel viewManagerModel,
                           ArtistViewModel artistViewModel,
                           NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.notifyViewModel = notifyViewModel;
    }

    @Override
    public void prepareSuccessView(NotifyOutputData response){

        // switch to artist on success
        ArtistState artistState = artistViewModel.getState();
//        artistState.setArtistsOnTour(response.getArtistOnTour());
        artistState.setArtistsOnTour(response.getArtistOnTour());


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