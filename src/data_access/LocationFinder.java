package data_access;

import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationFinder {
    //

    private static final List<Double> geoPoint = new ArrayList<>();

    public List<Double> locationFinder(User user){
        String postalCode = user.getPostalCode();

        try {
            String locationFinderApiKey = "feaad5daa48247fd8ecaa9b3983c9383";
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";
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

                    geoPoint.add(latitude);
                    geoPoint.add(longitude);

                } else {
                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoPoint;
    }

}

//public class LocationFinder {
//
//    private static final List<Double> geoPoint = new ArrayList<>();
//
//    public List<Double> locationFinder(String postalCode){
//
//        String locationFinderApiKey = "daf00ad4979542568d5801316ffd22dd";
//        try {
//            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";
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
//                    geoPoint.add(latitude);
//                    geoPoint.add(longitude);
//
//                } else {
//                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return geoPoint;
//    }
//
//
//    public static void main(String[] args) {
//        // Create a User with a postal code
//          // Replace with the actual postal code you want to test
//
//        // Use the LocationFinder to get coordinates
//        LocationFinder locationFinder = new LocationFinder();
//        List<Double> coordinates = locationFinder.locationFinder("M2J2C6");
//
//        // Display the coordinates
//        if (coordinates.size() == 2) {
//            double latitude = coordinates.get(0);
//            double longitude = coordinates.get(1);
//            System.out.println("Latitude: " + latitude);
//            System.out.println("Longitude: " + longitude);
//        } else {
//            System.out.println("Unable to retrieve coordinates.");
//        }
//    }
//}


