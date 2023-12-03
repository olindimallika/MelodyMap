package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.similar_artist.SimilarArtistController;
import interface_adapter.similar_artist.SimilarArtistPresenter;
import interface_adapter.similar_artist.SimilarArtistViewModel;
import use_case.similar_artist_venue.SimilarDataAccess;
import use_case.similar_artist_venue.SimilarInputBoundary;
import use_case.similar_artist_venue.SimilarInteractor;
import use_case.similar_artist_venue.SimilarOutputBoundary;
import view.SimilarView;

import javax.swing.*;
import java.io.IOException;

public class SimilarArtistUseCaseFactory {
    private SimilarArtistUseCaseFactory() {
    }

    public static SimilarView create(
            ViewManagerModel viewManagerModel,
            SimilarArtistViewModel similarArtistViewModel,
             SimilarDataAccess userDataAccessObject) {

        try {
            SimilarArtistController similarArtistController = createUserUpcomingUseCase(viewManagerModel, similarArtistViewModel, userDataAccessObject);
            return new SimilarView(similarArtistController, similarArtistViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SimilarArtistController createUserUpcomingUseCase(ViewManagerModel viewManagerModel,
                                                                     SimilarArtistViewModel similarArtistViewModel,
                                                                     SimilarDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SimilarOutputBoundary similarOutputBoundary = new SimilarArtistPresenter(viewManagerModel, similarArtistViewModel);

        UserFactory userFactory = new UserModelFactory();

        SimilarInputBoundary userSimilarInteractor = new SimilarInteractor(
                userDataAccessObject, similarOutputBoundary, userFactory);

        return new SimilarArtistController(userSimilarInteractor);
    }
}
