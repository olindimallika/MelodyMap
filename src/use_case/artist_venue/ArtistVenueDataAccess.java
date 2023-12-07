package use_case.artist_venue;


import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

public interface ArtistVenueDataAccess {
    String getEventUrl(JSONObject event);

    String getArtistName(JSONObject event);

    String getFavouriteArtists();

    LinkedHashMap<String, List<String>> getUpcomingArtistShows(List<List<JSONObject>> artistsEvents);
}