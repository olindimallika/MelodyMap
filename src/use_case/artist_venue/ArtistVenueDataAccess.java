package use_case.artist_venue;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The `ArtistVenueDataAccess` interface defines methods for accessing data related to artists and venues.
 * Implementing classes provide specific functionality for retrieving information such as event URLs,
 * artist names, favorite artists, and upcoming artist shows.
 */
public interface ArtistVenueDataAccess {

    /**
     * Retrieves the event URL from the provided event JSON object.
     *
     * @param event The JSON object representing an event.
     * @return The event URL as a string.
     */
    String getEventUrl(JSONObject event);

    /**
     * Retrieves the artist name from the provided event JSON object.
     *
     * @param event The JSON object representing an event.
     * @return The artist name as a string.
     */
    String getArtistName(JSONObject event);

    /**
     * Retrieves a list of favorite artists.
     *
     * @return A string containing the names of favorite artists.
     */
    String getFavouriteArtists();

    /**
     * Retrieves upcoming artist shows based on a list of events for multiple artists.
     *
     * @param artistsEvents A list containing events for multiple artists, each represented by a list of JSON objects.
     * @return A LinkedHashMap where keys are artist names and values are lists of corresponding upcoming concert details.
     */
    LinkedHashMap<String, List<String>> getUpcomingArtistShows(List<List<JSONObject>> artistsEvents);
}
