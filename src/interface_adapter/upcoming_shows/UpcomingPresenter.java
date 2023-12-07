package interface_adapter.upcoming_shows;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import use_case.upcoming_shows.UpcomingOutputBoundary;
import use_case.upcoming_shows.UpcomingOutputData;

/**
 * The UpcomingPresenter class is responsible for presenting the results of the upcoming shows functionality
 * and transitioning the view to the show concerts view in case of success.
 */
public class UpcomingPresenter implements UpcomingOutputBoundary {

    /**
     * The UpcomingViewModel associated with this presenter for managing the upcoming shows view.
     */
    private final UpcomingViewModel upcomingViewModel;

    /**
     * The ShowConcertsViewModel associated with this presenter for managing the show concerts view.
     */
    private final ShowConcertsViewModel showConcertsViewModel;

    /**
     * The ViewManagerModel for handling view transitions.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new UpcomingPresenter with the given ViewManagerModel and view models for upcoming and show concerts.
     *
     * @param viewManagerModel       The ViewManagerModel for handling view transitions.
     * @param showConcertsViewModel  The ShowConcertsViewModel associated with this presenter.
     * @param upcomingViewModel      The UpcomingViewModel associated with this presenter.
     */
    public UpcomingPresenter(ViewManagerModel viewManagerModel,
                             ShowConcertsViewModel showConcertsViewModel,
                             UpcomingViewModel upcomingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.showConcertsViewModel = showConcertsViewModel;
        this.upcomingViewModel = upcomingViewModel;
    }

    /**
     * Prepares the view for success by updating the show concerts view and transitioning to it.
     *
     * @param response The output data containing upcoming concerts information.
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
     * Prepares the view for failure by updating the upcoming shows view with the error information.
     *
     * @param error The error message indicating the cause of failure.
     */
    @Override
    public void prepareFailView(String error) {
        UpcomingState upcomingState = upcomingViewModel.getState();
        upcomingState.setPostalCodeError(error);
        upcomingState.setUpcomingShowsError(error);
        upcomingViewModel.firePropertyChanged();
    }
}
