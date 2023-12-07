package use_case.notify_user_tour;

/**
 * The NotifyInputBoundary interface represents the input boundary for the "Notify User Tour" use case.
 * It defines a single method, {@code execute}, which is responsible for executing the use case with the provided input data.
 */
public interface NotifyInputBoundary {

    /**
     * Executes the "Notify User Tour" use case with the given input data.
     *
     * @param notifyInputData The input data containing information needed for notifying users about tours.
     */
    void execute(NotifyInputData notifyInputData);
}
