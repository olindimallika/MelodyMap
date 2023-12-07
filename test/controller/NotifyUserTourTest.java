package controller;

import interface_adapter.notify_user_tour.NotifyController;
import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInputData;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotifyUserTourTest {
    @Test
    void successTest() {
        // creating the input boundary object
        String favouriteArtistName = "Taylor Swift, Lizzy Mcalpine, Laufey, Niki, Nicki Minaj";
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
