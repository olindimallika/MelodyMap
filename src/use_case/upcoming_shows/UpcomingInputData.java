package use_case.upcoming_shows;

/**
 * The {@code UpcomingInputData} class represents the input data for the upcoming shows use case.
 * It encapsulates information related to the user's postal code, allowing the application to retrieve
 * upcoming shows based on the specified location.
 *
 * <p>Instances of this class are immutable, ensuring that the postal code remains constant after initialization.
 */
public class UpcomingInputData {

    /**
     * The postal code provided as input for the upcoming shows use case.
     */
    private final String postalCode;

    /**
     * Constructs a new instance of {@code UpcomingInputData} with the specified postal code.
     *
     * @param postalCode The postal code associated with the upcoming shows request.
     * @throws IllegalArgumentException if the provided postal code is null or empty.
     */
    public UpcomingInputData(String postalCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code must not be null or empty.");
        }
        this.postalCode = postalCode;
    }

    /**
     * Gets the postal code associated with the upcoming shows request.
     *
     * @return The postal code as a string.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns a string representation of the {@code UpcomingInputData} object.
     *
     * @return A string representation containing the class name and the postal code.
     */
    @Override
    public String toString() {
        return "UpcomingInputData{" +
                "postalCode='" + postalCode + '\'' +
                '}';
    }
}
