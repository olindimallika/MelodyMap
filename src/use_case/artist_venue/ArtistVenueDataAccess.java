package use_case.artist_venue;


import org.json.JSONObject;

//
public interface ArtistVenueDataAccess {
    String getEventUrl(JSONObject event);

    String getArtistName(JSONObject event);

    String getFavouriteArtists();


}