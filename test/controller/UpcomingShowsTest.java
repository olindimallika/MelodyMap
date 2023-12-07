package controller;

import interface_adapter.upcoming_shows.UpcomingController;
import org.junit.jupiter.api.Test;
import use_case.upcoming_shows.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The UpcomingShowsTest class contains JUnit tests for the UpcomingController and associated
 * components in the "controller" package. It focuses on testing the success scenarios of the upcoming shows feature.
 *
 * The test case checks the functionality of the UpcomingController by verifying that the controller
 * correctly interacts with the input boundary object and triggers the upcoming shows use case successfully.
 * The test creates an input boundary object, a success input interactor, and a controller to simulate a successful
 * execution of the upcoming shows use case.
 *
 * First create an input boundary object (UpcomingInputBoundary) to simulate the interaction with the
 * upcoming shows use case. The object is defined as an anonymous class implementing the interface, where the
 * execute method verifies that the provided postal code matches the expected one.
 *
 * Then, create an UpcomingController instance with the previously defined success input boundary.
 *
 * Finally, invoke the controller by passing in the postal code.
 *
 */
public class UpcomingShowsTest {

    /**
     * Test the success scenario of the UpcomingController by creating necessary input boundary
     * and verifying that the controller executes the upcoming shows use case with the correct postal code.
     */
    @Test
    void successTest() {

        String postalCode = "M1J3J9";

        // creating the input boundary object
        UpcomingInputBoundary successInteractor = new UpcomingInputBoundary() {

            @Override
            public void execute(UpcomingInputData upcomingInputData) {
                assertEquals(postalCode, upcomingInputData.getPostalCode());

            }

        };

        // creating the controller
        UpcomingController controller = new UpcomingController(successInteractor);

        // invoking the controller by passing in the postal code
        controller.execute(postalCode);
    }
}
