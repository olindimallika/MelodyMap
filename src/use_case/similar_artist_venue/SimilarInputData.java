package use_case.similar_artist_venue;


/**
 * The SimilarInputData class represents the input data required for the use case of finding similar artists and venues.
 * It encapsulates information such as the user's favorite artists and postal code, providing a convenient way to pass
 * this data between different components in the application.
 */
public class SimilarInputData {
    private String favArtists;
    private String postalCode;

    /**
     * Constructs a new instance of SimilarInputData with the specified postal code and favorite artists.
     *
     * @param postalCode the postal code associated with the user's location
     * @param favArtists a string containing the names of the user's favorite artists
     */
    public SimilarInputData(String postalCode, String favArtists) {
        this.postalCode = postalCode;
        this.favArtists = favArtists;
    }

    /**
     * Default constructor for SimilarInputData. Creates an instance with default values for postal code and favorite artists.
     * This constructor may be used when creating an empty instance that will be populated later.
     */
    public SimilarInputData() {

    }


    /**
     * Gets the postal code associated with this SimilarInputData instance.
     *
     * @return a string representing the postal code
     */
    String getPostalCode(){ return postalCode;}

    /**
     * Gets the string containing the names of the user's favorite artists associated with this SimilarInputData instance.
     *
     * @return a string containing the names of the user's favorite artists
     */
    String getFavArtists() {
        return favArtists;
    }
}