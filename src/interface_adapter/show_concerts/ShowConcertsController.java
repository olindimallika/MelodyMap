package interface_adapter.show_concerts;

import use_case.show_concerts.ShowConcertsInputBoundary;


public class ShowConcertsController {
  
    // The show concerts use case interactor responsible for executing the show concerts logic
    final ShowConcertsInputBoundary showConcertsUseCaseInteractor;

    /**
     * Constructs a new instance of ShowConcertsController
     *
     * @param showConcertsUseCaseInteractor The show concerts use case interactor to be injected into the controller.
     */
    public ShowConcertsController(ShowConcertsInputBoundary showConcertsUseCaseInteractor){
        this.showConcertsUseCaseInteractor = showConcertsUseCaseInteractor;
    }

    /**
     * Executes the show concerts use case by invoking the corresponding interactor
     */
    public void execute(){
        showConcertsUseCaseInteractor.execute();
    }

}



