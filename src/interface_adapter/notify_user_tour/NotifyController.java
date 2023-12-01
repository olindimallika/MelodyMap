package interface_adapter.notify_user_tour;

import use_case.notify_user_tour.NotifyInputBoundary;
import use_case.notify_user_tour.NotifyInputData;

public class NotifyController {
    final NotifyInputBoundary userNotifyUseCaseInteractor;

    public NotifyController(NotifyInputBoundary userNotifyUseCaseInteractor){
        this.userNotifyUseCaseInteractor = userNotifyUseCaseInteractor;
    }

    public void execute(String favouriteArtist){
        NotifyInputData notifyInputData = new NotifyInputData(favouriteArtist);

        userNotifyUseCaseInteractor.execute(notifyInputData);
    }
}
