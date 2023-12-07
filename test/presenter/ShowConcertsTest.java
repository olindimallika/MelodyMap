package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsPresenter;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import org.junit.jupiter.api.Test;


/**
 * The ShowConcertsTest class contains JUnit tests for the ShowConcertsPresenter and associated
 * components in the application. It focuses on testing the success scenarios of the concert presentation feature.
 *
 * The test case checks the functionality of the ShowConcertsPresenter by verifying that the presenter
 * prepares the success view correctly. It involves creating and initializing necessary view models and a
 * ViewManagerModel instance to simulate the interaction between the presenter and the views.
 *
 * First, create an instance of the necessary view models and a ViewManagerModel to represent the required
 * components for concert presentation.
 *
 * Then, create a ShowConcertsPresenter instance, providing it with the created view models and
 * the view manager model.
 *
 * Finally, invoke the presenter to prepare the success view.
 *
 */
public class ShowConcertsTest {

    /**
     * Test the success scenario of the ShowConcertsPresenter by creating necessary view models and
     * a view manager model, and verifying that the presenter prepares the success view without errors.
     */
    @Test
    void successTest() {

        // creating the required view models to pass into the presenter
        ShowConcertsViewModel showConcertsViewModel = new ShowConcertsViewModel();

        NotifyViewModel notifyViewModel = new NotifyViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // creating the presenter
        ShowConcertsPresenter presenter = new ShowConcertsPresenter(viewManagerModel, showConcertsViewModel, notifyViewModel);

        // invoking the presenter
        presenter.prepareSuccessView();
    }
}
