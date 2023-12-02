package interactor;

import use_case.similar_artist_venue.SimilarOutputBoundary;
import use_case.similar_artist_venue.SimilarOutputData;

public class SimilarOutputBoundaryStub implements SimilarOutputBoundary {
    private SimilarOutputData outputData;

    @Override
    public void displaySimilarArtists(SimilarOutputData similarArtistsData) {
        this.outputData = similarArtistsData;
    }

    @Override
    public void displayError(String errorMessage) {
        // For testing, you can just print the error message
        System.out.println(errorMessage);
    }

    public SimilarOutputData getOutputData() {
        return this.outputData;
    }
}
