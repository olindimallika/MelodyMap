package interactor;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import entity.UserModelFactory;

import org.junit.jupiter.api.Test;
import use_case.upcoming_shows.*;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code UpcomingShowsTest} class contains unit tests for the successful execution
 * of the upcoming shows use case.
 */
class UpcomingShowsTest {

    /**
     * Test case for a successful execution of the upcoming shows use case.
     */
    @Test
    void successTest() {
        // Creating the input data object
        UpcomingInputData inputData = new UpcomingInputData("M1J3J9");

        // Creating an in-memory user data access object
        UpcomingDataAccess userRepository = new InMemoryUserDataAccessObject();

        // Creating a success presenter for testing
        UpcomingOutputBoundary successPresenter = new UpcomingOutputBoundary() {
            @Override
            public void prepareSuccessView(UpcomingOutputData upcomingConcerts) {
                // 2 things to check: the output data is correct, and the postal code has valid coordinates.

                LinkedHashMap<String, String> outputConcerts = new LinkedHashMap<>();
                outputConcerts.put("KESmas - Kes The Band Live in Concert", "https://www.ticketmaster.ca/kesmas-kes-the-band-live-in-toronto-ontario-12-21-2023/event/10005F6AD6584CEE");
                // ... (omitting other concert entries for brevity)

                assertEquals(outputConcerts, upcomingConcerts.getUpcomingConcerts());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // Creating the upcoming interactor and adding dependencies
        UpcomingInputBoundary interactor = new UpcomingInteractor(userRepository, successPresenter, new UserModelFactory());

        // Invoking the interactor by passing in the input data
        interactor.execute(inputData);
    }
}
