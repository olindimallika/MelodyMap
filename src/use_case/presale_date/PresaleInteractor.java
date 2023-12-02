package use_case.presale_date;

import data_access.FileUserDataAccessObject;
import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PresaleInteractor implements PresaleInputBoundary {

    final PresaleDataAccess presaleDataAccessObject;
    final PresaleOutputBoundary presalePresenter;
    final UserFactory userFactory;


    public PresaleInteractor(PresaleDataAccess presaleDataAccessInterface,
                             PresaleOutputBoundary presaleOutputBoundary,
                             UserFactory userFactory) {
        this.presaleDataAccessObject = presaleDataAccessInterface;
        this.presalePresenter = presaleOutputBoundary;
        this.userFactory = userFactory;
    }
    


    public void execute(PresaleInputData presaleInputData) throws IOException {

        //String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        //PresaleMine ticketmasterAPI = new PresaleMine(apiKey);

        // Example: Call getEventsFromLatLong from PresaleBrittany
        try {
            int radius = 10;
            String unit = "miles";
            //String classification = "music";

            UserBuilder builder = new UserBuilder();
            User user = builder.addPostalCode(presaleInputData.getPostalCode()).build();

            // Example: Get only music events based on geoPoint without specifying radius and unit
            List<JSONObject> eventL = presaleDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);

            // Iterate throguh every event in events list
            for (JSONObject event : eventL) {
                // Add event information to the EventInfo object
                presaleDataAccessObject.addEventInfo(event);
            }

            // Print event URLs and presale dates
            List<String> urls = presaleDataAccessObject.getEventUrls();
            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();


            // iterate through every url and place the preale date beside it.
            for (int i = 0; i < urls.size(); i++) {
                System.out.println("Event URL: " + urls.get(i));
                System.out.println("Presale: " + presaleDates.get(i));
                System.out.println(); // Add a newline for better readability
            }
            System.out.println(presaleDates);

            //pr.printEventUrls(events);

            // Process the events as needed
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException as needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }


    //ADD TEST FOR INTERACTOR

}
