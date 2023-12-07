package use_case.upcoming_shows;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class UpcomingOutputData {

    private final LinkedHashMap<String, String> upcomingConcerts;

    public UpcomingOutputData(LinkedHashMap<String, String> upcomingConcerts){
        this.upcomingConcerts = upcomingConcerts;
    }

    public LinkedHashMap<String, String> getUpcomingConcerts() {
        return upcomingConcerts;
    }
}
