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
//    final EventStrategy eventStrategy;

    final ArtistVenueOutputBoundary artistPresenter;
    final UserFactory userFactory;

    public ArtistVenueInteractor(ArtistVenueDataAccess artistVenueDataAccess,
                                 ArtistVenueOutputBoundary artistPresenter, UserFactory userFactory) {
        this.artistVenueDataAccessObject = artistVenueDataAccess;
//        this.eventStrategy = eventStrategy;
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
            List<List<JSONObject>> eventsList = eventProcesser.processEvent(user);

            if (eventsList.isEmpty()) {
                artistPresenter.prepareFailView("Sorry none of your favourite artists have shows playing near you");
            } else {
                System.out.println("\n");

//                LinkedHashMap<String, List<String>> upcomingShows = artistVenueDataAccessObject.getArtistShows(events);
//                System.out.println(upcomingShows);
//                ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(upcomingShows);
//                artistPresenter.prepareSuccessView(artistVenueOutputData);


                LinkedHashMap<String, List<String>> artistShowsMap = new LinkedHashMap<>();

                for (List<JSONObject> events : eventsList) {
                    for (JSONObject event : events) {
                        String artistName = event.getString("name");
                        String eventUrl = event.getString("url");

                            // Check if the artist is already in the map
                        if (artistShowsMap.containsKey(artistName)) {
                            // If yes, add the eventUrl to the existing list of shows
                            List<String> artistShows = artistShowsMap.get(artistName);
                            artistShows.add(eventUrl);
                        } else {
                                // If no, create a new list with the current eventUrl
                            List<String> artistShows = new ArrayList<>();
                            artistShows.add(eventUrl);
                            artistShowsMap.put(artistName, artistShows);
                        }
                    }
                }

                System.out.println(artistShowsMap);
                ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(artistShowsMap);
                artistPresenter.prepareSuccessView(artistVenueOutputData);
                }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
//}


//    public static void main(String[] args) {
//        try {
//            // You can create an instance of your dependencies here if needed
//            String strFavArtists = "Olivia Rodrigo, Eagles, Noah Kahan";
//            String[] artistsArray = strFavArtists.split(", ");
//            List<String> artistList = Arrays.asList(artistsArray);
//            ArtistFactory artistFactory = new ArtistModelFactory();
//            ArrayList<Artist> favArtists = new ArrayList<>();
//
//
//            for (String artistString : artistList) {
//                Artist artist = artistFactory.create(artistString);
//                favArtists.add(artist);
//            }
//
//            UserFactory userFactory = new UserModelFactory();
//            User user = userFactory.create("L1C0K1", favArtists);
//
//            ArtistStrategy artistStrategy = new ArtistStrategy();
//
//            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
//            List<List<JSONObject>> events = eventProcesser.processEvent(user);
//
//            LinkedHashMap<String, String> allShows = new LinkedHashMap<>();
//            for (List<JSONObject> artistEvents : events) {
//                LinkedHashMap<String, String> shows = getUpcomingShows(artistEvents);
//                allShows.putAll(shows);
//            }
//
//            String upcomingShows = formatShows(allShows);
//            System.out.println(upcomingShows);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events) {
//        LinkedHashMap<String, String> shows = new LinkedHashMap<String, String>();
//        for (JSONObject event : events) {
//            shows.put(getArtistName(event), getEventUrl(event));
//        }
//        return shows;
//    }
//    public static String getArtistName(JSONObject event) {
//        Artist artist = new ArtistModelFactory().create(event.getString("name"));
//        return artist.getName();
//    }
//
//    public static String getEventUrl(JSONObject event) {
//        String url = event.getString("url");
//        return url;
//    }
//
//    public static String formatShows(LinkedHashMap<String, String> shows) {
//
//        StringBuilder formattedConcerts = new StringBuilder();
//
//        ArrayList<String> concerts = new ArrayList<>();
//
//        for (Map.Entry<String, String> entry : shows.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            concerts.add(key + ": " + value);
//        }
//
//        for (int i = 0; i < Math.min(5, concerts.size()); i++){
//            formattedConcerts.append(concerts.get(i));
//            formattedConcerts.append("\n");
//        }
//
//        return formattedConcerts.toString();
//    }





