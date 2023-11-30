package interface_adapter.upcoming_shows;

import interface_adapter.ViewManagerModel;
import use_case.upcoming_shows.UpcomingOutputBoundary;
import use_case.upcoming_shows.UpcomingOutputData;

public class UpcomingPresenter implements UpcomingOutputBoundary {

    private final UpcomingViewModel upcomingViewModel;
    private final ViewManagerModel viewManagerModel;

    public UpcomingPresenter(ViewManagerModel viewManagerModel, UpcomingViewModel upcomingViewModel){
        this.viewManagerModel = viewManagerModel;
        this.upcomingViewModel = upcomingViewModel;
    }

    @Override
    public void prepareSuccessView(UpcomingOutputData response) {
        // On success, display a message box in upcoming view

        UpcomingState upcomingState = upcomingViewModel.getState();
        upcomingState.setUpcomingShows(response.getUpcomingConcerts());
        this.upcomingViewModel.setState(upcomingState);
        this.upcomingViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(upcomingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UpcomingState upcomingState = upcomingViewModel.getState();
        upcomingState.setPostalCodeError(error);
        upcomingState.setUpcomingShowsError(error);
        upcomingViewModel.firePropertyChanged();
    }
}
