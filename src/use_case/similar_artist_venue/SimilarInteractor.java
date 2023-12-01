package use_case.similar_artist_venue;

import entity.UserFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimilarInteractor implements SimilarInputBoundary{

    final SimilarDataAccess userDataAccessObject;
    final SimilarOutputBoundary userPresenter;
    final UserFactory userFactory;
    private List<String> favouriteArtists;

    public SimilarInteractor(SimilarDataAccess userDataAccessInterface, SimilarOutputBoundary similarOutputBoundary,
                             UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = similarOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void enterFavouriteArtists(List<String> artists) {
        // Store the favourite artists in a field in this class
        this.favouriteArtists = artists;
    }

    public List<String> getFavArtists() {
        // Return the favourite artists
        return this.favouriteArtists;
    }

    @Override
    public void execute(SimilarInputData similarInputData) {
        try {
            // Get the user's favorite artists and postal code from the input data
            List<String> favouriteArtists = similarInputData.getFavArtists();
            String postalCode = similarInputData.getPostalCode();

            // Use the data access object to get the similar artists
            HashMap<String, ArrayList<String>> similarArtists = userDataAccessObject.getSimilarArtists(favouriteArtists);

            if (similarArtists != null && !similarArtists.isEmpty()) {
                // If similar artists were found, prepare the success view
                SimilarOutputData outputData = new SimilarOutputData(similarArtists);
                userPresenter.displaySimilarArtists(outputData);
            } else {
                // If no similar artists were found, prepare the fail view
                userPresenter.displayError("No similar artists found.");
            }
        } catch (Exception e) {
            // If an error occurred, prepare the fail view with the error message
            userPresenter.displayError(e.getMessage());
        }
    }
}
