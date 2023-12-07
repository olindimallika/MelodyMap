package use_case.artist_venue;

import java.io.IOException;

public interface ArtistVenueInputBoundary {

    void execute(ArtistVenueInputData artistVenueInputData) throws IOException;
}