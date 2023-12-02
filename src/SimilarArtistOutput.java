import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.URL;

import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

public class SimilarArtistOutput extends LocationFinder{
    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";
    static ArrayList<String> userInput = SimilarArtistInput.similar_Artists();
    static String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

    public SimilarArtistOutput(String apiKey) {
        this.apiKey = apiKey;
    }
    final static int radius = 100;
    final static String unit = "miles";
    final static String classification = "music";
    static HashMap<String, ArrayList<String>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        SimilarArtistOutput api = new SimilarArtistOutput(apiKey);
        String token = getToken();
        Set<String> addedArtists = new HashSet<>();
        for (String artist : userInput) {
            addedArtists.add(artist.toLowerCase());
        }

        try {
            for (int i = 0; i < userInput.size(); i++) {
                String list_artist = userInput.get(i);
                JSONObject artist = searchForArtist(token, list_artist);
                if (artist != null) {
                    String artistId = artist.getString("id");
                    JSONObject similarArtists = getSimilarArtists(token, artistId);
                    if (similarArtists != null) {
                        ArrayList<String> similarArtistNames = new ArrayList<>();
                        JSONArray artists = similarArtists.getJSONArray("artists");
                        for (int j = 0; j < artists.length(); j++) {
                            String similarArtistName = artists.getJSONObject(j).getString("name");
                            if (!addedArtists.contains(similarArtistName.toLowerCase())) {
                                similarArtistNames.add(similarArtistName);
                                addedArtists.add(similarArtistName.toLowerCase());
                                System.out.println(similarArtistName);
                            }
                        }
                        map.put(userInput.get(i), similarArtistNames);
                        System.out.println(map);
                    } else {
                        System.out.println("No similar artists found for " + list_artist);
                    }
                } else {
                    System.out.println("No artist found with the name " + list_artist);
                }
            }
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                int count = 0;
                for (String artist : entry.getValue()) {
                    boolean done = false;
                    while (!done) {
                        try {
                            String encodedArtistName = URLEncoder.encode(artist, StandardCharsets.UTF_8.toString());
                            List<JSONObject> events = api.findEventsFromLatLong(latlong, 10, "miles", "music", encodedArtistName);
                            if (!events.isEmpty() && count < 3) {
                                System.out.println("Events for " + artist + ":");
                                api.printEventUrls(events);
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

    }

    public List<JSONObject> findEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException, InterruptedException {
        double lat1 = latlong.get(0);
        double long2 = latlong.get(1);
        String strLatlong = Double.toString(lat1) + "," + Double.toString(long2);

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + strLatlong;

        // Append radius, unit, classification, and artist name if provided
        if (radius > 0 && unit != null) {
            urlString += "&radius=" + radius + "&unit=" + unit;
        }

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        if (artistName != null) {
            urlString += "&keyword=" + artistName;
        }
        urlString += "&apikey=" + apiKey;
        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();
        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        List<JSONObject> events = new ArrayList<>();
        JSONObject obj = new JSONObject(jsonContent.toString());

        // Check if _embedded is a JSONArray
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
            JSONObject embedded = obj.getJSONObject("_embedded");

            if (embedded.has("events")) {
                JSONArray eventsArray = embedded.getJSONArray("events");


                for (int i = 0; i < eventsArray.length(); i++) {
                    events.add(eventsArray.getJSONObject(i));
                }
                // Sort events based on distance
                events.sort(Comparator.comparingDouble(event ->
                        calculateDistance(latlong,
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"))));
            }
        }
        return events;
    }
    public static double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    public void printEventUrls(List<JSONObject> eventList) {
        for (JSONObject event : eventList) {
            if (event.has("url")) {
                System.out.println(event.getString("url"));
            } else {
                System.out.println("No URL found for this event.");
            }
        }
    }
    private static String getToken() throws Exception {
        String authString = CLIENT_ID + ":" + CLIENT_SECRET;
        String authBase64 = Base64.getEncoder().encodeToString(authString.getBytes());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + authBase64)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        return jsonResult.getString("access_token");
    }

    private static JSONObject searchForArtist(String token, String artistName) throws Exception {
        artistName = artistName.replace(" ", "%20");  // replace spaces with %20
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/search?q=" + artistName + "&type=artist&limit=1"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        JSONArray items = jsonResult.getJSONObject("artists").getJSONArray("items");
        if (items.length() == 0) {
            System.out.println("No artist with this name exists");
            return null;
        }
        return items.getJSONObject(0);
    }


    private static JSONObject getSimilarArtists(String token, String artistId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/artists/" + artistId + "/related-artists"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        if (jsonResult.getJSONArray("artists").length() == 0) {
            System.out.println("No similar artists");
            return null;
        }
        return jsonResult;
    }
}



