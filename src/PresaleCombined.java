import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;


import java.net.URL;
import java.util.Comparator;

//----helper imports

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PresaleCombined extends LocationFinder {
    private static final double r2d = 180.0D / Math.PI;
    private static final double d2r = Math.PI / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    static LocationFinder lf = new LocationFinder();
    public static List<Double> latlong = lf.latlongy();


    private final String apiKey;

    //helper private
    private List<String> eventUrls;
    private List<String> presaleDates;

    public PresaleCombined(String apiKey) {
        this.apiKey = apiKey;
        //helper

        this.eventUrls = new ArrayList<>();
        this.presaleDates = new ArrayList<>();
    }

    public List<JSONObject> getEventsFromLatLong(List<Double> latlong) throws IOException {
        return getEventsFromLatLong(latlong, 0, null, "music", null);
    }


    public List<JSONObject> getEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException {
        double lat1 = latlong.get(0);
        double lat2 = latlong.get(1);
        String strLatlong = Double.toString(lat1) + "," + Double.toString(lat2);


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
// Parse the JSON response and return a list of events
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



    // Finds length between users location and the venue to sort it so closest venue is shown
    public double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }


    public void printEventUrls(List<JSONObject> events) {
        for (JSONObject event : events) {
            String url = event.getString("url");
            //System.out.println("Event URL: " + url);
        }
    }

    //---------------------------------------helper method----------------------

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
            presaleDates.add("No presale date available, click to see if theres tix available");
        }
    }

    private boolean isPastPresale(String presaleEndDate){
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


    public static void main(String[] args) {
        // Inside the main method of user_enter_artists class
        //PresaleRoughHelper eventInfo = new PresaleRoughHelper();

        // Replace 'YOUR_API_KEY' with your actual Ticketmaster API key.
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";


        PresaleCombined ticketmasterAPI = new PresaleCombined(apiKey);


        try {
            System.out.println("\n");
            int radius = 100;
            String unit = "miles";
            String artistName = "Taylor Swift"; // Replace with the artist you want to filter by


            // Example: Get only music events based on geoPoint without specifying radius and unit
            List<JSONObject> eventL = ticketmasterAPI.getEventsFromLatLong(latlong, radius, unit, "music", artistName);
            ticketmasterAPI.printEventUrls(eventL);


            // Example: Get only music events based on geoPoint without specifying radius and unit
            List<JSONObject> eventList = ticketmasterAPI.getEventsFromLatLong(latlong, radius, unit, "music", artistName);

            for (JSONObject event : eventList) {
                // Add event information to the EventInfo object
                ticketmasterAPI.addEventInfo(event);
            }

// Print event URLs and presale dates
            List<String> eventUrls = ticketmasterAPI.getEventUrls();
            List<String> presaleDates = ticketmasterAPI.getPresaleDates();

            for (int i = 0; i < eventUrls.size(); i++) {
                System.out.println("Event URL: " + eventUrls.get(i));
                System.out.println("Presale: " + presaleDates.get(i));
                System.out.println(); // Add a newline for better readability
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<String> getEventUrls() {
        return eventUrls;
    }

    public List<String> getPresaleDates() {
        return presaleDates;
    }
}



