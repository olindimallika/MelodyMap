package use_case.show_concerts;

/**
 * The ShowConcertsInputBoundary interface defines the input boundary for the use case of showing concerts.
 * Classes implementing this interface are responsible for handling input data and orchestrating the execution of the
 * logic required to display concert information to the user.
 *
 * This interface adheres to the Clean Architecture principles, serving as the boundary between the application core
 * (use case) and the external layers (such as controllers and presenters). It encapsulates the interaction between
 * user input and the internal logic, providing a clear separation of concerns.
 *
 * The single method,  execute, takes a ShowConcertsInputData object as input, encapsulating the data
 * necessary for the execution of the show concerts use case. Implementing classes are expected to interpret and process
 * the input data, triggering the appropriate actions to fulfill the user's request.
 *
 * Implementations of this interface should focus on handling the input data and delegating the execution to the
 * appropriate components within the application core. They should not be concerned with specific details of user
 * interfaces or external systems.
 *
 * It is assumed that the actual logic for showing concerts is implemented in collaborating classes,
 * following the principles of the use case design pattern.
 *
 * Author: Bea Castro
 */
public interface ShowConcertsInputBoundary {

    /**
     * Executes the show concerts use case based on the provided input data.
     *
     * @param showConcertsInputData The input data object encapsulating information necessary for the show concerts operation.
     */
    void execute(ShowConcertsInputData showConcertsInputData);
}
