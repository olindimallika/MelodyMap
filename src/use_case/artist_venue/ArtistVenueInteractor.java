package use_case.artist_venue;

import data_access.ArtistStrategy;
import data_access.EventProcesser;
import entity.*;
import org.json.JSONObject;
import use_case.EventStrategy;
import use_case.upcoming_shows.*;

import java.io.IOException;
import java.util.*;


public class ArtistVenueInteractor implements ArtistVenueInputBoundary {
    final ArtistVenueDataAccess artistVenueDataAccessObject;
    final EventStrategy eventStrategy;

    final ArtistVenueOutputBoundary artistPresenter;
    final UserFactory userFactory;

    public ArtistVenueInteractor(ArtistVenueDataAccess artistVenueDataAccess, EventStrategy eventStrategy, ArtistVenueOutputBoundary artistPresenter, UserFactory userFactory) {
        this.artistVenueDataAccessObject = artistVenueDataAccess;
        this.eventStrategy = eventStrategy;
        this.artistPresenter = artistPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ArtistVenueInputData artistVenueInputData) throws IOException {
        try {
            String strFavArtists = artistVenueInputData.getFavouriteArtist();
            String[] artistsArray = strFavArtists.split(", ");
            List<String> artistList = Arrays.asList(artistsArray);
            ArtistFactory artistFactory = new ArtistModelFactory();
            ArrayList<Artist> favArtists = new ArrayList<>();


            for (String artistString : artistList) {
                Artist artist = artistFactory.create(artistString);
                favArtists.add(artist);
            }

            UserFactory userFactory = new UserModelFactory();
            User user = userFactory.create(artistVenueInputData.getPostalCode(), favArtists);

            ArtistStrategy artistStrategy = new ArtistStrategy();

            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
            List<List<JSONObject>> events = eventProcesser.processEvent(user);

            if (events.isEmpty()) {
                artistPresenter.prepareFailView("Sorry none of your favourite artists have shows playing near you");
            } else {
                System.out.println("\n");

                LinkedHashMap<String, String> allShows = new LinkedHashMap<>();
                for (List<JSONObject> artistEvents : events) {
                    LinkedHashMap<String, String> shows = artistVenueDataAccessObject.getUpcomingShows(artistEvents);
                    allShows.putAll(shows);
                }

                String upcomingShows = artistVenueDataAccessObject.formatShows(allShows);
                ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(upcomingShows);
                artistPresenter.prepareSuccessView(artistVenueOutputData);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


