package use_case.presale_date;

import entity.Artist;

import java.util.ArrayList;

/**
 * Represents the input data for the Presale use case.
 * This class holds information such as postal code and favorite artists.
 */
public class PresaleInputData {

    /** The postal code for which Presale information is requested. */
    private final String postalCode;

    /** A string containing the names of favorite artists separated by commas. */
    private final String favArtists;

    /**
     * Constructs a new instance of PresaleInputData with the specified postal code and favorite artists.
     *
     * @param postalCode The postal code for which Presale information is requested.
     * @param favArtists A string containing the names of favorite artists separated by commas.
     */
    public PresaleInputData(String postalCode, String favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }

    /**
     * Gets the postal code associated with this input data.
     *
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the string containing the names of favorite artists separated by commas.
     *
     * @return A string with the names of favorite artists.
     */
    public String getFavArtists() {
        return favArtists;
    }
}
