package use_case.similar_artist_venue;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import data_access.InMemoryUserDataAccessObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class SimilarInteractor implements SimilarInputBoundary {
    private HashMap<String, List<String>> similarArtistsMap = new HashMap<>();

    final SimilarDataAccess similarDataAccessObject;
    final SimilarOutputBoundary similarPresenter;
    final UserFactory userFactory;

    final InMemoryUserDataAccessObject im = new InMemoryUserDataAccessObject();
    private List<Artist> favouriteArtists;


    public SimilarInteractor(SimilarDataAccess similarDataAccess, SimilarOutputBoundary similarPresenter, UserFactory userFactory) {
        this.similarDataAccessObject = similarDataAccess;
        this.similarPresenter = similarPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(SimilarInputData similarInputData) {

        try {
            // converting user input, so it will fit what the api expects
            String artistNameInput = similarInputData.getFavArtists().toString();
            String lowerArtistNames = artistNameInput.toLowerCase();

            String[] artistNameList = lowerArtistNames.split(",");
            ArrayList<String> hasFavouriteArtistConcert = new ArrayList<>();

            for(String str : artistNameList){

                String favouriteArtists;
                if (str.contains(" ")){
                    favouriteArtists = str.replace(' ', '-');
                } else {
                    favouriteArtists = str;
                }

            }

            UserBuilder builder = new UserBuilder();
            User user = builder.addPostalCode(similarInputData.getPostalCode()).build();

            // Get the user's favorite artists and postal code from the input data
//            String favouriteArtists = similarInputData.getFavArtists();
            String postalCode = similarInputData.getPostalCode();

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
            String token = im.getToken();
            Set<String> addedArtists = new HashSet<>();
            for (favouriteArtists) {
                addedArtists.add(artist.toLowerCase());
            }
            try {
                for (int i = 0; i < similarInputData.getFavArtists().size(); i++) {
                    String list_artist = similarInputData.getFavArtists().get(i);
                    JSONObject artist = im.searchForArtist(token, list_artist);
                    if (artist != null) {
                        String artistId = artist.getString("id");
                        JSONObject simArtists = im.getSimilarArtists(token, artistId);
                        if (similarArtists != null) {
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
                                List<JSONObject> events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
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

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
