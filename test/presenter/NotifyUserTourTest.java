package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyPresenter;
import interface_adapter.notify_user_tour.NotifyViewModel;

import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.NotifyOutputData;

import java.util.LinkedHashMap;


/**
 * The NotifyUserTourTest class contains JUnit tests for the NotifyPresenter and associated
 * components in the "presenter" package. It focuses on testing the success scenarios of the notification feature.
 *
 * The test case checks the functionality of the NotifyPresenter by verifying that the presenter prepares
 * the success view correctly. It involves creating and initializing necessary view models and a ViewManagerModel
 * instance to simulate the interaction between the presenter and the views.
 *
 * First, create a LinkedHashMap representing artists on tour, where each entry consists of an artist name
 * and a corresponding tour status.
 *
 * Then, create a NotifyOutputData instance using the constructed artist data and create instances of necessary view
 * models and a ViewManagerModel to represent the required components for the notification presentation.
 *
 * Create a NotifyPresenter instance, providing it with the created view models and the view manager model.
 *
 * Finally, invoke the presenter to prepare the success view, passing in the NotifyOutputData
 *
 */
public class NotifyUserTourTest {

    /**
     * Test the success scenario of the NotifyPresenter by creating necessary data and view models,
     * and verifying that the presenter prepares the success view without errors.
     */
    @Test
    void successTest() {

        // creating the expected output hash map from the notify user tour use case
        LinkedHashMap<String, String> artistsOnTour = new LinkedHashMap<>();
        artistsOnTour.put("Laufey", "has a tour");
        artistsOnTour.put("Taylor Swift", "has a tour");
        artistsOnTour.put("Olivia Rodrigo", "has a tour");
        artistsOnTour.put("Niki", "doesn't have a tour");
        artistsOnTour.put("Lizzy Mcalpine", "doesn't have a tour");

        NotifyOutputData notifyOutputData = new NotifyOutputData(artistsOnTour);

        // creating the required view models to pass into the presenter
        NotifyViewModel notifyViewModel = new NotifyViewModel();

        ArtistViewModel artistViewModel = new ArtistViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // creating the presenter
        NotifyPresenter presenter = new NotifyPresenter(viewManagerModel, artistViewModel, notifyViewModel);

        // invoking the presenter by passing in the output data
        presenter.prepareSuccessView(notifyOutputData);
    }
}
