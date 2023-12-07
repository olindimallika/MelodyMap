package interface_adapter.show_artist_concerts;

import use_case.show_artist_concerts.ShowArtistInputBoundary;

public class ShowArtistController {

    final ShowArtistInputBoundary showArtistUseCaseInteractor;

    public ShowArtistController(ShowArtistInputBoundary showArtistUseCaseInteractor) {
        this.showArtistUseCaseInteractor = showArtistUseCaseInteractor;
    }

    public void execute() {
        showArtistUseCaseInteractor.execute();
    }

}
