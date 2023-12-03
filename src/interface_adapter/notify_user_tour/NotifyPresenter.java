package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_menu_tour.ArtistMenuState;
import interface_adapter.artist_menu_tour.ArtistMenuViewModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NotifyPresenter implements NotifyOutputBoundary {
    private final NotifyViewModel notifyViewModel;
    private final ViewManagerModel viewManagerModel;

    private final ArtistMenuViewModel artistMenuViewModel;

    public NotifyPresenter(ViewManagerModel viewManagerModel, NotifyViewModel notifyViewModel,
                           ArtistMenuViewModel artistMenuViewModel){
        this.viewManagerModel = viewManagerModel;
        this.notifyViewModel = notifyViewModel;
        this.artistMenuViewModel = artistMenuViewModel;
    }

    @Override
    public void prepareSuccessView(NotifyOutputData response) {
        ArtistMenuState artistMenuState = artistMenuViewModel.getState();

        LinkedHashMap<String, String> favArtist = response.getFavouriteArtists();
        ArrayList<String> artistOnTour = new ArrayList<>();
        ArrayList<String> artistNotOnTour = new ArrayList<>();

        for (Map.Entry<String, String> entry : favArtist.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.equals("has a tour")) {
                artistOnTour.add(key);
            } else {
                artistNotOnTour.add(key);
            }
        }
        artistMenuState.setArtistOnTour(artistOnTour);
        artistMenuState.setArtistNotOnTour(artistNotOnTour);
        this.artistMenuViewModel.setState(artistMenuState);
        this.artistMenuViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(notifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String error){
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setArtistOnTourError(error);
        notifyViewModel.firePropertyChanged();
    }
}
