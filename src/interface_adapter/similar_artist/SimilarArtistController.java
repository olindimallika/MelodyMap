package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;
import use_case.similar_artist_venue.SimilarOutputBoundary;

import java.util.List;

public class SimilarArtistController {
    private SimilarInputBoundary userSimilarUseCaseInteractor;
    private SimilarOutputBoundary userSimilarUseCaseOutputBoundary;

    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor, SimilarOutputBoundary userSimilarUseCaseOutputBoundary) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;
        this.userSimilarUseCaseOutputBoundary = userSimilarUseCaseOutputBoundary;
    }

    public void execute(String postalCode, String favouriteArtists) throws Exception {
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
    }
}
