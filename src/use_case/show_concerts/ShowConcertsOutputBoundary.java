package use_case.show_concerts;

public interface ShowConcertsOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
