package use_case.artist_venue;

import use_case.upcoming_shows.UpcomingOutputData;

public interface ArtistVenueOutputBoundary {

    public void prepareSuccessView(ArtistVenueOutputData upcomingConcert);

    public void prepareFailView(String error);

}
