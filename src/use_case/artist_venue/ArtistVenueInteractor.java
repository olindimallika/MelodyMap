package use_case.artist_venue;

import data_access.*;
import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * The `ArtistVenueInteractor` class represents the use case interactor for handling artist and venue-related operations.
 * It implements the `ArtistVenueInputBoundary` interface, orchestrating the execution of the use case
 * and interacting with data access objects, presenters, and other necessary components.
 */
public class ArtistVenueInteractor implements ArtistVenueInputBoundary {

    /**
     * The data access object responsible for retrieving artist and venue-related data.
     */
    final ArtistVenueDataAccess userDataAccessObject;

    /**
     * The output boundary for presenting the results of the artist and venue-related use case.
     */
    final ArtistVenueOutputBoundary artistPresenter;

    /**
     * The factory for creating user entities.
     */
    final UserFactory userFactory;

    /**
     * The location finder for determining the location of the user.
     */
    final LocationFinder locationFinder;

    /**
     * Constructs an `ArtistVenueInteractor` with the specified dependencies.
     *
     * @param artistVenueDataAccess The data access object for artist and venue-related operations.
     * @param artistPresenter       The output boundary for presenting results.
     * @param userFactory           The factory for creating user entities.
     * @param locationFinder        The location finder for determining user location.
     */
    public ArtistVenueInteractor(ArtistVenueDataAccess artistVenueDataAccess,
                                 ArtistVenueOutputBoundary artistPresenter,
                                 UserFactory userFactory,
                                 LocationFinder locationFinder) {
        this.userDataAccessObject = artistVenueDataAccess;
        this.artistPresenter = artistPresenter;
        this.userFactory = userFactory;
        this.locationFinder = locationFinder;
    }

    /**
     * Executes the artist and venue-related use case based on the provided input data.
     *
     * @param artistVenueInputData The input data containing information for the use case execution.
     * @throws IOException If an I/O error occurs during the execution of the use case.
     */
    @Override
    public void execute(ArtistVenueInputData artistVenueInputData) throws IOException {
        try {

            // Define the strategy for processing events related to artists
            EventStrategy<List<List<JSONObject>>> artistStrategy = new ArtistStrategy();

            // Extract artist names from the input data
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(artistVenueInputData.getArtistTours().keySet());

            // Create artists based on the extracted names
            ArtistFactory artistFactory = new ArtistModelFactory();
            ArrayList<Artist> favArtists = new ArrayList<>();

            for (String artistString : arrayList) {
                Artist artist = artistFactory.create(artistString);
                favArtists.add(artist);
            }

            // Create a user with the extracted artists
            UserFactory userFactory = new UserModelFactory();
            User user = userFactory.create("L1C0K1", favArtists);

            // Process events using the defined strategy
            EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
            List<List<JSONObject>> eventsList = eventProcesser.processEvent(user);

            // Retrieve upcoming artist shows from the data access object
            LinkedHashMap<String, List<String>> upcomingArtistShows = userDataAccessObject.getUpcomingArtistShows(eventsList);

            // Create output data and prepare the success view
            ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(upcomingArtistShows);
            artistPresenter.prepareSuccessView(artistVenueOutputData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
