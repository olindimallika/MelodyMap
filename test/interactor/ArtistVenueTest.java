//package interactor;
//
//import data_access.ArtistStrategy;
//import data_access.EventProcesser;
//import data_access.InMemoryUserDataAccessObject;
//import entity.*;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import data_access.EventStrategy;
//import use_case.artist_venue.*;
////import use_case.presale_date.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AristVenueTest {
//    @Test
//    void successTest() throws IOException {
//        ArtistVenueInputData inputData = new ArtistVenueInputData("L1C0K1", "Taylor Swift, Olivia Rodrigo");
//        ArtistVenueDataAccess userRepository = new InMemoryUserDataAccessObject();
//
//        EventStrategy artistStrategy = new ArtistStrategy();
//
//        UserFactory userFactory = new UserModelFactory();
//        ArtistFactory artistFactory = new ArtistModelFactory();
//        Artist artist1 = artistFactory.create("Taylor Swift");
//        Artist artist2 = artistFactory.create("Olivia Rodrigo");
//        ArrayList<Artist> favArtists = new ArrayList<>();
//        favArtists.add(artist1);
//        favArtists.add(artist2);
//        User user = userFactory.create("L1C0K1", favArtists);
//
//        EventProcesser<List<List<JSONObject>>> eventProcesser = new EventProcesser(artistStrategy);
//        List<List<JSONObject>> events = eventProcesser.processEvent(user);
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        ArtistVenueOutputBoundary successPresenter = new ArtistVenueOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ArtistVenueOutputData user) {
//                String test = "{The Taylor Party: The TS Dance Party - 19+=[https://www.ticketmaster.ca/the-taylor-party-the-ts-dance-toronto-ontario-12-16-2023/event/10005F5BDFB3368A, https://www.ticketweb.ca/event/taylor-swift-nye-party-2024-the-opera-house-tickets/13815958], Olivia Rodrigo - GUTS world tour=[https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-29-2024/event/10005F2839E1667D, https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-30-2024/event/10005F2E0D0460DE]}"
//                ;
//                assertEquals(test, events.get);
//
//            }
//
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        EventStrategy artistStrategy = new ArtistStrategy();
//        //PresaleOutputBoundary presaleOutputBoundary = new PresaleOutputBoundary();
//
//        PresaleInputBoundary interactor = new PresaleInteractor(userRepository, artistStrategy, successPresenter, new UserModelFactory());
//        interactor.execute(inputData);
//    }
//}