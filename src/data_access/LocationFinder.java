package data_access;

import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LocationFinder class is responsible for retrieving the geographical coordinates (latitude and longitude)
 * based on a given postal code using the OpenCageData API.
 */
/**
 * The LocationFinder class is responsible for retrieving the geographical coordinates (latitude and longitude)
 * based on a given postal code using the OpenCageData API.
 */
public class LocationFinder {

    /**
     * A constant representing an empty postal code (initialized, but not used in the code).
     */
    private final String postalCode = "";

    /**
     * A static list to store the geographical coordinates (latitude and longitude) obtained from the API response.
     */
    private static final List<Double> geoPoint = new ArrayList<>();

    /**
     * Retrieves the geographical coordinates for a given user's postal code using the OpenCageData API.
     *
     * @param user The user object containing the postal code.
     * @return A list containing the latitude and longitude of the specified postal code.
     */
    public List<Double> locationFinder(User user) {
        // Extract postal code from the user object
        String postalCode = user.getPostalCode();

        try {
            // OpenCageData API key for geocoding
            String locationFinderApiKey = "590432017a624836975180e8e71df0b1";

            // Construct the URL for the OpenCageData API request
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";

            // Create an OkHttpClient to send the HTTP request
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            // Execute the HTTP request and obtain the response
            try (Response response = client.newCall(request).execute()) {
                // Read the response body as a string
                String responseBody = response.body().string();

                // Parse the response as a JSON object
                JSONObject json = new JSONObject(responseBody);

                // Extract the geometry information from the JSON response
                JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");

                // Extract latitude and longitude from the API response
                double latitude = location.getDouble("lat");
                double longitude = location.getDouble("lng");

                // Add the coordinates to the geoPoint list
                geoPoint.add(latitude);
                geoPoint.add(longitude);
            }
        } catch (IOException e) {
            // Handle potential IO exceptions, such as network errors
            e.printStackTrace();
        }
        return geoPoint;
    }

    /**
     * Gets the empty postal code constant.
     *
     * @return The empty postal code constant.
     */
    public String getPostalCode() {
        return postalCode;
    }
}
