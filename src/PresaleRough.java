import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PresaleRough {
    private List<String> eventUrls;
    private List<String> presaleDates;

    public PresaleRough() {
        this.eventUrls = new ArrayList<>();
        this.presaleDates = new ArrayList<>();
    }

    public void addEventInfo(JSONObject event) {
        // Extract and store the URL
        String url = event.getString("url");
        eventUrls.add(url);

        // Extract and store presale date if available
        if (event.has("sales") && event.getJSONObject("sales").has("presales")) {
            JSONArray presalesArray = event.getJSONObject("sales").getJSONArray("presales");
            for (int j = 0; j < presalesArray.length(); j++) {
                JSONObject presale = presalesArray.getJSONObject(j);
                String startPresaleDate = presale.getString("startDateTime");
                String endPresaleDate = presale.getString("startDateTime");
                String intervalPresale = startPresaleDate + " - "+endPresaleDate;
                presaleDates.add(intervalPresale);
            }
        } else {
            // If no presale date is available, add a placeholder or handle it as needed
            presaleDates.add("No presale date available");
        }
    }

    public List<String> getEventUrls() {
        return eventUrls;
    }

    public List<String> getPresaleDates() {
        return presaleDates;
    }
}
