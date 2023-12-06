package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsPresenter;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import org.junit.jupiter.api.Test;


public class ShowConcertsTest {
    @Test
    void successTest() {

        ShowConcertsViewModel showConcertsViewModel = new ShowConcertsViewModel();

        NotifyViewModel notifyViewModel = new NotifyViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // creating the presenter
        ShowConcertsPresenter presenter = new ShowConcertsPresenter(viewManagerModel, showConcertsViewModel, notifyViewModel);

        // invoking the presenter
        presenter.prepareSuccessView();
    }
}
