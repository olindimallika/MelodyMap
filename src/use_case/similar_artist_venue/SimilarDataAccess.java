package use_case.similar_artist_venue;

import entity.Artist;
import org.json.JSONObject;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SimilarDataAccess {
    HashMap<String, List<String>> getSimilarArtists(List<Artist> favouriteArtists);
    String getEventUrl(JSONObject event);
    String getArtistName(JSONObject event);
    List<Double> locationFinder(User user);
    boolean existsInCoord(String postalCode);
}
