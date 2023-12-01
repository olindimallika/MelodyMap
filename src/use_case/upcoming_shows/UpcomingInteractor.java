package use_case.upcoming_shows;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

import java.util.LinkedHashMap;
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

            LinkedHashMap<String, String> upcomingShowMap = userDataAccessObject.getUpcomingShows(eventL);
            String upcomingShows = userDataAccessObject.formatShows(upcomingShowMap);
            UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
            userPresenter.prepareSuccessView(upcomingOutputData);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
