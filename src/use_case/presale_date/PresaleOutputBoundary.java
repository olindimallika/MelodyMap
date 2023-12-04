package use_case.presale_date;

import use_case.upcoming_shows.UpcomingOutputData;

public interface PresaleOutputBoundary {
    void prepareSuccessView(PresaleOutputData user);

    void prepareFailView(String error);
}

