package use_case.notify_user_tour;

/**
 * The {@code NotifyOutputBoundary} interface defines methods for preparing views in response to the "Notify User Tour" use case.
 * It specifies methods for both successful and unsuccessful outcomes.
 */
public interface NotifyOutputBoundary {

    /**
     * Prepares the view for a successful execution of the "Notify User Tour" use case.
     *
     * @param notifyOutputData The output data containing information to be presented to the user.
     */
    void prepareSuccessView(NotifyOutputData notifyOutputData);

    /**
     * Prepares the view for a failed execution of the "Notify User Tour" use case.
     *
     * @param error A string describing the error that occurred during the use case execution.
     */
    void prepareFailView(String error);
}
