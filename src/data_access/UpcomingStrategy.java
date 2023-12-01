package data_access;

import entity.User;
import org.json.JSONObject;
import use_case.EventStrategy;
import data_access.EventsByUrl;

import java.util.List;


public class UpcomingStrategy implements EventStrategy<List<JSONObject>> {
    @Override
    public List<JSONObject> getEvents(User user) {
        return null;
    }
}
