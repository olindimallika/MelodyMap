package use_case.notify_user_tour;

import entity.User;
import org.json.JSONObject;

public interface NotifyDataAccess {
    JSONObject getPerformerInfo(String artistName);
    boolean existsInApi();
    String getPostalCode();
}
