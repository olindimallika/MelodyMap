package use_case.presale_date;

import entity.User;
import org.json.JSONObject;
import java.util.List;

public interface PresaleDataAccess {
    List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws Exception;

    //List<String> getEventUrls();
    List<String> getPresaleDates();

    void addEventInfo(JSONObject event);

    boolean isPastPresale(String presaleEndDate);

    List<String> getEventUrls();

    String getArtistName(JSONObject event);

    String formatOutputPresale(String artName, String artUrl, String artPresale);

    String getFormatOutputPresale();

}
