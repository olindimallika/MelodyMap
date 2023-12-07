package interface_adapter.upcoming_shows;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import use_case.upcoming_shows.UpcomingOutputBoundary;
import use_case.upcoming_shows.UpcomingOutputData;

/**
 * The UpcomingPresenter class acts as a presenter for the
 * upcoming shows use case. It is responsible for preparing the data to be displayed in the UI after the execution
 * of the upcoming shows use case. This presenter communicates with the view models and the view manager to update
 * the UI state accordingly.
 *
 * Member Variables:
 * upcomingViewModel: The view model responsible for managing the state related to upcoming shows.
 * showConcertsViewModel: The view model responsible for managing the state related to the show concerts view.
 * viewManagerModel: The view model responsible for managing the active view within the application.
 *
 * Constructor:
 * Constructs an instance of the presenter with the necessary view models and initializes them with the provided models.
 *
 * Methods:
 * prepareSuccessView(UpcomingOutputData): Overrides the method to handle the successful execution of
 *    the upcoming shows use case. It updates the show concerts view model with the retrieved concert data, sets the
 *    active view to the show concerts view, and notifies the view manager about the change.
 * prepareFailView(String): Overrides the method to handle the failure of the upcoming shows use case.
 *    It updates the upcoming view model with the error information and notifies the view about the change.
 *
 */
public class UpcomingPresenter implements UpcomingOutputBoundary {

    // The view model responsible for managing the state related to upcoming shows.
    private final UpcomingViewModel upcomingViewModel;

    // The view model responsible for managing the state related to the show concerts view.
    private final ShowConcertsViewModel showConcertsViewModel;

    // The view model responsible for managing the active view within the application.
    private final ViewManagerModel viewManagerModel;


    /**
     * Constructs an instance of the presenter with the necessary view models.
     *
     * @param viewManagerModel The view model for managing the active view.
     * @param showConcertsViewModel The view model for managing the show concerts view state.
     * @param upcomingViewModel The view model for managing the upcoming shows view state.
     */
    public UpcomingPresenter(ViewManagerModel viewManagerModel,
                             ShowConcertsViewModel showConcertsViewModel,
                             UpcomingViewModel upcomingViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showConcertsViewModel = showConcertsViewModel;
        this.upcomingViewModel = upcomingViewModel;
    }

    /**
     * Overrides the method to handle the successful execution of the upcoming shows use case.
     * It updates the show concerts view model with the retrieved concert data, sets the active
     * view to the show concerts view, and notifies the view manager about the change.
     *
     * @param response The output data containing information about upcoming concerts.
     */
    @Override
    public void prepareSuccessView(UpcomingOutputData response) {
        // On success, we want to switch to the show concerts view

        ShowConcertsState showConcertsState = showConcertsViewModel.getState();
        showConcertsState.setConcerts(response.getUpcomingConcerts());
        this.showConcertsViewModel.setState(showConcertsState);
        this.showConcertsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showConcertsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Overrides the method to handle the failure of the upcoming shows use case.
     * It updates the upcoming view model with the error information and notifies
     * the view about the change.
     *
     * @param error The error message indicating the cause of the failure.
     */
    @Override
    public void prepareFailView(String error) {
        UpcomingState upcomingState = upcomingViewModel.getState();
        upcomingState.setPostalCodeError(error);
        upcomingState.setUpcomingShowsError(error);
        upcomingViewModel.firePropertyChanged();
    }
}