package use_case.similar_artist_venue;

import java.util.List;

public interface SimilarInputBoundary {
    void enterFavouriteArtists(List<String> artists);
    void execute(SimilarInputData similarInputData);
}
