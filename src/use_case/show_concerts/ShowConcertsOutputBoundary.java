package use_case.show_concerts;

/**
 * The ShowConcertsOutputBoundary interface defines the contract for presenting the results of the show concerts use case.
 * Implementations of this interface are responsible for preparing and displaying views based on the outcome of the show concerts logic.
 *
 * This interface adheres to the Dependency Inversion Principle, serving as the boundary between the application core (use case)
 * and the external layers (such as controllers and views). It abstracts the details of presentation, allowing the show concerts use case
 * to remain decoupled from specific user interfaces or external display mechanisms.
 *
 * The two methods, prepareSuccessView and prepareFailView, handle the presentation of success and failure scenarios,
 * respectively. Implementing classes should interpret the provided data and error information to create appropriate views for the user.
 *
 * It is crucial for implementations to handle the preparation of views efficiently and in a user-friendly manner. The success view
 * may include details about upcoming concerts, while the fail view communicates errors or lack of available information.
 *
 * It is assumed that the actual implementations of this interface will interact with the user interface or other external
 * systems to present the results of the show concerts use case. The specifics of view preparation are left to the implementing classes.
 *
 * Author: Bea Castro
 */
public interface ShowConcertsOutputBoundary {

    /**
     * Prepares and displays a success view
     *
     */
    void prepareSuccessView();

    /**
     * Prepares and displays a fail view based on the provided error message.
     *
     * @param error The error message describing the reason for the failure.
     */
    void prepareFailView(String error);
}