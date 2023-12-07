package use_case.artist_venue;

import data_access.*;
import entity.*;
import org.json.JSONObject;
import use_case.upcoming_shows.UpcomingOutputData;


import java.io.IOException;
import java.util.*;
//

public class ArtistVenueInteractor implements ArtistVenueInputBoundary {
    final ArtistVenueDataAccess userDataAccessObject;
    final ArtistVenueOutputBoundary artistPresenter;
    final UserFactory userFactory;
    final LocationFinder locationFinder;

    public ArtistVenueInteractor(ArtistVenueDataAccess artistVenueDataAccess,
                                 ArtistVenueOutputBoundary artistPresenter,
                                 UserFactory userFactory,
                                 LocationFinder locationFinder) {
        this.userDataAccessObject = artistVenueDataAccess;
        this.artistPresenter = artistPresenter;
        this.userFactory = userFactory;
        this.locationFinder = locationFinder;
    }

    @Override
    public void execute(ArtistVenueInputData artistVenueInputData) throws IOException {
        try {

            EventStrategy<List<List<JSONObject>>> artistStrategy = new ArtistStrategy();

//            ArrayList<String> artistsOnTour = new ArrayList<>(artistVenueInputData.getArtistTours().keySet());

            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(artistVenueInputData.getArtistTours().keySet());

            ArtistFactory artistFactory = new ArtistModelFactory();
            ArrayList<Artist> favArtists = new ArrayList<>();

            for (String artistString : arrayList) {
                Artist artist = artistFactory.create(artistString);
                favArtists.add(artist);
            }

            UserFactory userFactory = new UserModelFactory();
            User user = userFactory.create("L1C0K1", favArtists);

            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
            List<List<JSONObject>> eventsList = eventProcesser.processEvent(user);
            LinkedHashMap<String, List<String>> upcomingArtistShows = userDataAccessObject.getUpcomingArtistShows(eventsList);

            ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(upcomingArtistShows);
            artistPresenter.prepareSuccessView(artistVenueOutputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
