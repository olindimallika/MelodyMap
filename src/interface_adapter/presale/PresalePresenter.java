package interface_adapter.presale;

import interface_adapter.ViewManagerModel;
import use_case.presale_date.PresaleOutputBoundary;
import use_case.presale_date.PresaleOutputData;

public class PresalePresenter implements PresaleOutputBoundary {
    private final PresaleViewModel presaleViewModel;
    private final ViewManagerModel viewManagerModel;



    public PresalePresenter(ViewManagerModel viewManagerModel,PresaleViewModel presaleViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.presaleViewModel = presaleViewModel;

    }


    @Override
    public void prepareSuccessView(PresaleOutputData response) {
        PresaleState presaleState = presaleViewModel.getState();
        presaleState.setPresale(response.getPresaleDates());
        this.presaleViewModel.setState(presaleState);
        this.presaleViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(presaleViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        PresaleState presaleState = presaleViewModel.getState();
        presaleState.setPostalCodeError(error);
        presaleState.setFavouriteArtistPresaleError(error);
        presaleState.setPresaleError(error);
        presaleViewModel.firePropertyChanged();

    }
}
