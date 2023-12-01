package use_case.presale_date;

import use_case.upcoming_shows.UpcomingOutputData;

public interface PresaleOutputBoundary {
    public void prepareSuccessView(PresaleOutputData user);

    public void prepareFailView(String error);
}

