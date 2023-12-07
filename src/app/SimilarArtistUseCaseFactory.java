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

/**
 * The `SimilarArtistUseCaseFactory` class is responsible for creating and assembling the components necessary for the Similar Artist use case.
 * It acts as a factory, instantiating and configuring the appropriate objects required to set up the Similar Artist functionality within the application.
 *
 * The class provides a static method, create, which takes the necessary parameters to initialize the Similar Artist feature,
 * such as the view manager model, the view model for similar artists, and the data access object for similar artist information.
 * It returns a SimilarView instance, representing the graphical user interface for the Similar Artist use case.
 *
 * The private method, createUserUpcomingUseCase, is responsible for creating the core components of the Similar Artist feature.
 * It initializes the presenter, interactor, and controller, ensuring proper communication between the user interface and the underlying use case logic.
 *
 * This class follows the Factory design pattern, encapsulating the complex object creation process and providing a simplified interface
 * for creating instances of the Similar Artist feature. It handles potential exceptions, such as IOException, by displaying an error message
 * to the user through a JOptionPane and wrapping other exceptions in a RuntimeException for proper handling.
 *
 * The Similar Artist functionality assumes a specific architecture and design pattern within the larger application,
 * where the components (controller, presenter, interactor) adhere to certain interfaces (SimilarInputBoundary, SimilarOutputBoundary).
 * Modifications to this class should be done with consideration for the overall application architecture and design.
 *
 * @author Kelsang Tsomo
 */
public class SimilarArtistUseCaseFactory {

    /**
     * Private constructor to prevent instantiation. This class should be used as a static factory.
     */
    private SimilarArtistUseCaseFactory() {
    }

    /**
     * Creates and initializes the components required for the Similar Artist use case, including the view, controller, presenter, and interactor.
     *
     * @param viewManagerModel       The view manager model responsible for managing the overall user interface.
     * @param similarArtistViewModel The view model representing the state and behavior of the Similar Artist user interface.
     * @param userDataAccessObject   The data access object providing access to similar artist information.
     * @return The initialized SimilarView representing the graphical user interface for the Similar Artist use case.
     */
    public static SimilarView create(
            ViewManagerModel viewManagerModel,
            SimilarArtistViewModel similarArtistViewModel,
             SimilarDataAccess userDataAccessObject) {

        try {
            // Create and configure the Similar Artist controller by calling the private helper method.
            SimilarArtistController similarArtistController = createUserUpcomingUseCase(viewManagerModel, similarArtistViewModel, userDataAccessObject);

            // Return a new instance of SimilarView with the configured controller and view model.
            return new SimilarView(similarArtistController, similarArtistViewModel);
        } catch (IOException e) {

            // Display an error message if an IOException occurs during the process.
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Return null in case of an exception, indicating a failure in creating the SimilarView.
        return null;
    }

    /**
     * Creates the core components of the Similar Artist use case, including the presenter, interactor, and controller.
     *
     * @param viewManagerModel       The view manager model responsible for managing the overall user interface.
     * @param similarArtistViewModel The view model representing the state and behavior of the Similar Artist user interface.
     * @param userDataAccessObject   The data access object providing access to similar artist information.
     * @return The initialized SimilarArtistController representing the controller for the Similar Artist use case.
     * @throws IOException If an I/O exception occurs during the creation process.
     */
    private static SimilarArtistController createUserUpcomingUseCase(ViewManagerModel viewManagerModel,
                                                                     SimilarArtistViewModel similarArtistViewModel,
                                                                     SimilarDataAccess userDataAccessObject) throws IOException {
        // Create the output boundary (presenter) for the Similar Artist use case.
        SimilarOutputBoundary similarOutputBoundary = new SimilarArtistPresenter(viewManagerModel, similarArtistViewModel);

        // Create the user factory responsible for creating user-related entities.
        UserFactory userFactory = new UserModelFactory();

        // Create the input boundary (interactor) for the Similar Artist use case.
        SimilarInputBoundary userSimilarInteractor = new SimilarInteractor(
                userDataAccessObject, similarOutputBoundary, userFactory);

        // Return a new instance of SimilarArtistController with the configured interactor.
        return new SimilarArtistController(userSimilarInteractor);
    }
}
