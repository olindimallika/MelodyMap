package use_case.similar_artist_venue;

import entity.Artist;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * The SimilarDataAccess interface defines methods for accessing data related to similar artists and events.
 * Implementations of this interface provide the functionality to retrieve similar artists and find events
 * based on location, radius, unit, classification, and artist name.
 */
public interface SimilarDataAccess {
    /**
     * Retrieves a map of similar artists for each provided artist name.
     * DONT KNOW ABOUT THIS
     * @param similarArtists a list of artist names for which similar artists are to be retrieved
     * @return a HashMap where each key is an artist name and the corresponding value is a list of similar artist names
     */
    HashMap<String, List<String>> getSimilarArtists(List<String> favouriteArtists);

    /**
     * Finds events based on the provided latitude, longitude, radius, unit, classification, and artist name.
     *
     * @param latlong        a list containing the latitude and longitude values for the location
     * @param radius         the radius within which to search for events
     * @param unit           the unit of measurement for the radius (in the case of the application, "miles")
     * @param classification the classification of events to search for
     * @param artistName     the name of the artist for which to find events
     * @return a list of JSONObjects representing the found events
     * @throws IOException            if an I/O error occurs during the data retrieval process
     * @throws InterruptedException   if the thread is interrupted during the data retrieval process
     */
    List<JSONObject> findEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification,
                                           String artistName) throws IOException, InterruptedException;

}