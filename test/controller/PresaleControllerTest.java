/**
 * The {@code PresaleControllerTest} class contains unit tests for the {@link PresaleController} class.
 * These tests cover various scenarios, including successful execution.
 *
 * <p><strong>Note:</strong> This class uses JUnit 5 for testing.
 */
package controller;

import interface_adapter.presale.PresaleController;
import org.junit.jupiter.api.Test;
import use_case.presale_date.PresaleInputBoundary;
import use_case.presale_date.PresaleInputData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PresaleController} class.
 */
public class PresaleControllerTest {

    /**
     * Tests the successful execution of the {@link PresaleController}.
     *
     * @throws IOException if an I/O exception occurs during the test.
     */
    @Test
    void successTest() throws IOException {
        // creating the input boundary object
        String postalCode = "M5V3L9";
        String favArtsts = "Laufey";
        PresaleInputBoundary successInteractor = new PresaleInputBoundary() {

            /**
             * Executes the interactor and asserts that the input data matches the expected values.
             *
             * @param presaleInputData The input data for the presale operation.
             */
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
