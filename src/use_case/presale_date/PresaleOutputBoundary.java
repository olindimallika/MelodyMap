package use_case.presale_date;

/**
 * The output boundary for the Presale use case, defining methods to prepare views for success and failure scenarios.
 */
public interface PresaleOutputBoundary {

    /**
     * Prepares the success view for the Presale use case, providing the necessary output data.
     *
     * @param user The output data containing formatted presale information.
     */
    void prepareSuccessView(PresaleOutputData user);

    /**
     * Prepares the failure view for the Presale use case, providing an error message.
     *
     * @param error The error message to be displayed in case of a failure.
     */
    void prepareFailView(String error);
}
