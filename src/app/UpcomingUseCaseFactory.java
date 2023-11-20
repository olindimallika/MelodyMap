package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.upcoming_shows.UpcomingController;
import interface_adapter.upcoming_shows.UpcomingPresenter;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import use_case.upcoming_shows.UpcomingDataAccess;
import use_case.upcoming_shows.UpcomingInputBoundary;
import use_case.upcoming_shows.UpcomingInteractor;
import use_case.upcoming_shows.UpcomingOutputBoundary;
import view.UpcomingView;

import javax.swing.*;
import java.io.IOException;

public class UpcomingUseCaseFactory {

    /** Prevent instantiation. */
    private UpcomingUseCaseFactory() {}

    public static UpcomingView create(
            ViewManagerModel viewManagerModel, UpcomingViewModel upcomingViewModel, UpcomingDataAccess userDataAccessObject) {

        try {
            UpcomingController upcomingController = createUserUpcomingUseCase(viewManagerModel, upcomingViewModel, userDataAccessObject);
            return new UpcomingView(upcomingController, upcomingViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static UpcomingController createUserUpcomingUseCase(ViewManagerModel viewManagerModel, UpcomingViewModel upcomingViewModel, UpcomingDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        UpcomingOutputBoundary signupOutputBoundary = new UpcomingPresenter(viewManagerModel, upcomingViewModel);

        UserFactory userFactory = new UserModelFactory();

        UpcomingInputBoundary userUpcomingInteractor = new UpcomingInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new UpcomingController(userUpcomingInteractor);
    }

}
