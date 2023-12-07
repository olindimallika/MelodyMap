/**
 * The {@code PresaleTest} class contains unit tests for the {@link PresaleInteractor} class.
 * These tests cover various scenarios, including successful execution.
 *
 * <p><strong>Note:</strong> This class uses JUnit 5 for testing.
 */
package interactor;

import data_access.ArtistStrategy;
import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import use_case.EventStrategy;
import use_case.presale_date.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PresaleInteractor} class.
 */
public class PresaleTest {

    /**
     * Tests the successful execution of the {@link PresaleInteractor}.
     *
     * @throws IOException if an I/O exception occurs during the test.
     */
    @Test
    void successTest() throws IOException {
        // Create input data for the test case
        PresaleInputData inputData = new PresaleInputData("M5V3L9", "Olivia Rodrigo");

        // Create an in-memory user data access object
        PresaleDataAccess userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        PresaleOutputBoundary successPresenter = new PresaleOutputBoundary() {
            /**
             * Prepares the success view based on the provided output data and asserts that it matches the expected values.
             *
             * @param user The output data for the presale operation.
             */
            @Override
            public void prepareSuccessView(PresaleOutputData user) {
                // Expected test for artist "Olivia Rodrigo"
                String test = "Olivia Rodrigo - GUTS world tour\n" +
                        "Event URL: https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-29-2024/event/10005F2839E1667D\n" +
                        "Presale Status: No presale date available. Click to see if there's tickets available\n" +
                        "\n" +
                        "Olivia Rodrigo - GUTS world tour\n" +
                        "Event URL: https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-30-2024/event/10005F2E0D0460DE\n" +
                        "Presale Status: No presale date available. Click to see if there's tickets available\n" +
                        "\n";

                // Compare the expected output with the actual output
                assertEquals(test, user.getFormatOutputPresale());
            }

            /**
             * Asserts that the failure view is not expected to be called during a successful test.
             *
             * @param error The error message (unexpected in this context).
             */
            @Override
            public void prepareFailView(String error) {
                // If the use case fails, then fail the test
                fail("Use case failure is unexpected.");
            }
        };

        // Create an event strategy (artist strategy)
        EventStrategy artistStrategy = new ArtistStrategy();

        // Create the presale interactor and add dependencies
        PresaleInputBoundary interactor = new PresaleInteractor(userRepository, artistStrategy, successPresenter, new UserModelFactory());

        // Execute the presale interactor with the provided input data
        interactor.execute(inputData);
    }
}
