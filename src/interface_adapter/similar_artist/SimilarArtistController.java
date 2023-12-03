package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;
import use_case.similar_artist_venue.SimilarOutputBoundary;

import java.util.ArrayList;
import java.util.Scanner;

public class SimilarArtistController {
    final SimilarInputBoundary userSimilarUseCaseInteractor;
    private SimilarOutputBoundary userSimilarUseCaseOutputBoundary;

    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;
//        this.userSimilarUseCaseOutputBoundary = userSimilarUseCaseOutputBoundary;
    }

    public void execute(String postalCode, ArrayList<String> favouriteArtists){
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
//        userSimilarUseCaseOutputBoundary.displaySimilarArtists();
    }
}

