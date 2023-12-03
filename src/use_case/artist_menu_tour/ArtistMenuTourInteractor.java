package use_case.artist_menu_tour;

public class ArtistMenuTourInteractor implements ArtistMenuTourInputBoundary{
    final ArtistMenuTourDataAccess userDataAcessObject;

    final ArtistMenuTourOutputBoundary artistMenuPresenter;

    public ArtistMenuTourInteractor(ArtistMenuTourDataAccess userDataAcessInterface,
                                    ArtistMenuTourOutputBoundary artistMenuOutputBoundary){
        this.userDataAcessObject = userDataAcessInterface;
        this.artistMenuPresenter = artistMenuOutputBoundary;
    }

    @Override
    public void execute(ArtistMenuTourInputData artistMenuTourInputData) {

        ArtistMenuTourOutputData artistMenuTourOutputData = new ArtistMenuTourOutputData();
        artistMenuPresenter.prepareSuccessView(artistMenuTourOutputData);
    }
}
