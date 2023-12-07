package interface_adapter.upcoming_shows;

import use_case.upcoming_shows.UpcomingInputBoundary;
import use_case.upcoming_shows.UpcomingInputData;

/**
 * The UpcomingController class is responsible for handling user input and triggering the execution
 * of the upcoming shows functionality through the associated UpcomingInputBoundary.
 */
public class UpcomingController {
    /**
     * The UpcomingInputBoundary associated with this controller to handle the upcoming shows use case.
     */
    final UpcomingInputBoundary userUpcomingUseCaseInteractor;

    /**
     * Constructs a new UpcomingController with the given UpcomingInputBoundary.
     *
     * @param userUpcomingUseCaseInteractor The UpcomingInputBoundary associated with this controller.
     */
    public UpcomingController(UpcomingInputBoundary userUpcomingUseCaseInteractor) {
        this.userUpcomingUseCaseInteractor = userUpcomingUseCaseInteractor;
    }

    /**
     * Executes the upcoming shows functionality with the provided postal code.
     *
     * @param postalCode The postal code input provided by the user.
     */
    public void execute(String postalCode) {
        UpcomingInputData upcomingInputData = new UpcomingInputData(postalCode);
        userUpcomingUseCaseInteractor.execute(upcomingInputData);
    }
}
