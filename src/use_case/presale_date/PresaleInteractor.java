package use_case.presale_date;

import data_access.ArtistStrategy;
import data_access.EventProcesser;
import data_access.EventStrategy;
import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Represents the interactor for the Presale use case, responsible for handling user input and coordinating
 * interactions between the data access layer and the presentation layer.
 */
public class PresaleInteractor implements PresaleInputBoundary {

    /** The data access object for retrieving presale information. */
    final PresaleDataAccess presaleDataAccessObject;

    /** The presenter for displaying presale information to the user. */
    final PresaleOutputBoundary presalePresenter;

    /** The strategy for handling events, such as concerts or shows. */
    final EventStrategy eventStrategy;

    /** The factory for creating user instances. */
    final UserFactory userFactory;

    /**
     * Constructs a new PresaleInteractor with the specified dependencies.
     *
     * @param presaleDataAccessInterface The data access object for retrieving presale information.
     * @param eventStrategy             The strategy for handling events, such as concerts or shows.
     * @param presaleOutputBoundary     The presenter for displaying presale information to the user.
     * @param userFactory               The factory for creating user instances.
     */
    public PresaleInteractor(PresaleDataAccess presaleDataAccessInterface,
                             EventStrategy eventStrategy,
                             PresaleOutputBoundary presaleOutputBoundary,
                             UserFactory userFactory) {
        this.presaleDataAccessObject = presaleDataAccessInterface;
        this.eventStrategy = eventStrategy;
        this.presalePresenter = presaleOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the Presale use case based on the provided input data.
     *
     * @param presaleInputData The input data containing postal code and favorite artists.
     * @throws IOException If an I/O error occurs during the execution.
     */
    public void execute(PresaleInputData presaleInputData) throws IOException {
        try {
            // Extracting favorite artists from the input data
            String strFavArtists = presaleInputData.getFavArtists();
            String[] artistsArray = strFavArtists.split(", ");
            List<String> artistList = Arrays.asList(artistsArray);
            ArtistFactory artistFactory = new ArtistModelFactory();
            ArrayList<Artist> favArtists = new ArrayList<>();

            // Create Artist instances and add them to the list of favorite artists
            for (String artistString : artistList) {
                Artist artist = artistFactory.create(artistString);
                favArtists.add(artist);
            }

            UserFactory userFactory = new UserModelFactory();
            User user = userFactory.create(presaleInputData.getPostalCode(), favArtists);

            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(eventStrategy);
            List<List<JSONObject>> events = eventProcesser.processEvent(user);

            List<String> artNameList = new ArrayList<>();

            // Extract artist names from the events and add them to the list
            for (List<JSONObject> miniList : events) {
                for (JSONObject event : miniList) {
                    presaleDataAccessObject.addEventInfo(event);
                    String artName = presaleDataAccessObject.getArtistName(event);
                    artNameList.add(artName);
                }
            }

            List<String> urls = presaleDataAccessObject.getEventUrls();
            List<String> presaleDates = presaleDataAccessObject.getPresaleDates();

            // Create a HashMap mapping artist names to URLs
            HashMap<String, String> artistUrlMap = new HashMap<>();
            for (int i = 0; i < artNameList.size(); i++) {
                String artistName = artNameList.get(i);
                String urlArtist = urls.get(i);
                artistUrlMap.put(artistName, urlArtist);
            }

            // Format output and prepare success view
            for (int i = 0; i < urls.size(); i++) {
                presaleDataAccessObject.formatOutputPresale(artNameList.get(i), urls.get(i), presaleDates.get(i));
            }

            String displayFormatOut = presaleDataAccessObject.getFormatOutputPresale();
            PresaleOutputData presaleOutputData = new PresaleOutputData(displayFormatOut);
            presalePresenter.prepareSuccessView(presaleOutputData);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
