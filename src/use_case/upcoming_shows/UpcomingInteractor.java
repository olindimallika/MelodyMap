package use_case.upcoming_shows;

import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.upcoming_shows.UpcomingPresenter;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
            if (userDataAccessObject.existsInCoords(upcomingInputData.getPostalCode())){
                userPresenter.prepareFailView("Unable to find coordinates for postal code.");
            } else {
                System.out.println("\n");
                int radius = 10;
                String unit = "miles";

                UserBuilder builder = new UserBuilder();
                User user = builder.addPostalCode(upcomingInputData.getPostalCode()).build();
                List<JSONObject> eventL = userDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);

                LinkedHashMap<String, String> upcomingShows = userDataAccessObject.getUpcomingShows(eventL);

                UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
                userPresenter.prepareSuccessView(upcomingOutputData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
