package interactor;

import data_access.ArtistStrategy;
import data_access.InMemoryUserDataAccessObject;
import entity.UserModelFactory;
import org.junit.jupiter.api.Test;
import use_case.EventStrategy;
import use_case.presale_date.*;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PresaleTest {
    @Test
    void successTest() throws IOException {
        PresaleInputData inputData = new PresaleInputData("M5V3L9","Olivia Rodrigo");
        PresaleDataAccess userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        PresaleOutputBoundary successPresenter = new PresaleOutputBoundary() {
            @Override
            public void prepareSuccessView(PresaleOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
//                LinkedHashMap<String, String> hasTourMap = new LinkedHashMap<>();
//                hasTourMap.put("Laufey", "has a tour");
//                hasTourMap.put("Taylor Swift", "has a tour");
//                hasTourMap.put("Olivia Rodrigo", "has a tour");
//                hasTourMap.put("Niki", "doesn't have a tour");
//                hasTourMap.put("Lizzy Mcalpine", "doesn't have a tour");
                String test = "Olivia Rodrigo - GUTS world tour\n" +
                        "Event URL: https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-29-2024/event/10005F2839E1667D\n" +
                        "Presale Status: No presale date available. Click to see if theres tix available\n" +
                        "\n" +
                        "Olivia Rodrigo - GUTS world tour\n" +
                        "Event URL: https://www.ticketmaster.ca/olivia-rodrigo-guts-world-tour-toronto-ontario-03-30-2024/event/10005F2E0D0460DE\n" +
                        "Presale Status: No presale date available. Click to see if theres tix available\n" +
                        "\n";
                //System.out.println(user.getFormatOutputPresale());
                assertEquals(test, user.getFormatOutputPresale());

            }


            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        EventStrategy artistStrategy = new ArtistStrategy();
        //PresaleOutputBoundary presaleOutputBoundary = new PresaleOutputBoundary();

        PresaleInputBoundary interactor = new PresaleInteractor(userRepository, artistStrategy, successPresenter, new UserModelFactory());
        interactor.execute(inputData);
    }
}