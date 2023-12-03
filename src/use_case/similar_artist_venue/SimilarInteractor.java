package use_case.similar_artist_venue;

import entity.*;
import interface_adapter.similar_artist.SimilarArtistPresenter;
import org.json.JSONArray;
import use_case.similar_artist_venue.SimilarInputData;
import org.json.JSONObject;
import data_access.InMemoryUserDataAccessObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class SimilarInteractor implements SimilarInputBoundary {
    private static HashMap<String, List<String>> similarArtistsMap = new HashMap<>();
    static  SimilarDataAccess similarDataAccessObject = new SimilarDataAccess() {
        @Override
        public HashMap<String, List<String>> getSimilarArtists(List<String> favouriteArtists) {
            return null;
        }

        @Override
        public List<JSONObject> getEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException, InterruptedException {
            return null;
        }
    };
    static  SimilarOutputBoundary similarPresenter = new SimilarOutputBoundary() {
        @Override
        public void prepareSuccessView(SimilarOutputData similarArtistsData) {

        }

        @Override
        public void prepareFailView(String errorMessage) {

        }
    };
    final UserFactory userFactory;
    static SimilarInputData similarInputData = new SimilarInputData();

    static  InMemoryUserDataAccessObject im = new InMemoryUserDataAccessObject();

    public SimilarInteractor(SimilarDataAccess similarDataAccess, SimilarOutputBoundary similarPresenter, UserFactory userFactory) {
        this.similarDataAccessObject = similarDataAccess;
        this.similarPresenter = similarPresenter;
        this.userFactory = userFactory;
    }

//    public void execute(SimilarInputData similarInputData) {
    public static void main(String [] args) {
        try {
            UserBuilder builder = new UserBuilder();
            User user = builder.addPostalCode(similarInputData.getPostalCode()).build();

            // Get the user's favorite artists and postal code from the input data
            List<String> favouriteArtists = similarInputData.getFavArtists();

            // Use the data access object to get the similar artists
            HashMap<String, List<String>> similarArtists = similarDataAccessObject.getSimilarArtists(favouriteArtists);

            if (similarArtists != null && !similarArtists.isEmpty()) {
                // If similar artists were found, prepare the success view
                SimilarOutputData outputData = new SimilarOutputData(similarArtists);
                similarPresenter.prepareSuccessView(outputData);
            } else {
                // If no similar artists were found, prepare the fail view
                similarPresenter.prepareFailView("No similar artists found.");
            }
            String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
            String token = InMemoryUserDataAccessObject.getToken();
            Set<String> addedArtists = new HashSet<>();
            for (String artist : favouriteArtists) {
                addedArtists.add(artist.toLowerCase());
            }
            try {
                for (int i = 0; i < similarInputData.getFavArtists().size(); i++) {
                    String list_artist = similarInputData.getFavArtists().get(i);
                    JSONObject artist = im.searchForArtist(token, list_artist);
                    if (artist != null) {
                        String artistId = artist.getString("id");
                        JSONObject simArtists = im.getSimilarArtists(token, artistId);
                        if (simArtists != null) {
                            ArrayList<String> similarArtistNames = new ArrayList<>();
                            JSONArray artists = simArtists.getJSONArray("artists");
                            for (int j = 0; j < artists.length(); j++) {
                                String similarArtistName = artists.getJSONObject(j).getString("name");
                                if (!addedArtists.contains(similarArtistName.toLowerCase())) {
                                    similarArtistNames.add(similarArtistName);
                                    addedArtists.add(similarArtistName.toLowerCase());
                                    System.out.println(similarArtistName);
                                }
                            }
                            similarArtistsMap.put(similarInputData.getFavArtists().get(i), similarArtistNames);
                            System.out.println(similarArtistsMap);
                        } else {
                            System.out.println("No similar artists found for " + list_artist);
                        }
                    } else {
                        System.out.println("No artist found with the name " + list_artist);
                    }
                }
                for (Map.Entry<String, List<String>> entry : similarArtistsMap.entrySet()) {
                    int count = 0;
                    for (String artist : entry.getValue()) {
                        boolean done = false;
                        while (!done) {
                            try {
                                String encodedArtistName = URLEncoder.encode(artist, StandardCharsets.UTF_8.toString());
                                List<JSONObject> events = similarDataAccessObject.getEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
                                if (!events.isEmpty() && count < 3) {
                                    System.out.println("Events for " + artist + ":");
                                    im.printEventUrls(events);
                                    count++;
                                }
                                done = true;
                            } catch (IOException e) {
                                if (e.getMessage().contains("HTTP response code: 429")) {
                                    System.out.println("Rate limit exceeded, waiting for 1 minute before retrying...");
                                    Thread.sleep(60000); // Wait for 60 seconds
                                } else {
                                    throw e; // If it's not a rate limit issue, rethrow the exception
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(SimilarInputData similarInputData) {

    }


//    public static void main(String[] args) {
//        try {
//            // You can create an instance of your dependencies here if needed
//            String strFavArtists = "Taylor Swift, Olivia Rodrigo";
//            String[] artistsArray = strFavArtists.split(", ");
//            List<String> artistList = Arrays.asList(artistsArray);
//            ArtistFactory artistFactory = new ArtistModelFactory();
//            List<String> favArtists = new ArrayList<>();
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
//            HashMap<String, List<String>> similarArtists = similarDataAccessObject.getSimilarArtists(favArtists);
//            for (Map.Entry<String, List<String>> entry : similarArtists.entrySet()) {
//                String artist = entry.getKey();
//                List<String> artistEvents = entry.getValue();
//                for (String event : artistEvents) {
//                    allShows.put(artist, event);
//                }
//            }
//            String upcomingShows = formatShows(allShows);
//            System.out.println(upcomingShows);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
