package use_case.show_artist_concerts;

import use_case.show_concerts.ShowConcertsOutputBoundary;

public class ShowArtistInteractor implements ShowArtistInputBoundary{

    // The presenter responsible for preparing and displaying the results to the user
    final ShowArtistOuputBoundary showArtistPresenter;

    /**
     * Constructs a new instance of ShowConcertsInteractor
     *
     * @param showArtistOuputBoundary The presenter for displaying the results to the user.
     */
    public ShowArtistInteractor(ShowArtistOuputBoundary showArtistOuputBoundary){
        this.showArtistPresenter = showArtistOuputBoundary;
    }

    /**
     * Executes the show concerts logic based on the provided input data.
     *
     */
    public void execute(){
        // Instruct the presenter to prepare a success view
        showArtistPresenter.prepareSuccessView();

    }
}
