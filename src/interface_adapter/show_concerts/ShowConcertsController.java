package interface_adapter.show_concerts;


import use_case.show_concerts.ShowConcertsInputBoundary;
import use_case.show_concerts.ShowConcertsInputData;

public class ShowConcertsController {
    final ShowConcertsInputBoundary showConcertsUseCaseInteractor;

    public ShowConcertsController(ShowConcertsInputBoundary showConcertsUseCaseInteractor){
        this.showConcertsUseCaseInteractor = showConcertsUseCaseInteractor;
    }

    public void execute(){
        ShowConcertsInputData showConcertsInputData = new ShowConcertsInputData();
        showConcertsUseCaseInteractor.execute(showConcertsInputData);
    }
}
