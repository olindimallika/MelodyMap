package use_case.notify_user_tour;

import org.json.JSONObject;

public interface NotifyDataAccess {
    JSONObject getPerformerInfo(String artistName);
    boolean existsInApi();
}
