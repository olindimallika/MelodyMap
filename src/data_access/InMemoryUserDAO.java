//package data_access;
//
////import entity.TestArtist;
//import entity.Artist;
//import entity.User;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.json.JSONObject;
//import use_case.artist_venue.ArtistVenueDataAccess;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//
//
//import java.net.URL;
//import java.util.Comparator;
//import java.util.Scanner;
//
//
//public class InMemoryUserDAO implements ArtistVenueDataAccess {
//
//    private final Map<String, User> users = new HashMap<>();
//
//    @Override
//    public List<Double> getLatLong(String postalCode) { //correct and works!
//        // return the latitude and longitude of the user from their postal code
//        String apiKey1 = "0e7e66d3a7b44c6a8e5d7b6c7d61f4f7";
////        String postalCode = user.getPostalCode();
//        List<Double> coordinates = new ArrayList<>();
//
//        try {
//            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + apiKey1 + "&q=" + postalCode + "&countrycode=CA";
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    JSONObject json = new JSONObject(responseBody);
//                    JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");
//
//                    double latitude = location.getDouble("lat");
//                    double longitude = location.getDouble("lng");
//
//                    coordinates.add(latitude);
//                    coordinates.add(longitude);
//                } else {
////                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
//                    coordinates.add(0.0);
//                    // if the postal code is not a valid postal code coordinates = 0.0
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return coordinates;
//    }
//
//    // but you guys went to show
//
//    @Override
//    public List<List<JSONObject>> artistEvents(String postalCode, ArrayList<Artist> favArtists) throws IOException {
//        String apiKey2 = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
//
//        List<Double> latlong = getLatLong(postalCode);
//        double lat1 = latlong.get(0);
//        double lat2 = latlong.get(1);
//        String coordinates = lat1 + "," + lat2;
//
//        int radius = 0;
//        String unit = null;
//        String classification = "music";
//
////        ArrayList<TestArtist> favArtists = user.getFavArtist();
//
//        ArrayList<List<JSONObject>> favArtistEvents = new ArrayList<>();
//
//        for (Artist artist : favArtists) {
//            String artistName = artist.getName();
//            String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
//            String urlString = baseUrl + "?geoPoint=" + coordinates;
//
//            if (radius > 0 && unit != null) {
//                urlString += "&radius=" + radius + "&unit=" + unit;
//            }
//
//            if (classification != null) {
//                urlString += "&classificationName=" + classification;
//            }
//
//            if (artistName != null) {
//                urlString += "&keyword=" + artistName;
//            }
//
//            urlString += "&apikey=" + apiKey2;
//
//            List<JSONObject> events = fetchEvents(urlString, latlong);
//            favArtistEvents.add(events);
//        }
//
//        return favArtistEvents;
//    }
//
//    private List<JSONObject> fetchEvents(String urlString, List<Double> latlong) throws IOException {
//        URL url = new URL(urlString);
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//
//        scanner.close();
//
//        List<JSONObject> events = new ArrayList<>();
//        JSONObject obj = new JSONObject(jsonContent.toString());
//
//        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
//            JSONObject embedded = obj.getJSONObject("_embedded");
//
//            if (embedded.has("events")) {
//                JSONArray eventsArray = embedded.getJSONArray("events");
//
//                for (int j = 0; j < eventsArray.length(); j++) {
//                    events.add(eventsArray.getJSONObject(j));
//                }
//
//                // Sort events based on distance
//                events.sort(Comparator.comparingDouble(event ->
//                        calculateDistance(latlong,
//                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
//                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"))));
//            }
//        }
//
//        return events;
//    }
//
//    public double calculateDistance(List<Double> latlong, double lat2, double lon2) {
//        double lat1 = latlong.get(0);
//        double lon1 = latlong.get(1);
//        double x = lat1 * (Math.PI / 180);
//        double y = lat2 * (Math.PI / 180);
//        // Equation - need to fix
//        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
//    }
//}
//
//
//
