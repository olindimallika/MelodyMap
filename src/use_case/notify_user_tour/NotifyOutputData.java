package use_case.notify_user_tour;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NotifyOutputData {
    private final LinkedHashMap<String, String> artistOnTour;
    public NotifyOutputData(LinkedHashMap<String, String> artistOnTour){
        this.artistOnTour = artistOnTour;
    }

    public LinkedHashMap<String, String> getArtistOnTour(){
        return artistOnTour;
    }

}
