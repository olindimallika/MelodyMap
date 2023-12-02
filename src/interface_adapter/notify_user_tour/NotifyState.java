package interface_adapter.notify_user_tour;

import javax.swing.*;
import java.util.ArrayList;

public class NotifyState {

    //user input
    private String favouriteArtist = "";
    private ArrayList<String> artistOnTour = new ArrayList<>();
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

    public ArrayList<String> getArtistOnTour(){
        return artistOnTour;
    }

    public String getArtistOnTourError(){
        return artistOnTourError;
    }

    public void setArtistOnTour(ArrayList<String> artistOnTour) {
        this.artistOnTour = artistOnTour;
    }

    public void setArtistOnTourError(String artistOnTourError){
        this.artistOnTourError = artistOnTourError;
    }

}
