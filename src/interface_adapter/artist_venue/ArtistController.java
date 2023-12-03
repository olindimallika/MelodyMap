//package interface_adapter.artist_venue;
//
//import use_case.artist_venue.ArtistVenueInputBoundary;
//import use_case.artist_venue.ArtistVenueInputData;
//import use_case.upcoming_shows.UpcomingInputBoundary;
//import use_case.upcoming_shows.UpcomingInputData;
//
//import java.io.IOException;
//
//public class ArtistController {
//
//    final ArtistVenueInputBoundary userArtistVenueUseCaseInteractor;
//
//    public ArtistController(ArtistVenueInputBoundary userArtistVenueUseCaseInteractor) {
//        this.userArtistVenueUseCaseInteractor = userArtistVenueUseCaseInteractor;
//    }
//    public void execute() throws IOException {
//        ArtistVenueInputData artistVenueInputData = new ArtistVenueInputData();
//
//        userArtistVenueUseCaseInteractor.execute(artistVenueInputData);
//    }
//
//}
