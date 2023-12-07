package app;


import interface_adapter.ViewManagerModel;

import interface_adapter.show_artist_concerts.ShowArtistController;
import interface_adapter.show_artist_concerts.ShowArtistPresenter;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;

import use_case.show_artist_concerts.ShowArtistDataAcess;
import use_case.show_artist_concerts.ShowArtistInputBoundary;
import use_case.show_artist_concerts.ShowArtistInteractor;
import use_case.show_artist_concerts.ShowArtistOuputBoundary;

import view.ShowArtistView;

import javax.swing.*;
import java.io.IOException;

public class ShowArtistUseCaseFactory {
    private ShowArtistUseCaseFactory() {}

    public static ShowArtistView create(ViewManagerModel viewManagerModel,
                                        ShowArtistViewModel showArtistViewModel,
                                        ShowArtistDataAcess userDataAccessObject) {

        try {
            ShowArtistController showArtistController = createShowArtistUseCase(viewManagerModel,
                    showArtistViewModel, userDataAccessObject);

            return new ShowArtistView(showArtistController, showArtistViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static ShowArtistController createShowArtistUseCase(ViewManagerModel viewManagerModel,
                                                                ShowArtistViewModel showArtistViewModel,
                                                                ShowArtistDataAcess userDataAccessObject) throws IOException {

        ShowArtistOuputBoundary showArtistOuputBoundary = new ShowArtistPresenter(viewManagerModel, showArtistViewModel);
        ShowArtistInputBoundary showArtistInteractor = new ShowArtistInteractor(showArtistOuputBoundary);

        return new ShowArtistController(showArtistInteractor);


    }
}
