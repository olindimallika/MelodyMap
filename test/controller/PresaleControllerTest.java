package controller;//package controller;

import interface_adapter.presale.PresaleController;
import org.junit.jupiter.api.Test;
import use_case.presale_date.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PresaleControllerTest {
    @Test
    void successTest() throws IOException {
        // creating the input boundary object
        String postalCode = "M1E4N3";
        String favArtsts = "Laufey";
        PresaleInputBoundary successInteractor = new PresaleInputBoundary() {

            @Override
            public void execute(PresaleInputData presaleInputData) {
                assertEquals(postalCode, presaleInputData.getPostalCode());
                assertEquals(favArtsts, presaleInputData.getFavArtists());

            }

        };

        // creating the interactor
        PresaleController controller = new PresaleController(successInteractor);

        // invoking the controller by passing in the postal code
        controller.execute(postalCode, favArtsts);
    }
}