package use_case.upcoming_shows;

import org.json.JSONObject;

import java.util.*;

/**
 * The {@code UpcomingDataAccess} interface defines methods for accessing upcoming shows data.
 * Implementing classes are responsible for retrieving information from a data source,
 * such as a database or an external API, and providing it to the application.
 */
public interface UpcomingDataAccess {

    /**
     * Gets the event URL from the provided JSON object.
     *
     * @param event The JSON object representing an upcoming show event.
     * @return The event URL as a string.
     */
    String getEventUrl(JSONObject event);

    /**
     * Gets the artist name from the provided JSON object.
     *
     * @param event The JSON object representing an upcoming show event.
     * @return The artist name as a string.
     */
    String getArtistName(JSONObject event);

    /**
     * Gets a mapping of upcoming shows with event names and their corresponding event URLs.
     *
     * @param events A list of JSON objects representing upcoming show events.
     * @return A LinkedHashMap where keys are event names and values are event URLs.
     */
    LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events);
}
