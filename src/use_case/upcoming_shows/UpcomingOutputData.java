package use_case.upcoming_shows;

import java.util.HashMap;

public class UpcomingOutputData {

    private final HashMap<String, String> upcomingConcerts;

    public UpcomingOutputData(HashMap<String, String> upcomingConcerts){
        this.upcomingConcerts = upcomingConcerts;
    }

    public HashMap<String, String> getUpcomingConcerts() {
        return upcomingConcerts;
    }
}
