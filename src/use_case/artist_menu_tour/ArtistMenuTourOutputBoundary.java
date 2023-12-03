package use_case.artist_menu_tour;

public interface ArtistMenuTourOutputBoundary {

    void prepareSuccessView(ArtistMenuTourOutputData user);

    void prepareFailView(String error);

}
