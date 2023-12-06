package interactor;

import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.*;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class NotifyUserTourTest {
    @Test
    void successTest() {
        NotifyInputData inputData = new NotifyInputData("Laufey, Taylor Swift, Olivia Rodrigo, Niki, Lizzy Mcalpine");
        NotifyDataAccess userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        NotifyOutputBoundary successPresenter = new NotifyOutputBoundary() {
            @Override
            public void prepareSuccessView(NotifyOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
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

        NotifyInputBoundary interactor = new NotifyInteractor(userRepository, successPresenter, new UserModelFactory());
        interactor.execute(inputData);
    }
}
