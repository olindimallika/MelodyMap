package use_case.upcoming_shows;

import java.util.LinkedHashMap;

/**
 * The {@code UpcomingOutputData} class represents the output data for the upcoming shows use case.
 * It encapsulates information related to upcoming concerts formatted for presentation to the user interface.
 */
public class UpcomingOutputData {

    /**
     * A mapping of upcoming concerts where keys are event names and values are event URLs.
     */
    private final LinkedHashMap<String, String> upcomingConcerts;

    /**
     * Constructs a new instance of {@code UpcomingOutputData} with the specified upcoming concerts information.
     *
     * @param upcomingConcerts A mapping of upcoming concerts where keys are event names and values are event URLs.
     */
    public UpcomingOutputData(LinkedHashMap<String, String> upcomingConcerts) {
        this.upcomingConcerts = upcomingConcerts;
    }

    /**
     * Gets the mapping of upcoming concerts where keys are event names and values are event URLs.
     *
     * @return A LinkedHashMap containing upcoming concerts information.
     */
    public LinkedHashMap<String, String> getUpcomingConcerts() {
        return upcomingConcerts;
    }

    /**
     * Returns a string representation of the {@code UpcomingOutputData} object.
     *
     * @return A string representation containing the class name and the upcoming concerts information.
     */
    @Override
    public String toString() {
        return "UpcomingOutputData{" +
                "upcomingConcerts=" + upcomingConcerts +
                '}';
    }
}
