package use_case.upcoming_shows;

import data_access.EventProcesser;
import data_access.EventStrategy;
import data_access.FileUserDataAccessObject;
import data_access.UpcomingStrategy;
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
                UserBuilder builder = new UserBuilder();
                User user = builder.addPostalCode(upcomingInputData.getPostalCode()).build();

                EventStrategy upcomingStrategy = new UpcomingStrategy();

                EventProcesser<List<JSONObject>> eventProcesser = new EventProcesser(upcomingStrategy);
                List<JSONObject> events = eventProcesser.processEvent(user);

                LinkedHashMap<String, String> upcomingShows = userDataAccessObject.getUpcomingShows(events);

                UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
                userPresenter.prepareSuccessView(upcomingOutputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
