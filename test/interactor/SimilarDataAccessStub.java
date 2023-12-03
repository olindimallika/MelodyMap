package interactor;

import entity.User;
import org.json.JSONObject;
import use_case.similar_artist_venue.SimilarOutputBoundary;
import use_case.similar_artist_venue.SimilarDataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimilarDataAccessStub implements SimilarDataAccess {
    @Override
    public HashMap<String, ArrayList<String>> getSimilarArtists(ArrayList<String> favouriteArtists) {
        // For testing, just return some dummy data
        HashMap<String, ArrayList<String>> similarArtists = new HashMap<>();
        similarArtists.put("Artist1", new ArrayList<>());
        similarArtists.put("Artist2", new ArrayList<>());
        return similarArtists;
    }

    @Override
    public HashMap<String, ArrayList<String>> getSimilarArtists(List<String> favouriteArtists) {
        return null;
    }

    @Override
    public String getEventUrl(JSONObject event) {
        return null;
    }

    @Override
    public String getArtistName(JSONObject event) {
        return null;
    }

    @Override
    public List<Double> locationFinder(User user) {
        return null;
    }
}


