package use_case.presale_date;

public interface PresaleOutputBoundary {
    void prepareSuccessView(PresaleOutputData user);
    void prepareFailView(String error);
}

