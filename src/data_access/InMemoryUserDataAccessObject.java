package data_access;

import entity.Artist;
import entity.ArtistFactory;
import entity.ArtistModelFactory;
import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.artist_venue.ArtistVenueDataAccess;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.show_artist_concerts.ShowArtistDataAcess;
import use_case.show_concerts.ShowConcertsDataAccess;
import use_case.upcoming_shows.UpcomingDataAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
//
public class InMemoryUserDataAccessObject implements UpcomingDataAccess, NotifyDataAccess, ShowConcertsDataAccess, ArtistVenueDataAccess, ShowArtistDataAcess {
    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();
    private String favouriteArtists = "";
    private static final String ticketmasterApiKey = "uxoAAPe38AqJZwxwxFNDw74mgWMdpJ3B";

    private final LinkedHashMap<String, List<String>> allShows = new LinkedHashMap<>();


    /**
     * @param event concert event from the ticket master api
     * @return a link to ticketmaster website with the closest concert where user can purchase tickets
     */
    @Override
    public String getEventUrl(JSONObject event) {
        String url = event.getString("url");
        return url;
    }

    /**
     * @param event concert event from the ticketmaster api
     * @return the name of the artist who is holding the given ticketmaster concert
     */
    @Override
    public String getArtistName(JSONObject event) {
        Artist artist = new ArtistModelFactory().create(event.getString("name"));
        return artist.getName();
    }

    /**
     * @param events concert events from ticketmaster api
     * @return a hashmap of the artist and a link for the user to buy tickets
     */
    @Override
    public LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events) {
        for (JSONObject event : events) {
            shows.put(getArtistName(event), getEventUrl(event));
        }
        return shows;
    }

    //////////////////////// FOR NOTIFY USER TOUR USE CASE /////////////////////////////
    /**
     * @param artistName the user's favourite artist
     * @param classification the type of event; (music event, concert)
     * @return whether the artist has upcoming concerts
     */
    @Override
    public String hasATour(String artistName, String classification) throws IOException, InterruptedException {

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?keyword=" + artistName;

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        urlString += "&apikey=" + ticketmasterApiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();

        JSONObject obj = new JSONObject(jsonContent.toString());

        String output = "";
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
            JSONObject embedded = obj.getJSONObject("_embedded");

            if (embedded.has("events")) {
                output = "has a tour";
            }

        } else {
            output = "doesn't have a tour";
        }

        // Introduce a 1 second delay to avoid hitting the rate limit
        Thread.sleep(500); // Adjust the sleep duration as needed

        return output;
    }

    public String getFavouriteArtists(){
        return favouriteArtists;
    }

    @Override
    public LinkedHashMap<String, List<String>> getUpcomingArtistShows(List<List<JSONObject>> artistsEvents) {
        for (List<JSONObject> events : artistsEvents) {
            if (!events.isEmpty()) {
                String artistName = getArtistName(events.get(0)); // Assuming all events in the list have the same artist
                List<String> eventUrls = new ArrayList<>();

                // Limit the number of events to the minimum of the size of the list or 3
                int limit = Math.min(events.size(), 3);
                for (int i = 0; i < limit; i++) {
                    eventUrls.add(getEventUrl(events.get(i)));
                }

                allShows.put(artistName, eventUrls);
            }
        }

        return allShows;

    }

}