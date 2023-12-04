package app;

import entity.Artist;
import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistController;
import interface_adapter.artist_venue.ArtistPresenter;
import interface_adapter.artist_venue.ArtistViewModel;
import use_case.artist_venue.ArtistVenueDataAccess;
import use_case.artist_venue.ArtistVenueInputBoundary;
import use_case.artist_venue.ArtistVenueInteractor;
import use_case.artist_venue.ArtistVenueOutputBoundary;
import view.ArtistVenueView;
import view.ShowConcertsView;

import javax.swing.*;
import java.io.IOException;

public class ArtistVenueUseCaseFactory {


    /** Prevent instantiation. */
    private ArtistVenueUseCaseFactory() {}

    public static ArtistVenueView create(
            ViewManagerModel viewManagerModel,
            ArtistViewModel artistViewModel,
            ArtistVenueDataAccess userDataAccessObject
    ) {

        try {
            ArtistController artistController = createArtistVenueUseCase(viewManagerModel, artistViewModel, userDataAccessObject);
            return new ArtistVenueView(artistController, artistViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ArtistController createArtistVenueUseCase(
            ViewManagerModel viewManagerModel,
            ArtistViewModel artistViewModel,
            ArtistVenueDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ArtistVenueOutputBoundary artistVenueOutputBoundary = new ArtistPresenter(viewManagerModel, artistViewModel);

        UserFactory userFactory = new UserModelFactory();

        ArtistVenueInputBoundary artistVenueInteractor = new ArtistVenueInteractor(userDataAccessObject, artistVenueOutputBoundary, userFactory);

        return new ArtistController(artistVenueInteractor);
    }

}


//public class ArtistVenueUseCaseFactory {
//
//    private ArtistVenueUseCaseFactory() {
//
//    }
//    public static ArtistVenueView create(ViewManagerModel viewManagerModel, ArtistViewModel artistViewModel, ArtistVenueDataAccess
//            userDataAcessObject) {
//        try {
//        ArtistController artistController = createArtistVenueUseCase(viewManagerModel, artistViewModel, userDataAcessObject);
//        return new ArtistVenueView(artistController, artistViewModel);
//    }
//
//    private static ArtistController createArtistVenueUseCase(ViewManagerModel viewManagerModel,
//                                                             ArtistViewModel artistViewModel,
//                                                             ArtistVenueDataAccess userDataAccessObject) {
//
//        }
//
//    }
        
    
    // private ShowConcertsUseCaseFactory() {}
    //
    //    public static ShowConcertsView create(
    //            ViewManagerModel viewManagerModel,
    //            ShowConcertsViewModel showConcertsViewModel,
    //            NotifyViewModel notifyViewModel,
    //            ShowConcertsDataAccess userDataAccessObject
    //            ) {
    //
    //        try {
    //            ShowConcertsController showConcertsController = createShowConcertsUseCase(viewManagerModel, showConcertsViewModel, notifyViewModel, userDataAccessObject);
    //            return new ShowConcertsView(showConcertsController, showConcertsViewModel);
    //        } catch (IOException e) {
    //            JOptionPane.showMessageDialog(null, "Could not open user data file.");
    //        }
    //
    //        return null;
    //    }
    //
    //    private static ShowConcertsController createShowConcertsUseCase(
    //            ViewManagerModel viewManagerModel,
    //            ShowConcertsViewModel showConcertsViewModel,
    //            NotifyViewModel notifyViewModel,
    //            ShowConcertsDataAccess userDataAccessObject) throws IOException {
    //
    //        // Notice how we pass this method's parameters to the Presenter.
    //        ShowConcertsOutputBoundary showConcertsOutputBoundary = new ShowConcertsPresenter(viewManagerModel, showConcertsViewModel, notifyViewModel);
    //
    //        ShowConcertsInputBoundary showConcertsInteractor = new ShowConcertsInteractor(
    //                userDataAccessObject, showConcertsOutputBoundary);
    //
    //        return new ShowConcertsController(showConcertsInteractor);
    //    }


//    private static ArtistController createArtistVenueUseCase(ViewManagerModel viewManagerModel, ArtistViewModel artistViewModel, ArtistVenueDataAccess userDataAcessObject) {
//    }
