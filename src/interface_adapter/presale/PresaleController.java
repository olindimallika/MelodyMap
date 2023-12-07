package interface_adapter.presale;

import use_case.presale_date.PresaleInputBoundary;
import use_case.presale_date.PresaleInputData;

import java.io.IOException;

/**
 * The {@code PresaleController} class is responsible for coordinating and handling user requests related to the
 * presale functionality. It serves as an interface between the user interface and the underlying use case interactor.
 */
public class PresaleController {

    /**
     * The use case interactor responsible for executing the presale functionality.
     */
    final PresaleInputBoundary userPresaleUseCaseInteractor;

    /**
     * Constructs a new instance of {@code PresaleController} with the provided presale use case interactor.
     *
     * @param userPresaleUseCaseInteractor The use case interactor for the presale functionality.
     */
    public PresaleController(PresaleInputBoundary userPresaleUseCaseInteractor) {
        this.userPresaleUseCaseInteractor = userPresaleUseCaseInteractor;
    }

    /**
     * Executes the presale functionality based on the provided postal code and favorite artist.
     *
     * @param postalCode      The user's postal code.
     * @param favouriteArtist The user's favorite artist.
     * @throws IOException If an I/O error occurs during the execution of the presale functionality.
     */
    public void execute(String postalCode, String favouriteArtist) throws IOException {
        // Creating an input data object with the provided postal code and favorite artist
        PresaleInputData presaleInputData = new PresaleInputData(postalCode, favouriteArtist);

        // Delegating the execution of the presale functionality to the use case interactor
        userPresaleUseCaseInteractor.execute(presaleInputData);
    }
}



