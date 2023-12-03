package interface_adapter.artist_menu_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyState;
import use_case.artist_menu_tour.ArtistMenuTourOutputBoundary;
import use_case.artist_menu_tour.ArtistMenuTourOutputData;

public class ArtistMenuPresenter implements ArtistMenuTourOutputBoundary {

    private final ArtistMenuViewModel artistMenuViewModel;
    private final ArtistViewModel artistViewModel;

    private ViewManagerModel viewManagerModel;

    public ArtistMenuPresenter(ViewManagerModel viewManagerModel, ArtistMenuViewModel artistMenuViewModel,
                               ArtistViewModel artistViewModel){
        this.viewManagerModel = viewManagerModel;
        this.artistViewModel = artistViewModel;
        this.artistMenuViewModel = artistMenuViewModel;
    }

    //
    //    @Override
    //    public void prepareSuccessView(ShowConcertsOutputData response) {
    //        // On success, we want to switch to the notify view
    //
    //        NotifyState notifyState = notifyViewModel.getState();
    //        this.notifyViewModel.setState(notifyState);
    //        this.notifyViewModel.firePropertyChanged();
    //
    //        viewManagerModel.setActiveView(notifyViewModel.getViewName());
    //        viewManagerModel.firePropertyChanged();
    //    }
    //
    //    @Override
    //    public void prepareFailView(String error) {
    //        ShowConcertsState showConcertsState = showConcertsViewModel.getState();
    //        showConcertsState.setConcertsError(error);
    //        showConcertsViewModel.firePropertyChanged();
    //    }
    @Override
    public void prepareSuccessView(ArtistMenuTourOutputData response) {

        // on success we want to switch to artist view or similar artist view
        ArtistState artistState = artistViewModel.getState();
        this.artistViewModel.setState(artistState);
        this.artistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(artistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

        // Artists don't exist
        ArtistMenuState artistMenuState = artistMenuViewModel.getState();
        artistMenuState.setArtistMenuError(error);
        artistMenuViewModel.firePropertyChanged();

    }
}
