package interface_adapter.notify_user_tour;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NotifyState {

    //user input
    private String favouriteArtist = "";
    private LinkedHashMap<String, String> artistOnTour = new LinkedHashMap<>();
    private String artistOnTourError = null;

    public NotifyState(NotifyState copy){
        favouriteArtist = copy.favouriteArtist;
        artistOnTour = copy.artistOnTour;
        artistOnTourError = copy.artistOnTourError;
    }

    public NotifyState(){

    }

    public String getFavouriteArtist(){
        return favouriteArtist;
    }
    public void setFavouriteArtist(String favouriteArtist){
        this.favouriteArtist = favouriteArtist;
    }

    public LinkedHashMap<String, String> getArtistOnTour(){
        return artistOnTour;
    }

    public String getArtistOnTourError(){
        return artistOnTourError;
    }

    public void setArtistOnTour(LinkedHashMap<String, String> artistOnTour) {
        this.artistOnTour = artistOnTour;
    }

    public void setArtistOnTourError(String artistOnTourError){
        this.artistOnTourError = artistOnTourError;
    }

}
