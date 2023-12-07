/**
 * The {@code PresalePresenterTest} class contains unit tests for the {@link PresalePresenter} class.
 * These tests cover v successful presentation of presale usecase interactor.
 *
 */
package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.presale.PresalePresenter;
import interface_adapter.presale.PresaleViewModel;
import org.junit.jupiter.api.Test;
import use_case.presale_date.PresaleOutputData;

/**
 * Unit tests for the {@link PresalePresenter} class.
 */
public class PresalePresenterTest {

    /**
     * Tests the successful presentation of presale information by the {@link PresalePresenter}.
     */
    @Test
    void successTest() {
        // Expected output for the test case
        String outputConcerts = "Event URL: https://www.ticketmaster.ca/laufey-bewitched-the-goddess-tour-toronto-ontario-05-01-2024/event/10005F76CA731CA6\n" +
                "Presale Status: Presale is happening now until 2024-04-26T02:00:00Z click the link to go to presale.\n" +
                "\n" +
                "\n";

        // Create PresaleOutputData with the expected output
        PresaleOutputData upcomingOutputData = new PresaleOutputData(outputConcerts);

        // Create PresaleViewModel
        PresaleViewModel presaleViewModel = new PresaleViewModel();

        // Create ViewManagerModel
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Creating the presenter
        PresalePresenter presenter = new PresalePresenter(viewManagerModel, presaleViewModel);

        // Invoking the presenter by passing in the output data
        presenter.prepareSuccessView(upcomingOutputData);
    }
}
