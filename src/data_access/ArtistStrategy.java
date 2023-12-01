package data_access;

import entity.User;
import org.json.JSONObject;
import use_case.EventStrategy;
import data_access.EventsByUrl;

import java.util.List;

public class ArtistStrategy implements EventStrategy<List<List<JSONObject>>> {

    // returns a list of lists of events, where every inner list is are a bunch of events for one specific user

    @Override
    public List<List<JSONObject>> getEvents(User user) {
        return null;
    }
}
