package use_case.notify_user_tour;

import java.util.LinkedHashMap;

/**
 * The {@code NotifyOutputData} class represents the output data for the "Notify User Tour" use case.
 * It encapsulates information about artists and their tour status to be presented to the user.
 */
public class NotifyOutputData {

    /**
     * A map containing artist names as keys and their corresponding tour status as values.
     */
    private final LinkedHashMap<String, String> artistOnTour;

    /**
     * Constructs a new {@code NotifyOutputData} object with the specified artist and tour status map.
     *
     * @param artistOnTour A map containing artist names and their corresponding tour status.
     */
    public NotifyOutputData(LinkedHashMap<String, String> artistOnTour) {
        this.artistOnTour = artistOnTour;
    }

    /**
     * Gets the map containing artist names and their corresponding tour status.
     *
     * @return A map where each entry represents an artist name and its tour status.
     */
    public LinkedHashMap<String, String> getArtistOnTour() {
        return artistOnTour;
    }
}
