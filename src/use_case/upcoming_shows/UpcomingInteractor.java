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

/**
 * The {@code UpcomingInteractor} class implements the {@link UpcomingInputBoundary} interface,
 * serving as an intermediary between the user interface and the data access layer for handling upcoming shows requests.
 * It orchestrates the retrieval of upcoming shows information, processes the data, and communicates the results
 * to the user interface through the provided {@link UpcomingOutputBoundary}.
 */
public class UpcomingInteractor implements UpcomingInputBoundary {

    /**
     * The data access object responsible for retrieving upcoming shows information.
     */
    final UpcomingDataAccess userDataAccessObject;

    /**
     * The presenter responsible for formatting and displaying upcoming shows information to the user interface.
     */
    final UpcomingOutputBoundary userPresenter;

    /**
     * The factory for creating user objects.
     */
    final UserFactory userFactory;
    /**
     * Constructs a new instance of {@code UpcomingInteractor} with the specified dependencies.
     *
     * @param userDataAccessInterface The data access object for retrieving upcoming shows information.
     * @param upcomingOutputBoundary The presenter for formatting and displaying upcoming shows information.
     * @param userFactory             The factory for creating user objects.
     */
    public UpcomingInteractor(UpcomingDataAccess userDataAccessInterface,
                              UpcomingOutputBoundary upcomingOutputBoundary,
                              UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = upcomingOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the upcoming shows use case based on the provided input data.
     * Retrieves upcoming shows information, processes the data, and communicates the results to the user interface.
     *
     * @param upcomingInputData The input data for the upcoming shows use case.
     */
    public void execute(UpcomingInputData upcomingInputData){

        try {
            // Build a user object with the provided postal code
                UserBuilder builder = new UserBuilder();
                User user = builder.addPostalCode(upcomingInputData.getPostalCode()).build();

            // Define the strategy for upcoming shows
                EventStrategy upcomingStrategy = new UpcomingStrategy();

            // Process events using the defined strategy
                EventProcesser<List<JSONObject>> eventProcesser = new EventProcesser(upcomingStrategy);
            // Retrieve upcoming shows from the data access object
                List<JSONObject> events = eventProcesser.processEvent(user);

            // Create output data with the retrieved upcoming shows
                LinkedHashMap<String, String> upcomingShows = userDataAccessObject.getUpcomingShows(events);

                UpcomingOutputData upcomingOutputData = new UpcomingOutputData(upcomingShows);
            // Present the success view to the user interface
                userPresenter.prepareSuccessView(upcomingOutputData);
            // Handle exceptions and print the stack trace for debugging purposes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
