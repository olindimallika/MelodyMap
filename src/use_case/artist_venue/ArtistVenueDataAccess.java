package use_case.artist_venue;

import entity.Artist;
import entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ArtistVenueDataAccess {

    List<Double> locationFinder(User user);

    List<JSONObject> getEvents(String User) throws Exception;

}
