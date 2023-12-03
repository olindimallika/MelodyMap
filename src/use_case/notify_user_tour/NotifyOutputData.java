package use_case.notify_user_tour;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NotifyOutputData {
    private final LinkedHashMap<String, String> favouriteArtists;
    public NotifyOutputData(LinkedHashMap<String, String> favouriteArtists){
        this.favouriteArtists = favouriteArtists;
    }

    public LinkedHashMap<String, String> getFavouriteArtists(){
        return favouriteArtists;
    }

}
