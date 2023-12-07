package interface_adapter.notify_user_tour;

import java.util.LinkedHashMap;

/**
 * Represents the state of the notification functionality, including user input and notification results.
 */
public class NotifyState {

    /**
     * The user's favorite artist for notification.
     */
    private String favouriteArtist = "";

    /**
     * A map containing information about artists on tour, where the key is the artist name and the value
     * indicates whether the artist is on tour or not.
     */
    private LinkedHashMap<String, String> artistOnTour = new LinkedHashMap<>();

    /**
     * An error message indicating the reason for failure during the notification process.
     */
    private String artistOnTourError = null;

    /**
     * Constructs a new NotifyState by copying the values from another NotifyState instance.
     *
     * @param copy The NotifyState instance to copy values from.
     */
    public NotifyState(NotifyState copy){
        favouriteArtist = copy.favouriteArtist;
        artistOnTour = copy.artistOnTour;
        artistOnTourError = copy.artistOnTourError;
    }

    /**
     * Constructs a new NotifyState with default values.
     */
    public NotifyState(){

    }

    /**
     * Gets the user's favorite artist for notification.
     *
     * @return The user's favorite artist.
     */
    public String getFavouriteArtist(){
        return favouriteArtist;
    }

    /**
     * Sets the user's favorite artist for notification.
     *
     * @param favouriteArtist The user's favorite artist.
     */
    public void setFavouriteArtist(String favouriteArtist){
        this.favouriteArtist = favouriteArtist;
    }

    /**
     * Gets the error message indicating the reason for failure during the notification process.
     *
     * @return The error message for notification failure.
     */
    public String getArtistOnTourError(){
        return artistOnTourError;
    }

    /**
     * Sets the error message indicating the reason for failure during the notification process.
     *
     * @param artistOnTourError The error message for notification failure.
     */
    public void setArtistOnTourError(String artistOnTourError){
        this.artistOnTourError = artistOnTourError;
    }

}