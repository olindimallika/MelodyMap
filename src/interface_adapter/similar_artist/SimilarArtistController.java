package interface_adapter.similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;
import use_case.similar_artist_venue.SimilarOutputBoundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimilarArtistController {
    static SimilarInputBoundary userSimilarUseCaseInteractor;
    private SimilarOutputBoundary userSimilarUseCaseOutputBoundary;

    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;
//        this.userSimilarUseCaseOutputBoundary = userSimilarUseCaseOutputBoundary;
    }

    public static void execute(String postalCode, String favouriteArtists){
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
//        userSimilarUseCaseOutputBoundary.displaySimilarArtists();
    }
}

