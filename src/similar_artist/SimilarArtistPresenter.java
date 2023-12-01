package similar_artist;

public class SimilarArtistPresenter implements SimilarOutputBoundary{

    private final SimilarViewModel similarViewModel;
    private final ViewManagerModel viewManagerModel;

    public SimilarArtistPresenter(ViewManagerModel viewManagerModel,
                            SimilarViewModel similarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.similarViewModel = similarViewModel;
    }

    @Override
    public void prepareSuccessView(SimilarOutputData response) {
        SimilarState
    }

}
