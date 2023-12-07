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
        ArtistState artistState = artistViewModel.getState();

//        LinkedHashMap<String, String> shows = response.getArtistOnTour();
//        LinkedHashMap<String, String> artistsOnTour = new LinkedHashMap<>();
//
//        for (Map.Entry<String, String> entry : shows.entrySet()) {
//
//            if (entry.getValue().equals("has a tour")) {
//                artistsOnTour.put(entry.getKey(), entry.getValue());
//            }
////            else {
////                String key = entry.getKey();
////                artistsNotOnTour.add(key);
////            }
//        }
//
//        artistState.setArtistShows(artistsOnTour);
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
