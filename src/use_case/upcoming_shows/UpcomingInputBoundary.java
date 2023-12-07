package use_case.upcoming_shows;

/**
 * The {@code UpcomingInputBoundary} interface defines the contract for components
 * that handle input for the upcoming shows use case.
 */
public interface UpcomingInputBoundary {

    /**
     * Executes the upcoming shows use case based on the provided input data.
     *
     * @param upcomingInputData The input data for the upcoming shows use case.
     */
    void execute(UpcomingInputData upcomingInputData);
}
