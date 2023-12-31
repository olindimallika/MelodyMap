package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyController;
import interface_adapter.notify_user_tour.NotifyPresenter;
import interface_adapter.notify_user_tour.NotifyViewModel;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInteractor;
import use_case.notify_user_tour.NotifyOutputBoundary;
import view.NotifyView;

import javax.swing.*;
import java.io.IOException;

public class NotifyUseCaseFactory {
    /** Prevent instantiation. */
    private NotifyUseCaseFactory() {}

    public static NotifyView create(
            ViewManagerModel viewManagerModel,
            NotifyViewModel notifyViewModel,
            ArtistViewModel artistViewModel,
            NotifyDataAccess userDataAccessObject) {

        try {
            NotifyController notifyController = createUserNotifyUseCase(viewManagerModel, notifyViewModel, artistViewModel, userDataAccessObject);
            return new NotifyView(notifyController, notifyViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NotifyController createUserNotifyUseCase(ViewManagerModel viewManagerModel,
                                                            NotifyViewModel notifyViewModel,
                                                            ArtistViewModel artistViewModel,
                                                            NotifyDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        NotifyOutputBoundary notifyOutputBoundary = new NotifyPresenter(viewManagerModel, artistViewModel, notifyViewModel);

        UserFactory userFactory = new UserModelFactory();

        NotifyInputBoundary userNotifyInteractor = new NotifyInteractor(
                userDataAccessObject, notifyOutputBoundary, userFactory);

        return new NotifyController(userNotifyInteractor);
    }

}