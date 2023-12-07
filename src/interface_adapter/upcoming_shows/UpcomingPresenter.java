package interface_adapter.upcoming_shows;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import use_case.upcoming_shows.UpcomingOutputBoundary;
import use_case.upcoming_shows.UpcomingOutputData;

public class UpcomingPresenter implements UpcomingOutputBoundary {

    private final UpcomingViewModel upcomingViewModel;
    private final ShowConcertsViewModel showConcertsViewModel;
    private final ViewManagerModel viewManagerModel;

    public UpcomingPresenter(ViewManagerModel viewManagerModel,
                             ShowConcertsViewModel showConcertsViewModel,
                             UpcomingViewModel upcomingViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showConcertsViewModel = showConcertsViewModel;
        this.upcomingViewModel = upcomingViewModel;
    }

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

    @Override
    public void prepareFailView(String error) {
        UpcomingState upcomingState = upcomingViewModel.getState();
        upcomingState.setPostalCodeError(error);
        upcomingState.setUpcomingShowsError(error);
        upcomingViewModel.firePropertyChanged();
    }
}
