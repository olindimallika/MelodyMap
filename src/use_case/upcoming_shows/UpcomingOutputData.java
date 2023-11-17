package use_case.upcoming_shows;

import java.util.Map;
import java.util.HashMap;

public class UpcomingOutputData {

    private Map<String, String> upcomingConcerts = new HashMap<>();

    public UpcomingOutputData(HashMap<String, String> upcomingConcerts){
        this.upcomingConcerts = upcomingConcerts;
    }

    public Map<String, String> getUpcomingConcerts() {
        return upcomingConcerts;
    }
}
