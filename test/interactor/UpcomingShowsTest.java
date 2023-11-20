package interactor;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import data_access.InMemoryUserDataAccessObject;
import use_case.upcoming_shows.*;
import static org.junit.jupiter.api.Assertions.*;


class UpcomingShowsTest {
    @Test
    void successTest(){
        // creating the Input Data object
        UpcomingInputData inputData = new UpcomingInputData("M1J3J9");
        UpcomingDataAccess userRepository = new InMemoryUserDataAccessObject();

        // creating a presenter that implements output boundary to validate output data
        UpcomingOutputBoundary successPresenter = new UpcomingOutputBoundary() {
            @Override
            public void prepareSuccessView(UpcomingOutputData user) {
                assertNotNull(user.getUpcomingConcerts());
            }

            @Override
            public void prepareFailView(String error){
                fail("Use case failure is unexpected");
            }

        };

        UpcomingInputBoundary interactor = new UpcomingInteractor(userRepository, successPresenter, new UserModelFactory());
        interactor.execute(inputData);
    }
}
