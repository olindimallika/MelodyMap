package use_case.presale_date;

import entity.User;
import org.json.JSONObject;
import java.util.List;

/**
 * An interface defining the contract for accessing presale-related data.
 */
public interface PresaleDataAccess {

    /**
     * Retrieves a list of events based on latitude, longitude, radius, unit, classification, and user information.
     *
     * @param radius        The radius for the geographic search.
     * @param unit          The unit of measurement for the radius (e.g., "miles").
     * @param classification The classification of events (e.g., "music").
     * @param user          The user for whom events are being retrieved.
     * @return A list of JSONObjects representing events.
     * @throws Exception If an error occurs during the retrieval process.
     */
    List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws Exception;

    /**
     * Retrieves a list of presale dates associated with events from coordinates of user.
     *
     * @return A list of presale dates.
     */
    List<String> getPresaleDates();

    /**
     * Adds event information represented by a JSONObject.
     *
     * @param event The JSONObject containing information about an event.
     */
    void addEventInfo(JSONObject event);

    /**
     * Checks if a given presale end date has already passed.
     *
     * @param presaleEndDate The presale end date to be checked.
     * @return True if the presale has ended; otherwise, false.
     */
    boolean isPastPresale(String presaleEndDate);

    /**
     * Retrieves a list of event URLs.
     *
     * @return A list of event URLs.
     */
    List<String> getEventUrls();

    /**
     * Retrieves the artist name from the provided JSONObject representing an event.
     *
     * @param event The JSONObject containing information about an event.
     * @return The name of the artist associated with the event.
     */
    String getArtistName(JSONObject event);

    /**
     * Formats and returns a string representation of presale information.
     *
     * @param artName     The name of the artist.
     * @param artUrl      The URL of the event.
     * @param artPresale  The presale information.
     * @return The formatted string.
     */
    String formatOutputPresale(String artName, String artUrl, String artPresale);

    /**
     * Gets the formatted output of presale information.
     *
     * @return The formatted output of presale information.
     */
    String getFormatOutputPresale();

}
