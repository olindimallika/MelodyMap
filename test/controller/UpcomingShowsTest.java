package controller;

import interface_adapter.upcoming_shows.UpcomingController;
import org.junit.jupiter.api.Test;
import use_case.upcoming_shows.*;

import static org.junit.jupiter.api.Assertions.*;

public class UpcomingShowsTest {
    @Test
    void successTest() {
        // creating the input boundary object
        String postalCode = "M1J3J9";
        UpcomingInputBoundary successInteractor = new UpcomingInputBoundary() {

            @Override
            public void execute(UpcomingInputData upcomingInputData) {
                assertEquals(postalCode, upcomingInputData.getPostalCode());

            }

        };

        // creating the interactor
        UpcomingController controller = new UpcomingController(successInteractor);

        // invoking the controller by passing in the postal code
        controller.execute(postalCode);
    }
}
