package use_case.similar_artist_venue;

public interface SimilarOutputBoundary {
    public void prepareSuccessView(SimilarOutputData similarArtistsData);
    public void prepareFailView(String errorMessage);
}
