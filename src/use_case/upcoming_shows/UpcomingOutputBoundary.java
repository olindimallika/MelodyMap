package use_case.upcoming_shows;

public interface UpcomingOutputBoundary {
    void prepareSuccessView(UpcomingOutputData upcomingConcert);

    void prepareFailView(String error);
}
