package interface_adapter.similar_artist;
import interface_adapter.ViewManagerModel;
import use_case.similar_artist_venue.SimilarOutputBoundary;
import use_case.similar_artist_venue.SimilarOutputData;

public class SimilarArtistPresenter implements SimilarOutputBoundary {

    private final SimilarArtistViewModel similarViewModel;
    private final ViewManagerModel viewManagerModel;

    public SimilarArtistPresenter(ViewManagerModel viewManagerModel,
                            SimilarArtistViewModel similarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.similarViewModel = similarViewModel;
    }

    @Override
    public void prepareSuccessView(SimilarOutputData response) {
        SimilarArtistState similarArtistState = similarViewModel.getState();
        similarArtistState.setSimilarArtists(response.getSimilarArtists());
        this.similarViewModel.setState(similarArtistState);
        this.similarViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(similarViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        SimilarArtistState similarArtistState = similarViewModel.getState();
        similarArtistState.setSimilarArtistError(error);
        similarViewModel.firePropertyChanged();
    }


}
