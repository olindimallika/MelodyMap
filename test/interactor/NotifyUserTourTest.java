package interactor;

import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.*;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The NotifyUserTourTest class contains JUnit tests for the NotifyInteractor and associated
 * components in the "interactor" package. It focuses on testing the success scenarios of the notification feature.
 *
 * The test case checks the functionality of the NotifyInteractor by verifying that the interactor
 * correctly handles input data, interacts with the data access layer, and provides the expected output to the
 * NotifyOutputBoundary. The test creates an input data object, a data access object, and a success
 * output presenter to simulate a successful execution of the notification use case.
 *
 * First, create a NotifyInputData object representing the input data, such as a list of favorite artists and a
 * InMemoryUserDataAccessObject as a mock data access object for the user's tour information.
 *
 * Then, create a success output presenter (NotifyOutputBoundary) to verify the correctness of the
 * output data, including the tour information for each artist.
 *
 * Create a NotifyInteractor instance, providing it with the input data, data access object, and
 * the success output presenter.
 *
 * Finally, invoke the interactor by passing in the input data.
 *
 */
public class NotifyUserTourTest {

    /**
     * Test the success scenario of the NotifyInteractor by creating necessary input data, data access,
     * and output presenter, and verifying that the interactor execution results in the expected output.
     */
    @Test
    void successTest() {
        NotifyInputData inputData = new NotifyInputData("Laufey, Taylor Swift, Olivia Rodrigo, Niki, Lizzy Mcalpine");
        NotifyDataAccess userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        NotifyOutputBoundary successPresenter = new NotifyOutputBoundary() {
            @Override
            public void prepareSuccessView(NotifyOutputData user) {

                LinkedHashMap<String, String> hasTourMap = new LinkedHashMap<>();
                hasTourMap.put("Laufey", "has a tour");
                hasTourMap.put("Taylor Swift", "has a tour");
                hasTourMap.put("Olivia Rodrigo", "has a tour");
                hasTourMap.put("Niki", "doesn't have a tour");
                hasTourMap.put("Lizzy Mcalpine", "doesn't have a tour");
                assertEquals(hasTourMap, user.getArtistOnTour());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // creating the interactor
        NotifyInputBoundary interactor = new NotifyInteractor(userRepository, successPresenter, new UserModelFactory());

        // invoking the interactor by passing in the input data
        interactor.execute(inputData);
    }
}