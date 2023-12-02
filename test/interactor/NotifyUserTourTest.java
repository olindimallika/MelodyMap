package interactor;

import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import use_case.notify_user_tour.*;

import static org.junit.jupiter.api.Assertions.*;

public class NotifyUserTourTest {
    @Test
    void successTest() {
        NotifyInputData inputData = new NotifyInputData("Taylor Swift");
        NotifyDataAccess userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        NotifyOutputBoundary successPresenter = new NotifyOutputBoundary() {
            @Override
            public void prepareSuccessView(NotifyOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Your favourite artist has 18 upcoming concerts! " +
                        "Purchase tickets here (click on link): "
                        , user.getFavouriteArtistConcerts());
                assertTrue(userRepository.existsInApi());
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
