// app.ArtistMenuUseCaseFactory
package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_menu_tour.ArtistMenuController;
import interface_adapter.artist_menu_tour.ArtistMenuPresenter;
import interface_adapter.artist_menu_tour.ArtistMenuViewModel;
import use_case.artist_menu_tour.ArtistMenuTourDataAccess;
import use_case.artist_menu_tour.ArtistMenuTourInteractor;
import use_case.artist_menu_tour.ArtistMenuTourOutputBoundary;
import view.ArtistMenuView;

import javax.swing.*;
import java.io.IOException;

public class ArtistMenuUseCaseFactory {

    private ArtistMenuUseCaseFactory() {}

    public static ArtistMenuView create(ViewManagerModel viewManagerModel,
                                        ArtistMenuViewModel artistMenuViewModel,
                                        ArtistMenuTourDataAccess dataAccess) {
        try {
            ArtistMenuController artistMenuController = createArtistMenuUseCase(
                    viewManagerModel, artistMenuViewModel, dataAccess);

            return new ArtistMenuView(artistMenuController, artistMenuViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ArtistMenuController createArtistMenuUseCase(ViewManagerModel viewManagerModel,
                                                                ArtistMenuViewModel artistMenuViewModel,
                                                                ArtistMenuTourDataAccess dataAccess)
            throws IOException {
        ArtistMenuTourOutputBoundary artistMenuTourOutputBoundary =
                new ArtistMenuPresenter(viewManagerModel, artistMenuViewModel);

        ArtistMenuTourInteractor artistMenuTourInteractor =
                new ArtistMenuTourInteractor(dataAccess, artistMenuTourOutputBoundary);

        return new ArtistMenuController(artistMenuTourInteractor);
    }
}
