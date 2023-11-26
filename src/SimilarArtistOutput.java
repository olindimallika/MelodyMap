import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SimilarArtistOutput{
    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";
    SimilarArtistInput in = new SimilarArtistInput();
    static ArrayList<String> userInput = SimilarArtistInput.similar_Artists();
    String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

    FavArtistsShow show = new FavArtistsShow(apiKey);

    static HashMap<String, ArrayList<String>> map = new HashMap<>();


    public static void main(String[] args) throws Exception {

            String token = getToken();
            for (int i = 0; i < userInput.size(); i++) {
                String list_artist = userInput.get(i);
                JSONObject artist = searchForArtist(token, list_artist);
                if (artist != null) {
                    String artistId = artist.getString("id");
                    JSONObject similarArtists = getSimilarArtists(token, artistId);
                    if (similarArtists != null) {
                        ArrayList<String> similarArtistNames = new ArrayList<>();
                        JSONArray artists = similarArtists.getJSONArray("artists");
                        for (int j = 0; j < Math.min(4, artists.length()) ; j++) {
                            similarArtistNames.add(artists.getJSONObject(j).getString("name"));
                            System.out.println(artists.getJSONObject(j).getString("name"));
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
            for (int k = 0; k < map.size(); k++) {
                String find_shows = show.printEventUrls();

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



