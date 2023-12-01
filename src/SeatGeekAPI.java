
// example search in browser: https://api.seatgeek.com/2/events?client_id=Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// returning taylor swift info https://api.seatgeek.com/2/performers?slug=taylor-swift&Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3
public class SeatGeekAPI {
    String apiKey = "Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3";

    public void notifyUser(String artistName){
        try {

            // Replace with your specific API endpoint and parameters
            String baseUrl = "https://api.seatgeek.com/2/performers?";
            String apiUrl = baseUrl + "slug=" + artistName + "&client_id=" + apiKey;

            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

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
            JSONObject artistInfo = artistArray.getJSONObject(0);

            System.out.println(artistInfo.get("has_upcoming_events"));


            System.out.println(jsonResponse.get("performers"));

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SeatGeekAPI seatGeekAPI = new SeatGeekAPI();
        seatGeekAPI.notifyUser("seventeen");
    }

}



