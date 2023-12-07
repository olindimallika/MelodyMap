package controller;

import interface_adapter.notify_user_tour.NotifyController;
import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInputData;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The NotifyUserTourTest class contains JUnit tests for the NotifyController and associated
 * components in the "notify_user_tour" package in interface_adapter.
 * It focuses on testing the success scenario of the notification feature.
 *
 * The test case checks the functionality of the NotifyController by verifying that the input boundary
 * object is correctly invoked and that the expected data is passed to it. The test specifically examines the
 * successful notification scenario when the user's favorite artists are provided to the controller.
 *
 *  First, an input boundary object is created to simulate the interaction with the
 *  notification use case. Define an object as an anonymous class implementing the interface, where the
 *  execute method verifies that the provided favorite artist names match the expected ones.
 *
 *  Then, create a NotifyController instance with the previously defined success input boundary.
 *  The controller can then be invoked by passing in a string containing the favorite artist names.
 *
 *  Finally, assert that the input boundary's execute method was called with the correct data.
 *
 */
public class NotifyUserTourTest {

    /**
     * Test the success scenario of the NotifyController by providing a set of favorite artist names
     * and ensuring that the input boundary's execute method is called with the correct data.
     */
    @Test
    void successTest() {

        String favouriteArtistName = "Taylor Swift, Lizzy Mcalpine, Laufey, Niki, Nicki Minaj";

        // creating the input boundary object
        NotifyInputBoundary successInteractor = new NotifyInputBoundary() {

            @Override
            public void execute(NotifyInputData notifyInputData) {
                assertEquals(favouriteArtistName, notifyInputData.getFavouriteArtistNames());

            }

        };

        // creating the controller
        NotifyController controller = new NotifyController(successInteractor);

        // invoking the controller by passing in the favourite Artists
        controller.execute(favouriteArtistName);
    }
}
