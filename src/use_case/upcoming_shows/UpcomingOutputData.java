package use_case.upcoming_shows;

import java.util.HashMap;

public class UpcomingOutputData {

    private final String upcomingConcerts;

    public UpcomingOutputData(String upcomingConcerts){
        this.upcomingConcerts = upcomingConcerts;
    }

    public String getUpcomingConcerts() {
        return upcomingConcerts;
    }
}
