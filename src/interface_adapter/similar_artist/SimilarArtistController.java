package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;


public class SimilarArtistController {
    private SimilarInputBoundary userSimilarUseCaseInteractor;

    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;

    }

    public void execute(String postalCode, String favouriteArtists) throws Exception {
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
    }
}