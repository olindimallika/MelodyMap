package interface_adapter.notify_user_tour;

import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInputData;

/**
 * Controller class responsible for handling user interactions related to notifying about favorite artists on tour.
 * It communicates with the corresponding use case interactor to execute the notification process.
 */
public class NotifyController {

    /**
     * The interactor responsible for processing notifications based on user input.
     */
    final NotifyInputBoundary userNotifyUseCaseInteractor;

    /**
     * Constructs a new NotifyController with the provided NotifyInputBoundary.
     *
     * @param userNotifyUseCaseInteractor The interactor to handle the notification use case.
     */
    public NotifyController(NotifyInputBoundary userNotifyUseCaseInteractor){
        this.userNotifyUseCaseInteractor = userNotifyUseCaseInteractor;
    }

    /**
     * Executes the notification process for the given favorite artist.
     *
     * @param favouriteArtist The name of the favorite artist for whom the user wants to receive notifications.
     */
    public void execute(String favouriteArtist){
        NotifyInputData notifyInputData = new NotifyInputData(favouriteArtist);
        userNotifyUseCaseInteractor.execute(notifyInputData);
    }
}