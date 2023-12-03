package interface_adapter.show_concerts;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyState;
import interface_adapter.notify_user_tour.NotifyViewModel;
import use_case.show_concerts.ShowConcertsOutputBoundary;
import use_case.show_concerts.ShowConcertsOutputData;

public class ShowConcertsPresenter implements ShowConcertsOutputBoundary {
    private final ShowConcertsViewModel showConcertsViewModel;
    private final NotifyViewModel notifyViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowConcertsPresenter(ViewManagerModel viewManagerModel, ShowConcertsViewModel showConcertsViewModel, NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showConcertsViewModel = showConcertsViewModel;
        this.notifyViewModel = notifyViewModel;
    }

    @Override
    public void prepareSuccessView(ShowConcertsOutputData response) {
        // On success, we want to switch to the notify view

        NotifyState notifyState = notifyViewModel.getState();
        this.notifyViewModel.setState(notifyState);
        this.notifyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(notifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ShowConcertsState showConcertsState = showConcertsViewModel.getState();
        showConcertsState.setConcertsError(error);
        showConcertsViewModel.firePropertyChanged();
    }
}
