package use_case.artist_venue;

import use_case.upcoming_shows.UpcomingInputData;

import java.io.IOException;

public interface ArtistVenueInputBoundary {

    void execute() throws IOException;
}