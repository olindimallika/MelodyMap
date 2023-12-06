package use_case.show_concerts;

public interface ShowConcertsOutputBoundary {
    void prepareSuccessView(ShowConcertsOutputData user);
    void prepareFailView(String error);
}
