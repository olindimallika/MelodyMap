package interactor;

import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import use_case.similar_artist_venue.*;

public class SimilarArtistTest {
    @Test
    void successTest() throws Exception {
        SimilarInputData similarInput = new SimilarInputData("M8V1B7", "Taylor Swift, Laufey, Niki");
        SimilarDataAccess userRepository = new InMemoryUserDataAccessObject();

        SimilarOutputBoundary successPresenter = new SimilarOutputBoundary() {
            public void prepareSuccessView(SimilarOutputData user) {
                String outputSimilarArtists = "Similar artist for Laufey: Matt Maltese https://www.ticketmaster.ca/matt-maltese-touring-just-to-tour-toronto-ontario-03-27-2024/event/10005F36A74D2478\n" +
                        "Similar artist for Laufey: Ricky Montgomery https://www.ticketmaster.ca/ricky-montgomery-the-rick-tour-another-toronto-ontario-03-01-2024/event/10005F2A0D7B5DF8\n" +
                        "Similar artist for Laufey: PinkPantheress https://www.ticketmaster.ca/pinkpantheress-capable-of-love-tour-toronto-ontario-04-07-2024/event/10005F69D0F62EF5\n" +
                        "Similar artist for Taylor-Swift: Olivia Rodrigo https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-29-2024/event/10005F2839E1667D\n" +
                        "Similar artist for Niki: Doja Cat https://www.ticketmaster.ca/doja-cat-the-scarlet-tour-toronto-ontario-12-11-2023/event/10005ED4D89C2AB2\n" +
                        "Similar artist for Niki: Ice Spice https://www.ticketmaster.ca/doja-cat-the-scarlet-tour-toronto-ontario-12-11-2023/event/10005ED4D89C2AB2\n";
                assertEquals(outputSimilarArtists, user.getFormatOutputSimilarArtist());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is expected");
            }
        };

            SimilarInputBoundary interactor = new SimilarInteractor(userRepository, successPresenter, new UserModelFactory());
            interactor.execute(similarInput);

        }

//        @Test
//        void failureSimilarArtistsMismatch() {
//            SimilarInputData inputData = new SimilarInputData("M8V1B7", "Taylor Swift, Laufey, Niki");
//            SimilarDataAccess userRepository = new InMemoryUserDataAccessObject();
//
//            SimilarOutputBoundary failurePresenter = new SimilarOutputBoundary() {
//                @Override
//                public void prepareSuccessView(SimilarOutputData user) {
//                    fail("Use case success is unexpected");
//                }
//
//                @Override
//                public void prepareFailView(String errorMessage) {
//                    assertEquals("")
//                }
//            };
//        }
    }





