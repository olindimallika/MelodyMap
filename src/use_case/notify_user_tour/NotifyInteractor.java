package use_case.notify_user_tour;

import entity.UserFactory;
import org.json.JSONObject;

import java.util.InputMismatchException;

public class NotifyInteractor implements NotifyInputBoundary {
    final NotifyDataAccess userDataAccessObject;
    final NotifyOutputBoundary userPresenter;
    final UserFactory userFactory;

    public NotifyInteractor(NotifyDataAccess notifyDataAccess,
                            NotifyOutputBoundary notifyOutputBoundary,
                            UserFactory userFactory){
        this.userDataAccessObject = notifyDataAccess;
        this.userPresenter = notifyOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(NotifyInputData notifyInputData){

       try {
           // converting user input, so it will fit what the api expects
           String artistNameInput = notifyInputData.getFavouriteArtistName();
           String lowerArtistName = artistNameInput.toLowerCase();

           String artistName;
           if (lowerArtistName.contains(" ")){
               artistName = lowerArtistName.replace(' ', '-');
           } else {
               artistName = lowerArtistName;
           }
           JSONObject artistInfo = userDataAccessObject.getPerformerInfo(artistName);

           String favouriteArtistConcert;
           if (artistInfo.get("has_upcoming_events").equals(true)) {
               favouriteArtistConcert = "Your favourite artist has " + userDataAccessObject.getNumUpcomingConcerts() +
                       " upcoming concerts! Purchase tickets here (click on link): ";
           } else {
               favouriteArtistConcert = "Sorry, your favourite artist doesn't have any upcoming concerts :(";
           }

           String hyperlink = userDataAccessObject.getTicketLink();

           NotifyOutputData notifyOutputData = new NotifyOutputData(favouriteArtistConcert, hyperlink);
           userPresenter.prepareSuccessView(notifyOutputData);

       } catch (Exception e){
           // if user enters an artist name that cannot be entered into the seat geek api
           throw new InputMismatchException();
       }
    }
}
