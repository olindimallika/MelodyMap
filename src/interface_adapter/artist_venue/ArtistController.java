package interface_adapter.artist_venue;

import use_case.artist_venue.ArtistVenueInputBoundary;
import use_case.artist_venue.ArtistVenueInputData;

import java.io.IOException;

public class ArtistController {

    final ArtistVenueInputBoundary userArtistVenueUseCaseInteractor;

    public ArtistController(ArtistVenueInputBoundary userArtistVenueUseCaseInteractor) {
        this.userArtistVenueUseCaseInteractor = userArtistVenueUseCaseInteractor;
    }
    public void execute() throws IOException {
        userArtistVenueUseCaseInteractor.execute();
    }

}