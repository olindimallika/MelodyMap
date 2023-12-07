package use_case.notify_user_tour;

import entity.User;
import org.json.JSONObject;

import java.io.IOException;

public interface NotifyDataAccess {
    String hasATour(String artistName, String classification) throws IOException, InterruptedException;
}
