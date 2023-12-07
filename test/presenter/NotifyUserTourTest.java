package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyPresenter;
import interface_adapter.notify_user_tour.NotifyViewModel;

import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.NotifyOutputData;

import java.util.LinkedHashMap;


public class NotifyUserTourTest {
    @Test
    void successTest() {

        LinkedHashMap<String, String> artistsOnTour = new LinkedHashMap<>();
        artistsOnTour.put("Laufey", "has a tour");
        artistsOnTour.put("Taylor Swift", "has a tour");
        artistsOnTour.put("Olivia Rodrigo", "has a tour");
        artistsOnTour.put("Niki", "doesn't have a tour");
        artistsOnTour.put("Lizzy Mcalpine", "doesn't have a tour");

        NotifyOutputData notifyOutputData = new NotifyOutputData(artistsOnTour);

        NotifyViewModel notifyViewModel = new NotifyViewModel();

        ArtistViewModel artistViewModel = new ArtistViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // creating the presenter
        NotifyPresenter presenter = new NotifyPresenter(viewManagerModel, artistViewModel, notifyViewModel);

        // invoking the presenter by passing in the output data
        presenter.prepareSuccessView(notifyOutputData);
    }
}
