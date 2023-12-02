package interactor;

import entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import use_case.similar_artist_venue.SimilarInteractor;
import use_case.similar_artist_venue.SimilarInputData;
import use_case.similar_artist_venue.SimilarOutputData;

import entity.UserFactory;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SimilarArtistTest {
    @Test
    @Test
    public void testExecute() {
        // Create stub objects
        SimilarDataAccessStub dataAccessStub = new SimilarDataAccessStub();
        SimilarOutputBoundaryStub outputBoundaryStub = new SimilarOutputBoundaryStub();
        UserFactory realUserFactory = new UserFactory() {
            @Override
            public User create(String postalCode, ArrayList<String> favouriteArtist) {
                return null;
            }
        }; // replace with your actual implementation

        // Create a SimilarInteractor object with the stub objects
        SimilarInteractor interactor = new SimilarInteractor(dataAccessStub, outputBoundaryStub, realUserFactory);

        // Create some test data
        ArrayList<String> favArtists = new ArrayList<>();
        favArtists.add("Taylor Swift");
        favArtists.add("Selena Gomez");
        String postalCode = "M8V1B7";
        SimilarInputData inputData = new SimilarInputData(postalCode, favArtists);

        // Call the execute method
        interactor.execute(inputData);

        // Check the output
        SimilarOutputData outputData = outputBoundaryStub.getOutputData();
        assertNotNull(outputData);
        HashMap<String, ArrayList<String>> similarArtists = outputData.getSimilarArtists();
        assertFalse(similarArtists.isEmpty());
    }

}
