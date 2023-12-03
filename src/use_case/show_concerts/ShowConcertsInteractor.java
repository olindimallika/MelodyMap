package use_case.show_concerts;

import java.util.ArrayList;

public class ShowConcertsInteractor implements ShowConcertsInputBoundary{
    final ShowConcertsDataAccess userDataAccessObject;

    final ShowConcertsOutputBoundary showConcertsPresenter;

    public ShowConcertsInteractor(ShowConcertsDataAccess userDataAccessInterface,
                           ShowConcertsOutputBoundary showConcertsOutputBoundary){
        this.userDataAccessObject = userDataAccessInterface;
        this.showConcertsPresenter = showConcertsOutputBoundary;
    }

    public void execute(ShowConcertsInputData showConcertsInputData){
        ShowConcertsOutputData showConcertsOutputData = new ShowConcertsOutputData();
        showConcertsPresenter.prepareSuccessView(showConcertsOutputData);

    }
}
