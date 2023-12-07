package use_case.show_artist_concerts;

public interface ShowArtistOuputBoundary {

    void prepareSuccessView();

    void prepareFailView(String error);
}
