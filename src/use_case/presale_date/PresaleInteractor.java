package use_case.presale_date;

import data_access.ArtistStrategy;
import data_access.EventProcesser;
import entity.*;
import org.json.JSONObject;
import use_case.EventStrategy;
import use_case.notify_user_tour.NotifyOutputData;

import java.io.IOException;
import java.util.*;

public class PresaleInteractor implements PresaleInputBoundary {

    final PresaleDataAccess presaleDataAccessObject;
    final PresaleOutputBoundary presalePresenter;
    final EventStrategy eventStrategy;
    final UserFactory userFactory;


    public PresaleInteractor(PresaleDataAccess presaleDataAccessInterface,
                             EventStrategy eventStrategy,
                             PresaleOutputBoundary presaleOutputBoundary,
                             UserFactory userFactory) {
        this.presaleDataAccessObject = presaleDataAccessInterface;
        this.eventStrategy = eventStrategy;
        this.presalePresenter = presaleOutputBoundary;
        this.userFactory = userFactory;
    }

    public void execute(PresaleInputData presaleInputData) throws IOException {
        //String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        //PresaleMine ticketmasterAPI = new PresaleMine(apiKey);

        // Example: Call getEventsFromLatLong from PresaleBrittany
        try {
            String strFavArtists = presaleInputData.getFavArtists();
            String[] artistsArray = strFavArtists.split(", ");
            List<String> artistList = Arrays.asList(artistsArray);
            ArtistFactory artistFactory = new ArtistModelFactory();
            ArrayList<Artist> favArtists = new ArrayList<>();

            for (String artistString : artistList) {
                Artist artist = artistFactory.create(artistString);
                favArtists.add(artist);
            }

            UserFactory userFactory = new UserModelFactory();
            User user = userFactory.create(presaleInputData.getPostalCode(), favArtists);

            EventStrategy artistStrategy = new ArtistStrategy();

            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
            List<List<JSONObject>> events = eventProcesser.processEvent(user);


            //old below:
            List<String> artNameList = new ArrayList<>();


            for (List<JSONObject> miniList: events){
                for (JSONObject event : miniList){

                    presaleDataAccessObject.addEventInfo(event);
                    String artName = presaleDataAccessObject.getArtistName(event);
                    artNameList.add(artName);
                }
            }

            // Print event URLs and presale dates
            List<String> urls = presaleDataAccessObject.getEventUrls();
            //System.out.println(urls);
            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();

            //make a hashmapp of artist name mapped to URL

            HashMap<String, String> artistUrlMap = new HashMap<>();
            // Populate the HashMap
            for (int i = 0; i < artNameList.size(); i++) {
                String artistName = artNameList.get(i);
                String urlArtist = urls.get(i);
                artistUrlMap.put(artistName, urlArtist);
            }

            // Now artistUrlMap contains the mapping of artist names to URLs
            // You can use it as needed
            //System.out.println(artistUrlMap);


            // iterate through every url and place the preale date beside it.
            for (int i = 0; i < urls.size(); i++) {
                presaleDataAccessObject.formatOutputPresale(artNameList.get(i),urls.get(i),presaleDates.get(i));
//                System.out.println(artNameList.get(i));
//                System.out.println("Event URL: " + urls.get(i));
//                System.out.println("Presale: " + presaleDates.get(i));
//                System.out.println(); // Add a newline for better readability
            }

            String displayFormatOut = presaleDataAccessObject.getFormatOutputPresale();
            //System.out.println(displayFormatOut);
            //List<String> empty = new ArrayList<>();
            //System.out.println(empty.add(displayFormatOut));

            PresaleOutputData presaleOutputData = new PresaleOutputData(displayFormatOut);
            presalePresenter.prepareSuccessView(presaleOutputData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException as needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



//        //String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
//        //PresaleMine ticketmasterAPI = new PresaleMine(apiKey);
//
//        // Example: Call getEventsFromLatLong from PresaleBrittany
//        try {
//            String strFavArtists = presaleInputData.getFavArtists();
//            String[] artistsArray = strFavArtists.split(", ");
//            List<String> artistList = Arrays.asList(artistsArray);
//            ArtistFactory artistFactory = new ArtistModelFactory();
//            ArrayList<Artist> favArtists = new ArrayList<>();
//
//            for (String artistString : artistList) {
//                Artist artist = artistFactory.create(artistString);
//                favArtists.add(artist);
//            }
//
//            UserFactory userFactory = new UserModelFactory();
//            User user = userFactory.create(presaleInputData.getPostalCode(), favArtists);
//
//            EventStrategy artistStrategy = new ArtistStrategy();
//
//            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
//            List<List<JSONObject>> events = eventProcesser.processEvent(user);
//
//
//            for (List<JSONObject> miniList: events){
//                for (JSONObject event : miniList){
//                    // Add event information to the EventInfo object
//                    presaleDataAccessObject.addEventInfo(event);
//                }
//            }
//
//            // Print event URLs and presale dates
//            List<String> urls = presaleDataAccessObject.getEventUrls();
//            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();
//
//
//            // iterate through every url and place the preale date beside it.
//            for (int i = 0; i < urls.size(); i++) {
//                System.out.println("Event URL: " + urls.get(i));
//                System.out.println("Presale: " + presaleDates.get(i));
//                System.out.println(); // Add a newline for better readability
//            }
//            System.out.println(presaleDates);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the IOException as needed
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }



//            if (events.isEmpty()){
//                presalePresenter.prepareFailView("Sorry, none of these artists have shows close to you.");
//            }else{
//                System.out.println("\n");
//                LinkedHashMap<String, String>
//            }






            //---

//            int radius = 10;
//            String unit = "miles";
//            //String classification = "music";
//
//            UserBuilder builder = new UserBuilder();
//            User user = builder.addPostalCode(presaleInputData.getPostalCode()).build();
//
//            String favArtists = presaleInputData.getFavArtists();
//
//            for (Artist artist: favArtists){
//                ArtistFactory artistFactory = new ArtistModelFactory();
//                Artist recentArtist = artistFactory.create(artist);
//            }
//
//
//            // Example: Get only music events based on geoPoint without specifying radius and unit
//            List<JSONObject> eventL = presaleDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);
//
//            // Iterate throguh every event in events list
//            for (JSONObject event : eventL) {
//                // Add event information to the EventInfo object
//                presaleDataAccessObject.addEventInfo(event);
//            }
//
//            // Print event URLs and presale dates
//            List<String> urls = presaleDataAccessObject.getEventUrls();
//            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();
//
//
//            // iterate through every url and place the preale date beside it.
//            for (int i = 0; i < urls.size(); i++) {
//                System.out.println("Event URL: " + urls.get(i));
//                System.out.println("Presale: " + presaleDates.get(i));
//                System.out.println(); // Add a newline for better readability
//            }
//            System.out.println(presaleDates);
//
//            //pr.printEventUrls(events);
//
//            // Process the events as needed
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the IOException as needed
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        //below is artist with only string

//        //String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
//        //PresaleMine ticketmasterAPI = new PresaleMine(apiKey);
//
//        // Example: Call getEventsFromLatLong from PresaleBrittany
//        try {
//            int radius = 10;
//            String unit = "miles";
//            //String classification = "music";
//
//            UserBuilder builder = new UserBuilder();
//            User user = builder.addPostalCode(presaleInputData.getPostalCode()).build();
//
//            ArrayList<Artist> favArtists = presaleInputData.getFavouriteArtistName();
//
//
//            // Example: Get only music events based on geoPoint without specifying radius and unit
//            List<JSONObject> eventL = presaleDataAccessObject.getEventsFromLatLong(radius, unit, "music", user);
//
//            // Iterate throguh every event in events list
//            for (JSONObject event : eventL) {
//                // Add event information to the EventInfo object
//                presaleDataAccessObject.addEventInfo(event);
//            }
//
//            // Print event URLs and presale dates
//            List<String> urls = presaleDataAccessObject.getEventUrls();
//            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();
//
//
//            // iterate through every url and place the preale date beside it.
//            for (int i = 0; i < urls.size(); i++) {
//                System.out.println("Event URL: " + urls.get(i));
//                System.out.println("Presale: " + presaleDates.get(i));
//                System.out.println(); // Add a newline for better readability
//            }
//            System.out.println(presaleDates);
//
//            //pr.printEventUrls(events);
//
//            // Process the events as needed
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the IOException as needed
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }



    }


    //ADD TEST FOR INTERACTOR

}
