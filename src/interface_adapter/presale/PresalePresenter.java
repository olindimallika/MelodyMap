package interface_adapter.presale;

import interface_adapter.ViewManagerModel;
import use_case.presale_date.PresaleOutputBoundary;
import use_case.presale_date.PresaleOutputData;

/**
 * The {@code PresalePresenter} class is responsible for presenting the output of the presale use case to the view layer.
 * It translates the presale output data into a format suitable for the view model and updates the view manager accordingly.
 */
public class PresalePresenter implements PresaleOutputBoundary {

    /**
     * The view model representing the presale view and its state.
     */
    private final PresaleViewModel presaleViewModel;

    /**
     * The view manager model responsible for managing the views and their transitions.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new instance of {@code PresalePresenter} with the provided view manager model and presale view model.
     *
     * @param viewManagerModel The view manager model.
     * @param presaleViewModel The presale view model.
     */
    public PresalePresenter(ViewManagerModel viewManagerModel, PresaleViewModel presaleViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.presaleViewModel = presaleViewModel;
    }

    /**
     * Prepares and updates the view with the successful output of the presale use case.
     *
     * @param response The output data containing presale information.
     */
    @Override
    public void prepareSuccessView(PresaleOutputData response) {
        // Retrieve the current state of the presale view model
        PresaleState presaleState = presaleViewModel.getState();

        // Set the final format and presale information in the view model state
        presaleState.setFinalFormat(response.getFormatOutputPresale());
        //presaleState.setPresale(response.getPresaleDates());

        // Update the presale view model state and notify observers
        this.presaleViewModel.setState(presaleState);
        this.presaleViewModel.firePropertyChanged();

        // Set the presale view as the active view and notify the view manager
        viewManagerModel.setActiveView(presaleViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares and updates the view with an error message in case of a failed presale use case execution.
     *
     * @param error The error message describing the cause of the failure.
     */
    @Override
    public void prepareFailView(String error) {
        // Retrieve the current state of the presale view model
        PresaleState presaleState = presaleViewModel.getState();

        // Set the error messages in the view model state
        presaleState.setPostalCodeError(error);
        presaleState.setFavouriteArtistPresaleError(error);
        presaleState.setPresaleError(error);

        // Update the presale view model state and notify observers
        presaleViewModel.firePropertyChanged();
    }
}