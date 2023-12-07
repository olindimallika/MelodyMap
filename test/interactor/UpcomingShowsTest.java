//package interactor;
//
//import data_access.InMemoryUserDataAccessObject;
//import entity.UserModelFactory;
//
//import org.junit.jupiter.api.Test;
//import use_case.upcoming_shows.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UpcomingShowsTest {
//
//    @Test
//    void successTest() {
//        // creating the input data object
//        UpcomingInputData inputData = new UpcomingInputData("M1J3J9");
//
//        UpcomingDataAccess userRepository = new InMemoryUserDataAccessObject();
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        UpcomingOutputBoundary successPresenter = new UpcomingOutputBoundary() {
//            @Override
//            public void prepareSuccessView(UpcomingOutputData upcomingConcerts) {
//                // 2 things to check: the output data is correct, and the postal code has valid coordinates.
//                assertEquals("KESmas - Kes The Band Live in Concert: https://www.ticketmaster.ca/kesmas-kes-the-band-live-in-toronto-ontario-12-21-2023/event/10005F6AD6584CEE\n" +
//                        "Big Wreck: https://www.ticketmaster.ca/big-wreck-toronto-ontario-12-22-2023/event/10005F09E92D43C5\n" +
//                                "Sam Roberts Band - The Adventures Of Ben Blank Tour: https://www.ticketmaster.ca/sam-roberts-band-the-adventures-of-toronto-ontario-02-09-2024/event/10005F27983B20F5\n" +
//                        "+++ Crosses - Familiar World Tour: https://www.ticketmaster.ca/crosses-familiar-world-tour-toronto-ontario-02-16-2024/event/10005F4BB0A81953\n" +
//                        "The Strumbellas: https://www.ticketmaster.ca/the-strumbellas-toronto-ontario-02-24-2024/event/10005F53C4B31DC1\n", upcomingConcerts.getUpcomingConcerts());
//                assertTrue(userRepository.existsInCoords("M1J3J9"));
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        // creating the interactor
//        UpcomingInputBoundary interactor = new UpcomingInteractor(userRepository, successPresenter, new UserModelFactory());
//
//         // invoking the interactor by passing in the input data
//        interactor.execute(inputData);
//    }
//}