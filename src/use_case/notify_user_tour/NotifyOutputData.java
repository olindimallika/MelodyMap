package use_case.notify_user_tour;

import javax.swing.*;
import java.util.ArrayList;

public class NotifyOutputData {
    private final ArrayList<String> artistOnTour;
    public NotifyOutputData(ArrayList<String> artistOnTour){
        this.artistOnTour = artistOnTour;
    }

    public ArrayList<String> getArtistOnTour(){
        return artistOnTour;
    }

}
