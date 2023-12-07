package interface_adapter.show_artist_concerts;

import interface_adapter.ViewManagerModel;
import use_case.show_artist_concerts.ShowArtistOuputBoundary;

public class ShowArtistPresenter implements ShowArtistOuputBoundary {

    private final ShowArtistViewModel showArtistViewModel;

    private final ViewManagerModel viewManagerModel;

    public ShowArtistPresenter(ViewManagerModel viewManagerModel, ShowArtistViewModel showArtistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.showArtistViewModel = showArtistViewModel;
    }

    @Override
    public void prepareSuccessView() {

        // on success switch to olindis

        ShowArtistState showArtistState = showArtistViewModel.getState();
        this.showArtistViewModel.setState(showArtistState);
        this.showArtistViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showArtistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

        ShowArtistState showArtistState = showArtistViewModel.getState();
        showArtistState.setAllConcertsError(error);
        showArtistViewModel.firePropertyChanged();

    }

}

