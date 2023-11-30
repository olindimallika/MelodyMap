package use_case.upcoming_shows;

public interface UpcomingOutputBoundary {
    void prepareSuccessView(UpcomingOutputData user);

    void prepareFailView(String error);
}
