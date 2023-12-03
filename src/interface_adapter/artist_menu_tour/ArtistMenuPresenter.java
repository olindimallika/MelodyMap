package interface_adapter.artist_menu_tour;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyState;
import use_case.artist_menu_tour.ArtistMenuTourOutputBoundary;
import use_case.artist_menu_tour.ArtistMenuTourOutputData;

public class ArtistMenuPresenter implements ArtistMenuTourOutputBoundary {

    private final ArtistMenuViewModel artistMenuViewModel;
//    private final ArtistViewModel artistViewModel;

    private ViewManagerModel viewManagerModel;

    public ArtistMenuPresenter(ViewManagerModel viewManagerModel, ArtistMenuViewModel artistMenuViewModel){
        this.viewManagerModel = viewManagerModel;
//        this.artistViewModel = artistViewModel;
        this.artistMenuViewModel = artistMenuViewModel;
    }


    @Override
    public void prepareSuccessView(ArtistMenuTourOutputData response) {

        // on success we want to switch to artist view or similar artist view
        ArtistMenuState artistState = artistMenuViewModel.getState();
        this.artistMenuViewModel.setState(artistState);
        this.artistMenuViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(artistMenuViewModel.getViewName());
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
