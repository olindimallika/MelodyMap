package interactor;

import org.junit.jupiter.api.Test;

import use_case.show_concerts.*;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The ShowConcertsTest class contains JUnit tests for the ShowConcertsInteractor and associated
 * components in the "interactor" package. It focuses on testing the success scenarios of the show concerts feature.
 *
 * The test case checks the functionality of the ShowConcertsInteractor by verifying that the interactor
 * correctly interacts with the output boundary and handles the show concerts use case successfully. The test
 * creates a success output presenter to simulate a successful execution of the show concerts use case.
 *
 * First, create a success output presenter (ShowConcertsOutputBoundary) to verify the success scenario.
 *
 * Then, create a ShowConcertsInteractor instance, providing it with the success output presenter.
 *
 * Finally, invoke the interactor to execute the show concerts use case.
 *
 */
public class ShowConcertsTest {

    /**
     * Test the success scenario of the ShowConcertsInteractor by creating a success output presenter
     * and verifying that the interactor execution results in a successful show concerts use case.
     */
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
