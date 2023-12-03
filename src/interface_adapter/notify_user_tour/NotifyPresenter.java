package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_menu_tour.ArtistMenuState;
import interface_adapter.artist_menu_tour.ArtistMenuViewModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

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
        artistMenuState.setArtistOnTour(response.getArtistOnTour());
        // artistMenuState.setArtistNotOnTour(response.getArtistOnTour()); want method for not on tour
        this.artistMenuViewModel.setState(artistMenuState);
        this.artistMenuViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(artistMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
//        NotifyState notifyState = notifyViewModel.getState();
//        notifyState.setArtistOnTour(response.getArtistOnTour());
//        this.notifyViewModel.setState(notifyState);
//        this.notifyViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(notifyViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();


        // ShowConcertsState showConcertsState = showConcertsViewModel.getState();
        //        showConcertsState.setConcerts(response.getUpcomingConcerts());
        //        this.showConcertsViewModel.setState(showConcertsState);
        //        this.showConcertsViewModel.firePropertyChanged();
        //
        //        viewManagerModel.setActiveView(showConcertsViewModel.getViewName());
        //        viewManagerModel.firePropertyChanged();


    @Override
    public void prepareFailView(String error){
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setArtistOnTourError(error);
        notifyViewModel.firePropertyChanged();
    }
}
