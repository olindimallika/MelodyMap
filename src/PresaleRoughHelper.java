import org.json.JSONArray;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresaleRoughHelper {
    private List<String> eventUrls;
    private List<String> presaleDates;

    public PresaleRoughHelper() {
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
                String endPresaleDate = presale.getString("endDateTime");

                if (isPastPresale(endPresaleDate)) {
                    presaleDates.add("You missed the presale. Go to general sale by clicking link");
                } else {
                    String intervalPresale = "Presale is happening now until "+ endPresaleDate + " click the link to go to presale.";
                    presaleDates.add(intervalPresale);
                }
            }
        } else {
            // If no presale date is available, add a placeholder or handle it as needed
            presaleDates.add("No presale date available, click to see if theres tix available");
        }
    }


    private boolean isPastPresale(String presaleEndDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date endDate = sdf.parse(presaleEndDate);
            Date currentDate = new Date();

            return currentDate.after(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parse exception as needed
            return false;
        }

    }

    public List<String> getEventUrls() {
        return eventUrls;
    }

    public List<String> getPresaleDates() {
        return presaleDates;
    }
}
