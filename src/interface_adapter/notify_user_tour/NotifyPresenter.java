package interface_adapter.notify_user_tour;

import interface_adapter.ViewManagerModel;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.notify_user_tour.NotifyOutputData;

public class NotifyPresenter implements NotifyOutputBoundary {
    private final NotifyViewModel notifyViewModel;
    private final ViewManagerModel viewManagerModel;

    public NotifyPresenter(ViewManagerModel viewManagerModel, NotifyViewModel notifyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.notifyViewModel = notifyViewModel;
    }

    @Override
    public void prepareSuccessView(NotifyOutputData response){
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setFavouriteArtistUpcoming(response.getFavouriteArtistConcerts());
        notifyState.setConcertLink(response.getConcertLink());
        this.notifyViewModel.setState(notifyState);
        this.notifyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(notifyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        NotifyState notifyState = notifyViewModel.getState();
        notifyState.setFavouriteArtistUpcomingError(error);
        notifyState.setConcertLinkError(error);
        notifyViewModel.firePropertyChanged();
    }
}
