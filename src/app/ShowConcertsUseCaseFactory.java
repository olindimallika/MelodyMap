package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyController;
import interface_adapter.notify_user_tour.NotifyPresenter;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsController;
import interface_adapter.show_concerts.ShowConcertsPresenter;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInteractor;
import use_case.notify_user_tour.NotifyOutputBoundary;
import use_case.show_concerts.ShowConcertsDataAccess;
import use_case.show_concerts.ShowConcertsInputBoundary;
import use_case.show_concerts.ShowConcertsInteractor;
import use_case.show_concerts.ShowConcertsOutputBoundary;
import view.ShowConcertsView;

import javax.swing.*;
import java.io.IOException;

public class ShowConcertsUseCaseFactory {


    /** Prevent instantiation. */
    private ShowConcertsUseCaseFactory() {}

    public static ShowConcertsView create(
            ViewManagerModel viewManagerModel,
            ShowConcertsViewModel showConcertsViewModel,
            NotifyViewModel notifyViewModel,
            ShowConcertsDataAccess userDataAccessObject
    ) {

        try {
            ShowConcertsController showConcertsController = createShowConcertsUseCase(viewManagerModel, showConcertsViewModel, notifyViewModel, userDataAccessObject);
            return new ShowConcertsView(showConcertsController, showConcertsViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ShowConcertsController createShowConcertsUseCase(
            ViewManagerModel viewManagerModel,
            ShowConcertsViewModel showConcertsViewModel,
            NotifyViewModel notifyViewModel,
            ShowConcertsDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ShowConcertsOutputBoundary showConcertsOutputBoundary = new ShowConcertsPresenter(viewManagerModel, showConcertsViewModel, notifyViewModel);

        ShowConcertsInputBoundary showConcertsInteractor = new ShowConcertsInteractor(showConcertsOutputBoundary);

        return new ShowConcertsController(showConcertsInteractor);
    }

}