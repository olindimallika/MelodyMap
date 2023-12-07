package use_case.upcoming_shows;

/**
 * The {@code UpcomingOutputBoundary} interface defines methods for presenting the results of the upcoming shows use case
 * to the user interface. Implementing classes are responsible for formatting and displaying information related to
 * upcoming shows, both in case of success and failure.
 */
public interface UpcomingOutputBoundary {

    /**
     * Prepares and presents the success view to the user interface with the provided upcoming concert information.
     *
     * @param upcomingConcert The {@link UpcomingOutputData} object containing information about upcoming concerts.
     */
    void prepareSuccessView(UpcomingOutputData upcomingConcert);

    /**
     * Prepares and presents the fail view to the user interface with the provided error message.
     *
     * @param error The error message explaining the reason for the failure in the upcoming shows use case.
     */
    void prepareFailView(String error);
}
