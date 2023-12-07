package use_case.notify_user_tour;

import entity.UserFactory;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;

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

            String[] artistNameList = artistNameInput.split(",");
            LinkedHashMap<String, String> hasFavouriteArtistConcert = new LinkedHashMap<>();

            for(String str : artistNameList){

                String artistName;
                String lowerArtistName;
                if (str.contains(" ")){
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

        } catch (Exception e){
            // if user enters an artist name that cannot be entered into the seat geek api
            throw new InputMismatchException();
        }
    }


}