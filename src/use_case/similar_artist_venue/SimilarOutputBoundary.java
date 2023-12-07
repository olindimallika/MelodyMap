package use_case.similar_artist_venue;

/**
 * The `SimilarOutputBoundary` interface defines the contract for components responsible for preparing and presenting the
 * output view related to the use case of finding similar artists and venues. It encapsulates methods to handle both successful
 * and failed outcomes.
 */
public interface SimilarOutputBoundary {

    /**
     * Prepares the view for a successful outcome of the similar artists and venues discovery use case.
     *
     * @param similarArtistsData The data containing the results of the successful operation, including information about
     *                            similar artists and their associated venues.
     *
     * This method is called when the execution of the use case is successful. It prepares the view to present the user
     * with links to the shows for similar artists, ensuring a clear and coherent representation for the user.
     */
    public void prepareSuccessView(SimilarOutputData similarArtistsData);

    /**
     * Prepares the view for a failed outcome of the similar artists and venues discovery use case.
     *
     * @param errorMessage A message explaining the cause of the failure.
     *
     * This method is called when the execution of the use case encounters an issue or fails to produce meaningful results.
     * It handles the presentation of an error message or notification to inform the user about the encountered problem.
     */
    public void prepareFailView(String errorMessage);
}