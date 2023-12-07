package interactor;

import data_access.InMemoryUserDataAccessObject;
import data_access.LocationFinder;
import entity.UserFactory;
import entity.UserModelFactory;

import org.junit.jupiter.api.Test;
import use_case.artist_venue.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for the ArtistVenueInteractor use case.
 */


class ArtistVenueTest {

    /**
     * Test case for a successful execution of the ArtistVenue use case.
     *
     * @throws IOException if an I/O error occurs while executing the use case.
     */

    @Test
    void successTest() throws IOException {

        // Creating the input data object

        LinkedHashMap<String, String> tour = new LinkedHashMap<>();
        tour.put("Taylor Swift", "has a tour");
        tour.put("Olivia Rodrigo", "has a tour");

        ArtistVenueInputData inputData = new ArtistVenueInputData(tour);

        ArtistVenueDataAccess userRepository = new InMemoryUserDataAccessObject();

        UserFactory userFactory = new UserModelFactory();

        LocationFinder locationFinder = new LocationFinder();

        // Creating the success presenter
        ArtistVenueOutputBoundary successPresenter = new ArtistVenueOutputBoundary() {
            @Override
            public void prepareSuccessView(ArtistVenueOutputData upcomingConcert) {
                LinkedHashMap<String, List<String>> outputConcerts = new LinkedHashMap<>();

                outputConcerts.put("The Taylor Party: The TS Dance Party - 19+", Arrays.asList(
                        "https://www.ticketmaster.ca/the-taylor-party-the-ts-dance-toronto-ontario-12-16-2023/event/10005F5BDFB3368A",
                        "https://www.ticketweb.ca/event/taylor-swift-nye-party-2024-the-opera-house-tickets/13815958",
                        "https://www.ticketmaster.ca/taylor-swift-the-eras-tour-toronto-ontario-11-14-2024/event/10005F01FAA14AE2"
                ));

                outputConcerts.put("Olivia Rodrigo - GUTS world tour", Arrays.asList(
                                "https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-29-2024/event/10005F2839E1667D",
                                "https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-30-2024/event/10005F2E0D0460DE"
                        ));

                assertEquals(outputConcerts, upcomingConcert.getUpcomingArtistShows());

            }

            @Override
            public void prepareFailView(String error) {

                fail("Use case failure is unexpected.");
            }
        };

        // Creating the interactor

        ArtistVenueInputBoundary interactor = new ArtistVenueInteractor(userRepository, successPresenter, userFactory, locationFinder);
        interactor.execute(inputData);

    }
}