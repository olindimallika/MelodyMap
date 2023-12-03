package use_case.notify_user_tour;

import entity.UserFactory;
import org.json.JSONObject;

import java.util.ArrayList;
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
           String artistNameInput = notifyInputData.getFavouriteArtistNames();
           String lowerArtistNames = artistNameInput.toLowerCase();

           String[] artistNameList = lowerArtistNames.split(",");
           ArrayList<String> hasFavouriteArtistConcert = new ArrayList<>();

           for(String str : artistNameList){

               String artistNames;
               if (str.contains(" ")){
                   artistNames = str.replace(' ', '-');
               } else {
                   artistNames = str;
               }
               JSONObject artistInfo = userDataAccessObject.getPerformerInfo(artistNames);

               if (artistInfo.get("has_upcoming_events").equals(true)) {
                   hasFavouriteArtistConcert.add("Your favourite artist is on tour!");
               } else {
                   hasFavouriteArtistConcert.add("Sorry, your favourite artist doesn't have any upcoming concerts :(");
               }

           }

           // testing get postal code method
           // System.out.println(userDataAccessObject.getPostalCode());

           NotifyOutputData notifyOutputData = new NotifyOutputData(hasFavouriteArtistConcert);
           userPresenter.prepareSuccessView(notifyOutputData);

       } catch (Exception e){
           // if user enters an artist name that cannot be entered into the seat geek api
           throw new InputMismatchException();
       }
    }


}
