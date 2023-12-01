package use_case.presale_date;

import entity.Artist;
import entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public interface PresaleDataAccess {

    List<Double> getLatLong(String postalCode);

    List<List<JSONObject>> artistEvents(String postalCode, ArrayList<Artist> favArtists) throws IOException;


}
