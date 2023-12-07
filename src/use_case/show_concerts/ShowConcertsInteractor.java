package use_case.show_concerts;

/**
 * The ShowConcertsInteractor class represents the interactor component for the show concerts use case.
 * It implements the ShowConcertsInputBoundary interface, defining the behavior required to execute the show concerts logic.
 *
 * This class serves as the bridge between the application core and external layers, orchestrating the flow of information
 * and interactions during the execution of the show concerts use case. It collaborates with the data access layer to fetch
 * relevant information and communicates with the presentation layer to display the results to the user.
 *
 * The constructor of this class takes instances of ShowConcertsDataAccess and ShowConcertsOutputBoundary
 * as parameters, establishing dependencies on the data access layer and the presenter. This adheres to the Dependency Inversion Principle,
 * allowing the interactor to depend on abstractions rather than concrete implementations.
 *
 * The execute method, mandated by the ShowConcertsInputBoundary interface, is responsible for initiating
 * the execution of the show concerts logic. Currently, it creates an instance of ShowConcertsOutputData and instructs
 * the presenter to prepare a success view. In a real-world scenario, this method would involve interactions with the data access
 * layer to fetch relevant concert information based on the input data.
 *
 * It is essential to note that this class is part of a larger use case, and the actual logic for fetching and processing
 * concert information is expected to be implemented in collaborating classes, such as the data access objects and presenters.
 *
 * This class assumes a specific architecture and design pattern within the larger application, where components adhere to
 * certain interfaces (ShowConcertsInputBoundary, ShowConcertsDataAccess, ShowConcertsOutputBoundary). Modifications to this class
 * should be done with consideration for the overall application architecture and design.
 *
 * Author: Bea Castro
 */
public class ShowConcertsInteractor implements ShowConcertsInputBoundary{

    // The presenter responsible for preparing and displaying the results to the user
    final ShowConcertsOutputBoundary showConcertsPresenter;

    /**
     * Constructs a new instance of ShowConcertsInteractor
     *
     * @param showConcertsOutputBoundary The presenter for displaying the results to the user.
     */
    public ShowConcertsInteractor(ShowConcertsOutputBoundary showConcertsOutputBoundary){
        this.showConcertsPresenter = showConcertsOutputBoundary;
    }

    /**
     * Executes the show concerts logic based on the provided input data.
     *
     */
    public void execute(){
        // Instruct the presenter to prepare a success view
        showConcertsPresenter.prepareSuccessView();

    }


}
