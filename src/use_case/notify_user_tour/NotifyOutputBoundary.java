package use_case.notify_user_tour;

public interface NotifyOutputBoundary {
    void prepareSuccessView(NotifyOutputData user);
    void prepareFailView(String error);
}
