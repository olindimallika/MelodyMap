import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocationFinder {

    private static final String API_KEY = "0e7e66d3a7b44c6a8e5d7b6c7d61f4f7";

    public static List<Double> latlong = new ArrayList<>();

    public static List<Double> latlongy() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Canadian postal code (capital, no space): ");
        String postalCode = scanner.nextLine();

        try {
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + API_KEY + "&q=" + postalCode + "&countrycode=CA";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");

                    double latitude = location.getDouble("lat");
                    double longitude = location.getDouble("lng");

                    latlong.add(latitude);
                    latlong.add(longitude);
                    System.out.println(latlong);
                    // comment
                } else {
                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(latlong);
        return latlong;
    }

    public static void main(String[] args) {

    }
}