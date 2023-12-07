package interface_adapter.show_concerts;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyState;
import interface_adapter.notify_user_tour.NotifyViewModel;
import use_case.show_concerts.ShowConcertsOutputBoundary;
import use_case.show_concerts.ShowConcertsOutputData;

/**
 * The ShowConcertsPresenter class is responsible for presenting the results of the show concerts use case to the user interface.
 * It implements the ShowConcertsOutputBoundary interface, defining methods to prepare views for success and failure scenarios.
 *
 * This presenter collaborates with the user interface components, specifically the ShowConcertsViewModel and
 * NotifyViewModel, to update the state and trigger property changes for proper UI updates.
 *
 * The prepareSuccessView method is called when the show concerts use case execution is successful. It updates the state
 * of the NotifyViewModel and switches the active view to the notify view, notifying the ViewManagerModel of
 * the changes for proper UI rendering.
 *
 * The prepareFailView method is invoked in case of a failure during the show concerts use case. It updates the state of
 * the ShowConcertsViewModel with the provided error message and triggers property changes to update the UI accordingly.
 *
 * It is essential to note that this class assumes a specific user interface architecture and interaction flow, where the success view
 * involves switching to the notify view. The actual implementation details may vary based on the application's design.
 *
 * Author: Bea Castro
 */

public class ShowConcertsPresenter implements ShowConcertsOutputBoundary {

    // The view model for the show concerts feature
    private final ShowConcertsViewModel showConcertsViewModel;

    // The view model for the notify feature
    private final NotifyViewModel notifyViewModel;

    // The view manager model for managing the active view in the user interface
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new instance of <code>ShowConcertsPresenter</code>.
     *
     * @param viewManagerModel The view manager model for managing the active view in the user interface.
     * @param showConcertsViewModel The view model for the show concerts feature.
     * @param notifyViewModel The view model for the notify feature.
     */
    public ShowConcertsPresenter(ViewManagerModel viewManagerModel, ShowConcertsViewModel showConcertsViewModel, NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showConcertsViewModel = showConcertsViewModel;
        this.notifyViewModel = notifyViewModel;
    }

    /**
     * Prepares the success view for the show concerts use case by updating the state of the notify view model,
     * switching to the notify view, and notifying the view manager model of the changes.
     *
     * @param response The output data containing information to be presented in the success view.
     */
    @Override
    public void prepareSuccessView(ShowConcertsOutputData response) {
        // On success, we want to switch to the notify view
        NotifyState notifyState = notifyViewModel.getState();
        this.notifyViewModel.setState(notifyState);
        this.notifyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(notifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view for the show concerts use case by updating the state of the show concerts view model
     * with the provided error message and triggering property changes for UI updates.
     *
     * @param error The error message describing the reason for the failure.
     */
    @Override
    public void prepareFailView(String error) {
        ShowConcertsState showConcertsState = showConcertsViewModel.getState();
        showConcertsState.setConcertsError(error);
        showConcertsViewModel.firePropertyChanged();
    }
}
