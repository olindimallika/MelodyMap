package app;

import data_access.LocationFinder;
import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistController;
import interface_adapter.artist_venue.ArtistPresenter;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import use_case.artist_venue.ArtistVenueDataAccess;
import use_case.artist_venue.ArtistVenueInputBoundary;
import use_case.artist_venue.ArtistVenueInteractor;
import use_case.artist_venue.ArtistVenueOutputBoundary;
import view.ArtistVenueView;

import javax.swing.*;
import javax.xml.stream.Location;
import java.io.IOException;

public class ArtistVenueUseCaseFactory {


    /** Prevent instantiation. */
    private ArtistVenueUseCaseFactory() {}

    public static ArtistVenueView create(
            ViewManagerModel viewManagerModel,
            ArtistViewModel artistViewModel,
            ArtistVenueDataAccess userDataAccessObject, NotifyViewModel notifyViewModel) {

        try {
            ArtistController artistController = createArtistVenueUseCase(viewManagerModel, artistViewModel, userDataAccessObject, notifyViewModel);
            return new ArtistVenueView(artistController, artistViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ArtistController createArtistVenueUseCase(
            ViewManagerModel viewManagerModel,
            ArtistViewModel artistViewModel,
            ArtistVenueDataAccess userDataAccessObject,
            NotifyViewModel notifyViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ArtistVenueOutputBoundary artistVenueOutputBoundary = new ArtistPresenter(viewManagerModel, artistViewModel, notifyViewModel);

        UserFactory userFactory = new UserModelFactory();

        LocationFinder locationFinder = new LocationFinder();

        ArtistVenueInputBoundary artistVenueInteractor = new ArtistVenueInteractor(userDataAccessObject, artistVenueOutputBoundary, userFactory, locationFinder);

        return new ArtistController(artistVenueInteractor);
    }

}





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