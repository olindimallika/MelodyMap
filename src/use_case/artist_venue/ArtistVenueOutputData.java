package use_case.artist_venue;

import java.util.*;

/**
 * The `ArtistVenueOutputData` class represents the output data containing information about
 * upcoming artist shows. It encapsulates a LinkedHashMap where keys are artist names
 * and values are lists of corresponding upcoming concert details.
 */
public class ArtistVenueOutputData {

    /**
     * A LinkedHashMap containing information about upcoming artist shows, where keys are artist names
     * and values are lists of corresponding concert details.
     */
    private final LinkedHashMap<String, List<String>> artistShows;

    /**
     * Constructs an `ArtistVenueOutputData` object with the specified upcoming artist shows.
     *
     * @param artistShows A LinkedHashMap containing information about upcoming artist shows.
     */
    public ArtistVenueOutputData(LinkedHashMap<String, List<String>> artistShows) {
        this.artistShows = artistShows;
    }

    /**
     * Retrieves the upcoming artist shows from the output data.
     *
     * @return A LinkedHashMap containing information about upcoming artist shows.
     */
    public LinkedHashMap<String, List<String>> getUpcomingArtistShows() {
        return artistShows;
    }
}



