package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyController;
import interface_adapter.notify_user_tour.NotifyPresenter;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInteractor;
import use_case.notify_user_tour.NotifyOutputBoundary;
import view.ShowConcertsView;

import javax.swing.*;
import java.io.IOException;

public class ShowConcertsUseCaseFactory {


    /** Prevent instantiation. */
    private ShowConcertsUseCaseFactory() {}

    public static ShowConcertsView create(
            ViewManagerModel viewManagerModel, NotifyViewModel notifyViewModel, NotifyDataAccess notifyDataAccess, ShowConcertsViewModel showConcertsViewModel) {

        try {
            NotifyController notifyController = createUserShowConcertsUseCase(viewManagerModel, notifyViewModel, notifyDataAccess);
            return new ShowConcertsView(showConcertsViewModel, notifyController, notifyViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NotifyController createUserShowConcertsUseCase(ViewManagerModel viewManagerModel, NotifyViewModel notifyViewModel, NotifyDataAccess notifyDataAccess) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        NotifyOutputBoundary notifyOutputBoundary = new NotifyPresenter(viewManagerModel, notifyViewModel);

        UserFactory userFactory = new UserModelFactory();

        NotifyInputBoundary userNotifyInteractor = new NotifyInteractor(
                notifyDataAccess, notifyOutputBoundary, userFactory);

        return new NotifyController(userNotifyInteractor);
    }

}
