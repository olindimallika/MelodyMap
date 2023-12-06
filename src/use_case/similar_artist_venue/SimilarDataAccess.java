package use_case.similar_artist_venue;

import entity.Artist;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SimilarDataAccess {
    HashMap<String, List<String>> getSimilarArtists(List<String> similarArtists);
    List<JSONObject> findEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification,
                                           String artistName) throws IOException, InterruptedException;

}