package data_access;

import entity.TestArtist;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



class InMemoryUserDAOTest {

    @Test
    void getLatLong() {
        InMemoryUserDAO userDAO = new InMemoryUserDAO();
        TestArtist artist = new TestArtist("Taylor Swift", "Pop", "1989");
        ArrayList<TestArtist> favArtists = new ArrayList<>();
        favArtists.add(artist);
//        User user = new TestUser("Brittany", favArtists, "L1C0K1");
        String postalCode = "L1C0K1";
        /* create a User object for testing */;
      //Assuming the expected latitude and longitude values
        double expectedLatitude = 43.926850;  // Replace with your expected latitude
        double expectedLongitude = -78.685050;  // Replace with your expected longitude

        List<Double> coordinates = userDAO.getLatLong(postalCode);

        // Assuming the getLatLong method returns a list with latitude and longitude
        Assertions.assertEquals(expectedLatitude, coordinates.get(0), 0.001);
        Assertions.assertEquals(expectedLongitude, coordinates.get(1), 0.001);
    }

    @Test
    void artistEvents() throws IOException {
        InMemoryUserDAO userDAO = new InMemoryUserDAO();
        TestArtist artist1 = new TestArtist("Taylor Swift", "Pop", "1989");
        TestArtist artist2 = new TestArtist("Olivia Rodrigo", "Pop", "2000");
        ArrayList<TestArtist> favArtists = new ArrayList<>(Arrays.asList(artist1, artist2));
        String postalCode = "L1C0K1";

//        User user = new TestUser("Brittany", favArtists, "L1C0K1");

        List<List<JSONObject>> result = userDAO.artistEvents(postalCode, favArtists);

        // Assert that the result is not null and has the expected size (number of favorite artists)
        Assertions.assertNotNull(result);
        Assertions.assertEquals(favArtists.size(), result.size());

    }
}
