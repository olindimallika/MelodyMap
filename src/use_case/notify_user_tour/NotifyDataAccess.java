package use_case.notify_user_tour;

import entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface NotifyDataAccess {
    JSONObject getPerformerInfo(String artistName);

    String hasATour(String artistName, String classification) throws IOException, InterruptedException;
    String getPostalCode();

    HashMap<String, List<String>> getSimilarArtists(List<String> favouriteArtists);
}