
// example search in browser: https://api.seatgeek.com/2/events?client_id=Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// returning taylor swift info https://api.seatgeek.com/2/performers?slug=taylor-swift&Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3
public class SeatGeekAPI {
    String apiKey = "Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3";

    public void notifyUser(String artistName){
        try {

            // Replace with your specific API endpoint and parameters
            String baseUrl = "https://api.seatgeek.com/2/performers?";
            String apiUrl = baseUrl + "slug=" + artistName + "&client_id=" + apiKey;

            // Create URL object
            URL url1 = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();

            // Set request method
            connection1.setRequestMethod("GET");

            // disabling caching on client side (didn't work)
            connection1.setRequestProperty("Cache-Control", "no-cache");
            connection1.setRequestProperty("Pragma", "no-cache");

            //logging URL
            System.out.println("Request URL: " + connection1.getURL());
            Map<String, List<String>> headers = connection1.getHeaderFields();
            System.out.println("Response Headers: " + headers);

            // Connect to the server
            connection1.connect();

            // Read the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection1.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                connection1.getInputStream().close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                JSONArray artistArray = (JSONArray) jsonResponse.get("performers");
                JSONObject artistInfo = artistArray.getJSONObject(0);

                Integer numUpcomingEvents = (Integer) artistInfo.get("num_upcoming_events");

                if (artistInfo.get("has_upcoming_events").equals(true)) {
                    System.out.println("Your favourite artist has " + numUpcomingEvents + " upcoming concerts!\nPurchase tickets here: " + artistInfo.get("url"));
                } else {
                    System.out.println("Sorry! Your favourite artist doesn't have any upcoming concerts :(");
                }
            }


            // Close the connection
            connection1.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void getSomething(String city){
//        try {
//
//            // Replace with your specific API endpoint and parameters
//            String baseUrl = "https://api.seatgeek.com/2/venues?city=" + city + "&client_id=" + apiKey;
//
//            // for debugging purposes
//            System.out.println(baseUrl);
//
//
//            // Create URL object
//            URL url = new URL(baseUrl);
//
//            // Open connection
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            //logging URL
//            System.out.println("Request URL: " + connection.getURL());
//
//            // Set request method
//            connection.setRequestMethod("GET");
//
//            // Read the response
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                String line;
//                StringBuilder response = new StringBuilder();
//
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
//                // Parse the JSON response
//                JSONObject jsonResponse = new JSONObject(response.toString());
//
//                System.out.println(jsonResponse);
//
//            }
//
//
//            // Close the connection
//            connection.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }




    public static void main(String[] args) {
        SeatGeekAPI seatGeekAPI = new SeatGeekAPI();
        ArrayList<String> hi = new ArrayList<>();
        hi.add("taylor-swift");
        hi.add("laufey");

        seatGeekAPI.notifyUser("taylor-swift");
        seatGeekAPI.notifyUser("niki");
//        seatGeekAPI.notifyUser("lizzy-mcalpine");
//        seatGeekAPI.notifyUser("seventeen");
//        seatGeekAPI.getSomething("toronto");
//        seatGeekAPI.getSomething("rockford");
//        seatGeekAPI.getSomething("hollywood");
    }

}

// headers: Response Headers: {null=[HTTP/1.1 200 OK], Server=[openresty], Fastly-Restarts=[1], X-Timer=[S1701576020.561145,VS0,VS0,VE14], set-cookie=[datadome=i3t2UDmZUTnqtUgnjU9SUVC7QBRs8JgQOpX98DqolX5zD7qxOBiKEvpkG1zYVVKULNxXIlkxq7VdlRIQxRQ99XmgqLvsKUx~zX0nYolZWkGIsJM_pFZPJn4dfiCq6hUl; Max-Age=604800; Domain=.seatgeek.com; Path=/; Secure; SameSite=Lax], Strict-Transport-Security=[max-age=31536000;], X-Served-By=[cache-yyz4577-YYZ], Set-Cookie=[sixpack_client_id=7d5ae117-a4aa-47f7-92dd-b8617b3289f2; max-age=259200000; domain=.api.seatgeek.com; path=/; secure;], x-datadome=[protected], X-XSS-Protection=[1; mode=block], cache-control=[max-age=60], Content-Length=[10308], Age=[4], Content-Type=[application/json], X-Cache=[HIT], X-Content-Type-Options=[nosniff], Connection=[keep-alive], x-datadog-trace-id=[2332432294203607537], Date=[Sun, 03 Dec 2023 04:00:19 GMT], Via=[1.1 varnish], Accept-Ranges=[bytes], access-control-allow-origin=[*], x-robots-tag=[none], Vary=[Accept-Encoding], etag=["37a5ab0b9a73b210b6ffaa129b720b59f3eeab45"], X-Cache-Hits=[1]}

