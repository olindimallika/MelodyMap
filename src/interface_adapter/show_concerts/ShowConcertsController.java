package interface_adapter.show_concerts;


import use_case.show_concerts.ShowConcertsInputBoundary;

public class ShowConcertsController {
    final ShowConcertsInputBoundary showConcertsUseCaseInteractor;

    public ShowConcertsController(ShowConcertsInputBoundary showConcertsUseCaseInteractor){
        this.showConcertsUseCaseInteractor = showConcertsUseCaseInteractor;
    }

    public void execute(){
        showConcertsUseCaseInteractor.execute();
    }
}
