package interface_adapter.artist_menu_tour;

import use_case.artist_menu_tour.ArtistMenuTourInputBoundary;
import use_case.artist_menu_tour.ArtistMenuTourInputData;
import use_case.show_concerts.ShowConcertsInputBoundary;
import use_case.show_concerts.ShowConcertsInputData;

public class ArtistMenuController {
    final ArtistMenuTourInputBoundary artistMenuUseCaseInteractor;

    public ArtistMenuController(ArtistMenuTourInputBoundary artistMenuUseCaseInteractor){
        this.artistMenuUseCaseInteractor = artistMenuUseCaseInteractor;
    }

    public void execute(){
        ArtistMenuTourInputData artistMenuInputData = new ArtistMenuTourInputData();
        artistMenuUseCaseInteractor.execute(artistMenuInputData);
    }
}
