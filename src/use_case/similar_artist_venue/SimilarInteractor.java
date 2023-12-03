package use_case.similar_artist_venue;

import data_access.FileUserDataAccessObject;
import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class SimilarInteractor implements SimilarInputBoundary {
    private static final HashMap<String, List<String>> similarArtistsMap = new HashMap<>();
    static SimilarDataAccess similarDataAccessObject = new SimilarDataAccess() {

        @Override
        public HashMap<String, List<String>> getSimilarArtists(List<String> similarArtists) {
            return null;
        }

        @Override
        public List<JSONObject> findEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException, InterruptedException {
            return null;
        }

    };
    static SimilarOutputBoundary similarPresenter = new SimilarOutputBoundary() {
        @Override
        public void prepareSuccessView(SimilarOutputData similarArtistsData) {

        }

        @Override
        public void prepareFailView(String errorMessage) {

        }
    };
    final UserFactory userFactory;
    public List<String> eventURL;
    static SimilarInputData similarInputData = new SimilarInputData();

    static FileUserDataAccessObject im = new FileUserDataAccessObject();

    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";

    public SimilarInteractor(SimilarDataAccess similarDataAccess, SimilarOutputBoundary similarPresenter, UserFactory userFactory) {
        similarDataAccessObject = similarDataAccess;
        SimilarInteractor.similarPresenter = similarPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(SimilarInputData similarInputData) throws Exception {

        String strFavArtists = similarInputData.getFavArtists();
        String[] artistsArray = strFavArtists.split(", ");
        List<String> artistList = Arrays.asList(artistsArray);
        ArtistFactory artistFactory = new ArtistModelFactory();
        List<Artist> favArtists = new ArrayList<>();

        for (String artistString : artistList) {
            Artist artist = artistFactory.create(artistString);
            favArtists.add(artist);
        }

        // converting user input, so it will fit what the api expects
        List<String> favouriteArtists = new ArrayList<>();
        for (String str : artistList) {
            if (str.contains(" ")) {
                favouriteArtists.add(str.replace(' ', '-'));
            } else {
                favouriteArtists.add(str);
            }
        }
        UserBuilder builder = new UserBuilder();
        User user = builder.addPostalCode(similarInputData.getPostalCode()).build();
        HashMap<String, List<String>> similarArtistsMap = new HashMap<>();

        List<JSONObject> events = new ArrayList<>();
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        String token = FileUserDataAccessObject.getToken();
        Set<String> addedArtists = new HashSet<>();
        for (Artist artist : favArtists) {
            addedArtists.add(artist.getName().toLowerCase());
        }
        Iterator<String> iterator = favouriteArtists.iterator();
        while (iterator.hasNext()) {
            String artistName = iterator.next();
            JSONObject artist = FileUserDataAccessObject.searchForArtist(token, artistName);
            if (artist != null) {
                String artistId = artist.getString("id");
                JSONObject simArtists = FileUserDataAccessObject.getSimilarArtists(token, artistId);
                if (simArtists != null) {
                    ArrayList<String> similarArtistNames = new ArrayList<>();
                    JSONArray artists = simArtists.getJSONArray("artists");
                    for (int j = 0; j < artists.length(); j++) {
                        String similarArtistName = artists.getJSONObject(j).getString("name");
                        if (!addedArtists.contains(similarArtistName.toLowerCase())) {
                            // Check if the similar artist has any events
                            String encodedArtistName = URLEncoder.encode(similarArtistName, StandardCharsets.UTF_8);
                            events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
                            if (!events.isEmpty()) {
                                similarArtistNames.add(similarArtistName);
                                addedArtists.add(similarArtistName.toLowerCase());
                            }
                        }
                    }
                    if (!similarArtistNames.isEmpty()) {
                        similarArtistsMap.put(artistName, similarArtistNames);
                        System.out.println(similarArtistsMap);
                    } else {
                        // Remove the artist from favouriteArtists if they don't have any events
                        iterator.remove();
                        System.out.println("No events found for " + artistName);
                    }
                } else {
                    System.out.println("No similar artists found for " + artistName);
                }
            } else {
                System.out.println("No artist found with the name " + artistName);
            }
        }

//                for (String artistName : favouriteArtists) {
//                    JSONObject artist = FileUserDataAccessObject.searchForArtist(token, artistName);
//                    if (artist != null) {
//                        String artistId = artist.getString("id");
//                        JSONObject simArtists = FileUserDataAccessObject.getSimilarArtists(token, artistId);
//                        if (simArtists != null) {
//                            ArrayList<String> similarArtistNames = new ArrayList<>();
//                            JSONArray artists = simArtists.getJSONArray("artists");
//                            for (int j = 0; j < artists.length(); j++) {
//                                String similarArtistName = artists.getJSONObject(j).getString("name");
//                                if (!addedArtists.contains(similarArtistName.toLowerCase())) {
//                                    // Check if the similar artist has any events
//                                    String encodedArtistName = URLEncoder.encode(similarArtistName, StandardCharsets.UTF_8);
//                                    events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
//                                    if (!events.isEmpty()) {
//                                        similarArtistNames.add(similarArtistName);
//                                        addedArtists.add(similarArtistName.toLowerCase());
//                                    }
//                                }
//                            }
//                            similarArtistsMap.put(artistName, similarArtistNames);
//                            System.out.println(similarArtistsMap);
//                        } else {
//                            System.out.println("No similar artists found for " + artistName);
//                        }
//                    } else {
//                        System.out.println("No artist found with the name " + artistName);
//                    }

                for (Map.Entry<String, List<String>> entry : similarArtistsMap.entrySet()) {
                    int count = 0;
                    for (String artistName : entry.getValue()) {
                        boolean done = false;
                        while (!done) {
                            try {
                                String encodedArtistName = URLEncoder.encode(artistName, StandardCharsets.UTF_8.toString());
                                events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
                                if (count < 3) {
                                    eventURL.add("Similar artist for " + entry.getKey() + ": " + artistName + " " + im.getEventUrls(events);
                                    count++;
                                }
                                System.out.println(eventURL);
                                done = true;
                            } catch (IOException e) {
                                if (e.getMessage().contains("HTTP response code: 429")) {
                                    System.out.println("Rate limit exceeded, waiting for 1 minute before retrying...");
                                    Thread.sleep(60000); // Wait for 60 seconds
                                } else {
                                    throw e; // If it's not a rate limit issue, rethrow the exception
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
                if (similarArtistsMap != null && !similarArtistsMap.isEmpty()) {
                    // If similar artists were found, prepare the success view
                    System.out.println(eventURL);
                    SimilarOutputData outputData = new SimilarOutputData(eventURL);
                    similarPresenter.prepareSuccessView(outputData);
                } else {
                    // If no similar artists were found, prepare the fail view
                    similarPresenter.prepareFailView("No similar artists found.");



    }
}}


//    public static void main(String[] args) throws Exception {
//
//        try {
//
//            try {
//                String strFavArtists = "Taylor Swift, Laufey, Harry Styles";
//
//                String[] artistsArray = strFavArtists.split(", ");
//                String[] artistList = artistsArray;
//                ArtistFactory artistFactory = new ArtistModelFactory();
//                List<Artist> favArtists = new ArrayList<>();
////
////                String strFavArtists = similarInputData.getFavArtists();
////                String[] artistsArray = strFavArtists.split(", ");
////                List<String> artistList = Arrays.asList(artistsArray);
////                ArtistFactory artistFactory = new ArtistModelFactory();
////                List<Artist> favArtists = new ArrayList<>();
//
//                for (String artistString : artistList) {
//                    Artist artist = artistFactory.create(artistString);
//                    favArtists.add(artist);
//                }
//
//                // converting user input, so it will fit what the api expects
//                List<String> favouriteArtists = new ArrayList<>();
//                for (String str : artistList) {
//                    if (str.contains(" ")) {
//                        favouriteArtists.add(str.replace(' ', '-'));
//                    } else {
//                        favouriteArtists.add(str);
//                    }
//                }
//                UserBuilder builder = new UserBuilder();
//                User user = builder.addPostalCode("M8V1B7").build();
//                // Use the data access object to get the similar artists
//                HashMap<String, List<String>> similarArtistsMap = new HashMap<>();
//
//                if (similarArtistsMap != null && !similarArtistsMap.isEmpty()) {
//                    // If similar artists were found, prepare the success view
//                    SimilarOutputData outputData = new SimilarOutputData(similarArtistsMap);
//                    similarPresenter.prepareSuccessView(outputData);
//                } else {
//                    // If no similar artists were found, prepare the fail view
//                    similarPresenter.prepareFailView("No similar artists found.");
//                }
//
//                String token = im.getToken();
//                Set<String> addedArtists = new HashSet<>();
//                for (Artist artist : favArtists) {
//                    addedArtists.add(artist.getName().toLowerCase());
//                }
//
//                for (String artistName : favouriteArtists) {
//                    JSONObject artist = im.searchForArtist(token, artistName);
//                    if (artist != null) {
//                        String artistId = artist.getString("id");
//                        JSONObject simArtists = im.getSimilarArtists(token, artistId);
//                        if (simArtists != null) {
//                            ArrayList<String> similarArtistNames = new ArrayList<>();
//                            JSONArray artists = simArtists.getJSONArray("artists");
//                            for (int j = 0; j < artists.length(); j++) {
//                                String similarArtistName = artists.getJSONObject(j).getString("name");
//                                if (!addedArtists.contains(similarArtistName.toLowerCase())) {
//                                    // Check if the similar artist has any events
//                                    String encodedArtistName = URLEncoder.encode(similarArtistName, StandardCharsets.UTF_8);
//                                    List<JSONObject> events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
//                                    if (!events.isEmpty()) {
//                                        similarArtistNames.add(similarArtistName);
//                                        addedArtists.add(similarArtistName.toLowerCase());
////                                        System.out.println(similarArtistName);
//                                    }
//                                }
//                            }
//                            similarArtistsMap.put(artistName, similarArtistNames);
//                            System.out.println(similarArtistsMap);
//                        } else {
//                            System.out.println("No similar artists found for " + artistName);
//                        }
//                    } else {
//                        System.out.println("No artist found with the name " + artistName);
//                    }
//                }
//                for (Map.Entry<String, List<String>> entry : similarArtistsMap.entrySet()) {
//                    int count = 0;
//                    for (String artistName : entry.getValue()) {
//                        boolean done = false;
//                        while (!done) {
//                            try {
//                                String encodedArtistName = URLEncoder.encode(artistName, StandardCharsets.UTF_8.toString());
//                                List<JSONObject> events = im.findEventsFromLatLong(im.locationFinder(user), 10, "miles", "music", encodedArtistName);
//                                if (count < 3) {
////                                    System.out.println("Events for " + artistName + ":");  // Changed 'artist' to 'artistName'
////                                    im.printEventUrls(events);
////                                    count++;
//                                    System.out.println("Similar artist for " + entry.getKey() + ": " + artistName);
//                                    System.out.println(im.getEventUrls(events));
//                                    count++;
//                                }
//                                done = true;
//                            } catch (IOException e) {
//                                if (e.getMessage().contains("HTTP response code: 429")) {
//                                    System.out.println("Rate limit exceeded, waiting for 1 minute before retrying...");
//                                    Thread.sleep(60000); // Wait for 60 seconds
//                                } else {
//                                    throw e; // If it's not a rate limit issue, rethrow the exception
//                                }
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    }
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }



//    @Override
//    public void execute(SimilarInputData similarInputData) throws Exception {
//
//    }
//






