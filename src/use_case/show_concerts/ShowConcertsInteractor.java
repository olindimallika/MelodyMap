package use_case.show_concerts;


public class ShowConcertsInteractor implements ShowConcertsInputBoundary{

    final ShowConcertsOutputBoundary showConcertsPresenter;

    public ShowConcertsInteractor(ShowConcertsOutputBoundary showConcertsOutputBoundary){
        this.showConcertsPresenter = showConcertsOutputBoundary;
    }

    public void execute(){
        showConcertsPresenter.prepareSuccessView();

    }
}
