package interface_adapter.upcoming_shows;

import use_case.upcoming_shows.UpcomingInputBoundary;
import use_case.upcoming_shows.UpcomingInputData;

/**
 * The UpcomingController class serves as an adapter between the user interface and the upcoming shows use case.
 * It encapsulates the interaction logic by accepting user input from the UI (postal code) and invoking the corresponding
 * use case interactor to process the input and retrieve upcoming shows information.
 *
 * Member Variables:
 * The use case interactor responsible for handling upcoming shows related logic.
 *
 * Constructor:
 * Constructs an instance of the controller with the provided UpcomingInputBoundary as the use case interactor.
 *
 * Methods:
 * execute(String): Accepts a postal code from the UI, creates an UpcomingInputData object,
 * and delegates the execution to the underlying use case interactor.
 */
public class UpcomingController {
    /**
     * The use case interactor responsible for handling upcoming shows related logic.
     */
    final UpcomingInputBoundary userUpcomingUseCaseInteractor;

    /**
     * Constructs an instance of the controller with the provided use case interactor.
     *
     * @param userUpcomingUseCaseInteractor The use case interactor to be used by the controller.
     */
    public UpcomingController(UpcomingInputBoundary userUpcomingUseCaseInteractor){
        this.userUpcomingUseCaseInteractor = userUpcomingUseCaseInteractor;
    }

    /**
     * Accepts a postal code from the UI, creates an UpcomingInputData object, and delegates the execution
     * to the underlying use case interactor.
     *
     * @param postalCode The postal code input received from the UI.
     */
    public void execute(String postalCode){
        UpcomingInputData upcomingInputData = new UpcomingInputData(postalCode);

        userUpcomingUseCaseInteractor.execute(upcomingInputData);
    }
}
