package similar_artist;

import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInputData;
import use_case.similar_artist_venue.SimilarOutputBoundary;

import java.util.ArrayList;
import java.util.Scanner;

public class SimilarArtistController {
    private SimilarInputBoundary userSimilarUseCaseInteractor;
    private SimilarOutputBoundary userSimilarUseCaseOutputBoundary;
    private Scanner scanner = new Scanner(System.in);

    public SimilarArtistController(SimilarInputBoundary userSimilarUseCaseInteractor, SimilarOutputBoundary userSimilarUseCaseOutputBoundary) {
        this.userSimilarUseCaseInteractor = userSimilarUseCaseInteractor;
        this.userSimilarUseCaseOutputBoundary = userSimilarUseCaseOutputBoundary;
    }

    public void execute(String postalCode, ArrayList<String> favouriteArtists){
        SimilarInputData similarInputData = new SimilarInputData(postalCode, favouriteArtists);
        userSimilarUseCaseInteractor.execute(similarInputData);
        userSimilarUseCaseOutputBoundary.displaySimilarArtists();
    }
}

//    public void getFavouriteArtistsFromUser() {
//        ArrayList<String> favouriteArtists = new ArrayList<>();
//
//        System.out.println("Enter your favourite artists and type done when you are finished: ");
//
//        while(true) {
//            String check = scanner.nextLine();
//
//            if (check.equalsIgnoreCase("done")) {
//                break;
//            }
//            else {
//                favouriteArtists.add(check);
//            }
//        }
//        similarInputBoundary.enterFavouriteArtists(favouriteArtists);
//    }

