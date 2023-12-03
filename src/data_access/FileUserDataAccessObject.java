package data_access;

import entity.*;
import org.json.JSONArray;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.presale_date.PresaleDataAccess;
import use_case.upcoming_shows.UpcomingDataAccess;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUserDataAccessObject implements UpcomingDataAccess, NotifyDataAccess, PresaleDataAccess {
    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();

    private static final String locationFinderApiKey = "6d23eef602cc4b218db79d85609ddbfe";

    private static final String seatGeekApiKey = "Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3";

    private static final List<Double> geoPoint = new ArrayList<>();

    private JSONObject artistInfo;

    private List<String> presaleDates = new ArrayList<>();
    private List<String> eventUrls = new ArrayList<>();
    private List<String> listFormatOutputPresale = new ArrayList<>();
    private String finalFormatOutputPresale = "";


    //private String idkFormatOutputPresale = "";



    //private List<String> artName = new ArrayList<>();

    public FileUserDataAccessObject() {
    }

    public List<Double> locationFinder(User user){
        String postalCode = user.getPostalCode();

        try {
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");

                double latitude = location.getDouble("lat");
                double longitude = location.getDouble("lng");

                geoPoint.add(latitude);
                geoPoint.add(longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoPoint;
    }

    public List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws IOException {
        List<Double> latlong = locationFinder(user);
        double lat1 = latlong.get(0);
        double lat2 = latlong.get(1);
        String strLatlong = Double.toString(lat1) + "," + Double.toString(lat2);

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + strLatlong;

        // Append radius, unit, and classification if provided
        if (radius > 0 && unit != null) {
            urlString += "&radius=" + radius + "&unit=" + unit;
        }

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        urlString += "&apikey=" + "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());

        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        // Parse the JSON response and return a list of events
        List<JSONObject> events = new ArrayList<>();
        JSONObject obj = new JSONObject(jsonContent.toString());

        // Check if _embedded is a JSONArray
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject && obj.getJSONObject("_embedded").has("events")) {
            JSONObject embedded = obj.getJSONObject("_embedded");
            JSONArray eventsArray = embedded.getJSONArray("events");

            for (int i = 0; i < eventsArray.length(); i++) {
                events.add(eventsArray.getJSONObject(i));
            }

            // Sort events based on distance
            events.sort(Comparator.comparingDouble(event ->
                    calculateDistance(
                            event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                            event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"),
                            user)));
        }

        return events;
    }


    // Finds length between users location and the venue to sort it so closest venue is shown
    public double calculateDistance(double lat2, double lon2, User user) {
        List<Double> latlong = locationFinder(user);
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    public String getEventUrl(JSONObject event) {
        String url = event.getString("url");
        return url;
    }

    public String getArtistName(JSONObject event) {
        Artist artist = new ArtistModelFactory().create(event.getString("name"));
        return artist.getName();
    }

    public LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events) {
        for (JSONObject event : events) {
            shows.put(getArtistName(event), getEventUrl(event));
        }
        return shows;
    }

    public String formatShows(LinkedHashMap<String, String> shows) {

        StringBuilder formattedConcerts = new StringBuilder();

        ArrayList<String> concerts = new ArrayList<>();

        for (Map.Entry<String, String> entry : shows.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            concerts.add(key + ": " + value);
        }

        for (int i = 0; i < 5; i++){
            formattedConcerts.append(concerts.get(i));
            formattedConcerts.append("\n");
        }
        return formattedConcerts.toString();
    }

    /**
     * @param postalCode the user's postal code
     * @return whether the coordinates of the user's postal code exists
     */
    @Override
    public boolean existsInCoords(String postalCode) {
        return !geoPoint.isEmpty();
    }

    //--------------- FOR PRESALE USE CASE ----------
    public void addEventInfo(JSONObject event) {
        // Extract and store the URL
        String url = event.getString("url");
        eventUrls.add(url);

        // Extract and store presale date if available
        if (event.has("sales") && event.getJSONObject("sales").has("presales")) {
            JSONArray presalesArray = event.getJSONObject("sales").getJSONArray("presales");
            for (int j = 0; j < presalesArray.length(); j++) {
                JSONObject presale = presalesArray.getJSONObject(j);
                String startPresaleDate = presale.getString("startDateTime");
                String endPresaleDate = presale.getString("endDateTime");

                if (isPastPresale(endPresaleDate)) {
                    presaleDates.add("You missed the presale. Go to general sale by clicking link");
                } else {
                    String intervalPresale = "Presale is happening now until "+ endPresaleDate + " click the link to go to presale.";
                    presaleDates.add(intervalPresale);
                }
            }
        } else {
            // If no presale date is available, add a placeholder or handle it as needed
            presaleDates.add("No presale date available. Click to see if theres tix available");
        }
    }

    public boolean isPastPresale(String presaleEndDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date endDate = sdf.parse(presaleEndDate);
            Date currentDate = new Date();

            return currentDate.after(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parse exception as needed
            return false;
        }

    }

    public String formatOutputPresale(String artName, String artUrl, String artPresale){
        String result = (artName + "\n" + "Event URL: "+artUrl +"\n"+ "Presale Status: "+ artPresale + "\n"+ "\n");
        listFormatOutputPresale.add(result);
        return result;
    }


    public List<String> getEventUrls() {
        return eventUrls;
    }

    public List<String> getPresaleDates() {
        return presaleDates;
    }

    public String getFormatOutputPresale() {
        finalFormatOutputPresale = String.join("", listFormatOutputPresale);
        return finalFormatOutputPresale;
        //return listFormatOutputPresale;
    }





    //////////////////////// FOR NOTIFY USER TOUR USE CASE /////////////////////////////
    public JSONObject getPerformerInfo(String artistName){

        try {
            // Replace with your specific API endpoint and parameters
            String baseUrl = "https://api.seatgeek.com/2/performers?";
            String apiUrl = baseUrl + "slug=" + artistName + "&client_id=" + seatGeekApiKey;

            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Pragma", "no-cache");
            connection.setRequestProperty("Expires", "no-cache");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            JSONArray artistArray = (JSONArray) jsonResponse.get("performers");
            artistInfo = artistArray.getJSONObject(0);

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistInfo;
    }

    public Integer getNumUpcomingConcerts(){
        Integer numUpcomingEvents = (Integer) artistInfo.get("num_upcoming_events");
        return numUpcomingEvents;
    }

    public String getTicketLink(){
        return String.valueOf(artistInfo.get("url"));
    }

    /**
     * @return whether the api call can be made to find the artist's information
     */
    @Override
    public boolean existsInApi() {
        // if artistInfo is null, that means the artistName could not be assigned through the getPerformerInfo method
        return artistInfo != null;
    }

}
