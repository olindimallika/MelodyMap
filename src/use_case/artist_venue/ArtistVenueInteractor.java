package use_case.artist_venue;

import data_access.ArtistStrategy;
import entity.*;
import org.json.JSONObject;
import use_case.EventStrategy;
import use_case.upcoming_shows.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


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
            UserFactory userFactory = new UserModelFactory();
            ArtistFactory artistFactory = new ArtistModelFactory();

            List<String> artists = artistVenueInputData.getFavouriteArtist();

            for (String string : artists) {
                artistFactory.create(string);

            }
//            UserBuilder builder = new UserBuilder();
//            User user = builder.addPostalCode(artistVenueInputData.getPostalCode()).build();
//            ArtistFactory artistFactory = new A
//            ArrayList<Artist> favArtists = artistVenueInputData.getFavouriteArtist();
//            UserFactory factory = new UserFactory() {
//                @Override
//                public User create(String postalCode, ArrayList<Artist> favouriteArtist) {
//                    return null;
//                }
//            };
//            User user = builder.addPostalCode(artistVenueInputData.getPostalCode()).build();
//            ArrayList<Artist> favArtists = artistVenueInputData.getFavouriteArtist();

//
//            for (Artist artist : favArtists) {
//                ArtistFactory artistFactory = new ArtistModelFactory();
//                Artist recentArtist = artistFactory.create(artist.getName());
//            }
            EventStrategy artistStrategy = new ArtistStrategy();
//            artistStrategy.getEvents(artist)
            List<List<JSONObject>> events = artistStrategy.getEvents();



        }
    }
}
//             if (artistVenueDataAccessObject.artistEvents(postalCode, favouriteArtists).isEmpty()) {
////                artistPresenter.prepareFailView("Sorry none of your favourite artists have shows playing near you.");
//                    if (userDataAccessObject.existsInCoords(upcomingInputData.getPostalCode())){
//                        userPresenter.prepareFailView("Unable to find coordinates for postal code.");
//                    } else {
//                        System.out.println("\n");
//                        int radius = 10;
//                        String unit = "miles";
//
//                        UserBuilder builder = new UserBuilder();
//                        User user = builder.addPostalCode(upcomingInputData.getPostalCode()).build();
//                        List<JSONObject> eventL = userDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);
//
//                        LinkedHashMap<String, String> upcomingShowMap = userDataAccessObject.getUpcomingShows(eventL);
//                        String upcomingShows = userDataAccessObject.formatShows(upcomingShowMap);
//
//                        UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
//                        userPresenter.prepareSuccessView(upcomingOutputData);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//
////        public class UpcomingInteractor implements UpcomingInputBoundary {
////            final UpcomingDataAccess userDataAccessObject;
////            final UpcomingOutputBoundary userPresenter;
////            final UserFactory userFactory;
////
////            public UpcomingInteractor(UpcomingDataAccess userDataAccessInterface,
////                                      UpcomingOutputBoundary upcomingOutputBoundary,
////                                      UserFactory userFactory){
////                this.userDataAccessObject = userDataAccessInterface;
////                this.userPresenter = upcomingOutputBoundary;
////                this.userFactory = userFactory;
////            }
////
////            public void execute(UpcomingInputData upcomingInputData){
////
////                try {
////                    if (userDataAccessObject.existsInCoords(upcomingInputData.getPostalCode())){
////                        userPresenter.prepareFailView("Unable to find coordinates for postal code.");
////                    } else {
////                        System.out.println("\n");
////                        int radius = 10;
////                        String unit = "miles";
////
////                        UserBuilder builder = new UserBuilder();
////                        User user = builder.addPostalCode(upcomingInputData.getPostalCode()).build();
////                        List<JSONObject> eventL = userDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);
////
////                        LinkedHashMap<String, String> upcomingShowMap = userDataAccessObject.getUpcomingShows(eventL);
////                        String upcomingShows = userDataAccessObject.formatShows(upcomingShowMap);
////
////                        UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
////                        userPresenter.prepareSuccessView(upcomingOutputData);
////                    }
////                } catch (Exception e) {
////                    e.printStackTrace();
////                    throw new RuntimeException(e);
////                }
////            }
////        }
//
//
////        if (artistVenueDataAccessObject.getLatLong(postalCode) == coordinates) {
////            artistPresenter.prepareFailView("This is an invalid Canadian Postal Code. Please try again.");
////        } else {
////            if (artistVenueDataAccessObject.artistEvents(postalCode, favouriteArtists).isEmpty()) {
////                artistPresenter.prepareFailView("Sorry none of your favourite artists have shows playing near you.");
////            } else { //There are upcoming events
//////                for (TestArtist artist : favouriteArtists) {
//////                    String artistName = artist.getName();
//////                iterate through artistVenueDataAccessObject.artistEvents(postalCode, favouriteArtists)
////                // get artist name, and get their events create hashmap with artist name as key, value is an array list of events
////                ArtistVenueOutputData artistVenuesData = new ArtistVenueOutputData();
////
////                // Adding events for artists
////                //artistVenuesData.addEvent("Artist1", new ArtistVenueOutputData.Event("Event1"));
////                // ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(new);
////                //THIS ISNT CORRECT
////                }
////            }
////        }
////    }
//
//
//
