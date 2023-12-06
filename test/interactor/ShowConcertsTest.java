package interactor;

import org.junit.jupiter.api.Test;

import use_case.show_concerts.*;

import static org.junit.jupiter.api.Assertions.fail;


public class ShowConcertsTest {
    @Test
    void successTest() {

        // This creates a successPresenter that tests whether the test case is as we expect.
        ShowConcertsOutputBoundary successPresenter = new ShowConcertsOutputBoundary() {
            @Override
            public void prepareSuccessView() {
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        // creating the interactor
        ShowConcertsInputBoundary interactor = new ShowConcertsInteractor(successPresenter);

        // invoking the interactor by passing in the input data
        interactor.execute();
    }
}
