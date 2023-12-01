package use_case.similar_artist_venue;

public interface SimilarOutputBoundary {
    void displaySimilarArtists(SimilarOutputData similarArtistsData);
    void displayError(String errorMessage);
}
