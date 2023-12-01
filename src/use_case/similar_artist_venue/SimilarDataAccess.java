package use_case.similar_artist_venue;

import org.json.JSONObject;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SimilarDataAccess {
    HashMap<String, ArrayList<String>> getSimilarArtists(List<String> favouriteArtists);
    String getEventUrl(JSONObject event);
    String getArtistName(JSONObject event);
    List<Double> locationFinder(User user);
}
