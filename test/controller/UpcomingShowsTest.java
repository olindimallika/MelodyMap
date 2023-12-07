package controller;

import interface_adapter.upcoming_shows.UpcomingController;
import org.junit.jupiter.api.Test;
import use_case.upcoming_shows.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code UpcomingShowsTest} class contains unit tests for the successful execution
 * of the upcoming shows use case within the controller layer.
 */
public class UpcomingShowsTest {

    /**
     * Test case for a successful execution of the upcoming shows use case.
     */
    @Test
    void successTest() {
        // Creating the input boundary object
        String postalCode = "M1J3J9";
        UpcomingInputBoundary successInteractor = new UpcomingInputBoundary() {

            @Override
            public void execute(UpcomingInputData upcomingInputData) {
                // Checking if the provided postal code matches the one passed to the interactor
                assertEquals(postalCode, upcomingInputData.getPostalCode());
            }
        };

        // Creating the upcoming controller with the success interactor
        UpcomingController controller = new UpcomingController(successInteractor);

        // Invoking the controller by passing in the postal code
        controller.execute(postalCode);
    }
}
