package use_case.notify_user_tour;

import entity.UserFactory;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
           System.out.println("We were unable to find your favourite artist. Please exit and try again.");
       }
    }
}
