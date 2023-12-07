package use_case.notify_user_tour;

import entity.UserFactory;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;

/**
 * The {@code NotifyInteractor} class represents the interactor component for the "Notify User Tour" use case.
 * It coordinates the interaction between the user interface, data access, and presentation layers.
 */
public class NotifyInteractor implements NotifyInputBoundary {

    /**
     * The data access object responsible for interacting with tour-related data.
     */
    final NotifyDataAccess userDataAccessObject;

    /**
     * The presenter responsible for preparing the user interface with the output data.
     */
    final NotifyOutputBoundary userPresenter;

    /**
     * The factory responsible for creating user entities.
     */
    final UserFactory userFactory;

    /**
     * Constructs a new {@code NotifyInteractor} object with the specified dependencies.
     *
     * @param notifyDataAccess       The data access object for tour-related data.
     * @param notifyOutputBoundary   The presenter for preparing the user interface.
     * @param userFactory            The factory for creating user entities.
     */
    public NotifyInteractor(NotifyDataAccess notifyDataAccess,
                            NotifyOutputBoundary notifyOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = notifyDataAccess;
        this.userPresenter = notifyOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the "Notify User Tour" use case with the given input data.
     *
     * @param notifyInputData The input data containing information needed for notifying users about tours.
     * @throws InputMismatchException If there is an issue with the input data or the seat geek API.
     */
    @Override
    public void execute(NotifyInputData notifyInputData) throws InputMismatchException {
        try {
            // Converting user input, so it will fit what the API expects
            String artistNameInput = notifyInputData.getFavouriteArtistNames();
            String[] artistNameList = artistNameInput.split(",");
            LinkedHashMap<String, String> hasFavouriteArtistConcert = new LinkedHashMap<>();

            for (String str : artistNameList) {
                String artistName;
                String lowerArtistName;
                if (str.contains(" ")) {
                    artistName = str.strip();
                    lowerArtistName = artistName.toLowerCase();
                } else {
                    artistName = str;
                    lowerArtistName = artistName.toLowerCase();
                }
                String tourInfo = userDataAccessObject.hasATour(lowerArtistName, "music");
                hasFavouriteArtistConcert.put(artistName, tourInfo);
            }

            NotifyOutputData notifyOutputData = new NotifyOutputData(hasFavouriteArtistConcert);
            userPresenter.prepareSuccessView(notifyOutputData);

        } catch (Exception e) {
            throw new InputMismatchException();
        }
    }
}
