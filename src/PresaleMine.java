import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PresaleMine extends PresaleBrittany {
    static PresaleBrittany pr = new PresaleBrittany("GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP");
    private List<String> presaleDates;
    private List<String> eventUrls;
    private final String apiKey;


    public PresaleMine(String apiKey) {
        super(apiKey);
        this.apiKey = apiKey;
        this.eventUrls = new ArrayList<>();
        this.presaleDates = new ArrayList<>();
    }


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
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        PresaleMine ticketmasterAPI = new PresaleMine(apiKey);

        // Example: Call getEventsFromLatLong from PresaleBrittany
        try {
            int radius = 100;
            String unit = "miles";
            String classification = "music";
            String artistName = "Taylor Swift";

            // Example: Get only music events based on geoPoint without specifying radius and unit
            List<JSONObject> events = pr.getEventsFromLatLong(latlong, radius, unit, classification, artistName);



            // Iterate throguh every event in events list
            for (JSONObject event : events) {
                // Add event information to the EventInfo object
                ticketmasterAPI.addEventInfo(event);
            }

            // Print event URLs and presale dates
            List<String> eventUrls = ticketmasterAPI.getEventUrls();
            List<String> presaleDates = ticketmasterAPI.getPresaleDates();


            // iterate through every url and place the preale date beside it.
            for (int i = 0; i < eventUrls.size(); i++) {
                System.out.println("Event URL: " + eventUrls.get(i));
                System.out.println("Presale: " + presaleDates.get(i));
                System.out.println(); // Add a newline for better readability
            }

            //pr.printEventUrls(events);

            // Process the events as needed
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException as needed
        }


    }

    public List<String> getEventUrls() {
        return eventUrls;
    }

    public List<String> getPresaleDates() {
        return presaleDates;
    }


}
