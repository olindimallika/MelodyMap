package use_case.artist_venue;

import entity.Artist;
import entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface ArtistVenueDataAccess {
    String getEventUrl(JSONObject event);

    String getArtistName(JSONObject event);

    String getFavouriteArtists();


}