package interface_adapter.notify_user_tour;

import javax.swing.*;

public class NotifyState {
    private String favouriteArtist = "";
    private String favouriteArtistError = null;
    private String favouriteArtistUpcoming = "";
    private String concertLink = "";
    private String concertLinkError = null;

    public NotifyState(NotifyState copy){
        favouriteArtist = copy.favouriteArtist;
        favouriteArtistError = copy.favouriteArtistError;
        concertLink = copy.concertLink;
        concertLinkError = copy.concertLinkError;
    }

    public NotifyState(){

    }

    public String getFavouriteArtist(){
        return favouriteArtist;
    }

    public String getFavouriteArtistError(){
        return favouriteArtistError;
    }

    public void setFavouriteArtist(String favouriteArtist) {
        this.favouriteArtist = favouriteArtist;
    }

    public String getFavouriteArtistUpcoming(){
        return favouriteArtistUpcoming;
    }

    public void setFavouriteArtistUpcoming(String favouriteArtistUpcoming){
        this.favouriteArtistUpcoming = favouriteArtistUpcoming;
    }

    public void setFavouriteArtistUpcomingError(String favouriteArtistError){
        this.favouriteArtistError = favouriteArtistError;
    }

    public String getConcertLink(){
        return concertLink;
    }

    public void setConcertLink(String concertLink){
        this.concertLink = concertLink;
    }

    public void setConcertLinkError(String concertLinkError){
        this.concertLinkError = concertLinkError;
    }

}
