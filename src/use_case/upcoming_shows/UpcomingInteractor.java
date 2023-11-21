package use_case.upcoming_shows;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpcomingInteractor implements UpcomingInputBoundary{
    final UpcomingDataAccess userDataAccessObject;
    final UpcomingOutputBoundary userPresenter;
    final UserFactory userFactory;

    public UpcomingInteractor(UpcomingDataAccess userDataAccessInterface,
                              UpcomingOutputBoundary upcomingOutputBoundary,
                              UserFactory userFactory){
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = upcomingOutputBoundary;
        this.userFactory = userFactory;
    }

    public void execute(UpcomingInputData upcomingInputData){

        try {
            System.out.println("\n");
            int radius = 10;
            String unit = "miles";

            User user = userFactory.create(upcomingInputData.getPostalCode());
            List<JSONObject> eventL = userDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);
            ArrayList<String> websites = userDataAccessObject.printEventUrls(eventL);

            HashMap<String, String> upcomingShows = userDataAccessObject.getUpcomingShows(websites);
            UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
            userPresenter.prepareSuccessView(upcomingOutputData);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
