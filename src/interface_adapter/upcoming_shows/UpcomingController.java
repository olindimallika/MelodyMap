package interface_adapter.upcoming_shows;

import use_case.upcoming_shows.UpcomingInputBoundary;
import use_case.upcoming_shows.UpcomingInputData;

public class UpcomingController {
    final UpcomingInputBoundary userUpcomingUseCaseInteractor;

    public UpcomingController(UpcomingInputBoundary userUpcomingUseCaseInteractor){
        this.userUpcomingUseCaseInteractor = userUpcomingUseCaseInteractor;
    }

    public void execute(String postalCode){
        UpcomingInputData upcomingInputData = new UpcomingInputData(postalCode);

        userUpcomingUseCaseInteractor.execute(upcomingInputData);
    }
}