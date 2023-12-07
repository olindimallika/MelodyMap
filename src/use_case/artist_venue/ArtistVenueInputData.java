package use_case.artist_venue;

import java.util.LinkedHashMap;

/**
 * The `ArtistVenueInputData` class represents input data for artist and venue-related use cases.
 * It encapsulates information about artist tours, allowing for a standardized input format.
 */
public class ArtistVenueInputData {

    /**
     * A LinkedHashMap containing information about artist tours, where keys are artist names
     * and values are corresponding venue names.
     */
    final private LinkedHashMap<String, String> artistTours;

    /**
     * Constructs an `ArtistVenueInputData` object with the specified artist tours.
     *
     * @param artistTours A LinkedHashMap containing information about artist tours.
     */
    public ArtistVenueInputData(LinkedHashMap<String, String> artistTours) {
        this.artistTours = artistTours;
    }

    /**
     * Retrieves the artist tours from the input data.
     *
     * @return A LinkedHashMap containing information about artist tours.
     */
    LinkedHashMap<String, String> getArtistTours() {
        return artistTours;
    }
}
