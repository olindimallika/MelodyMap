package use_case.presale_date;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the output data for the Presale use case.
 */
public class PresaleOutputData {
    // Member variables
    private String finalFormatOutputPresale = "";
    private List<String> presaleDates = new ArrayList<>();
    private List<String> eventUrls = new ArrayList<>();

    /**
     * Constructs a PresaleOutputData object with the final format of presale output.
     *
     * @param finalFormatOutputPresale The formatted presale output string.
     */
    public PresaleOutputData(String finalFormatOutputPresale) {
        this.finalFormatOutputPresale = finalFormatOutputPresale;
    }

    /**
     * Gets the final formatted presale output.
     *
     * @return The final formatted presale output string.
     */
    public String getFormatOutputPresale() {
        return finalFormatOutputPresale;
    }

    /**
     * Gets the list of event URLs from the PresaleOutputData object.
     *
     * @return The list of event URLs.
     */
    public List<String> getEventUrls() {
        return eventUrls;
    }

    /**
     * Gets the list of presale dates from the PresaleOutputData object.
     *
     * @return The list of presale dates.
     */
    public List<String> getPresaleDates() {
        return presaleDates;
    }
}
