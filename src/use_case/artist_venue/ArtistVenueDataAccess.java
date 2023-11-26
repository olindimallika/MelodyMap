package use_case.artist_venue;

import entity.TestArtist;
import entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ArtistVenueDataAccess {

    List<Double> getLatLong(String postalCode);

    List<List<JSONObject>> artistEvents(String postalCode, ArrayList<TestArtist> favArtists) throws IOException;


}
