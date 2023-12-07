package interface_adapter.show_artist_concerts;

import use_case.show_artist_concerts.ShowArtistInputBoundary;

/**
 * The `ShowArtistController` class serves as the controller for managing interactions
 * related to displaying information about an artist's concerts. It delegates requests to
 * the corresponding use case interactor.
 */
public class ShowArtistController {

    /**
     * The use case interactor responsible for handling the display of artist concerts.
     */
    final ShowArtistInputBoundary showArtistUseCaseInteractor;

    /**
     * Constructs a `ShowArtistController` with the specified use case interactor.
     *
     * @param showArtistUseCaseInteractor The use case interactor to be associated with this controller.
     */
    public ShowArtistController(ShowArtistInputBoundary showArtistUseCaseInteractor) {
        this.showArtistUseCaseInteractor = showArtistUseCaseInteractor;
    }

    /**
     * Executes the action of displaying information about an artist's concerts by
     * delegating the request to the associated use case interactor.
     */
    public void execute() {
        showArtistUseCaseInteractor.execute();
    }
}
