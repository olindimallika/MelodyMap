package use_case.notify_user_tour;

import javax.swing.*;

public class NotifyOutputData {
    private final String favouriteArtistConcerts;
    private final String concertLink;
    public NotifyOutputData(String favouriteArtistConcerts, String concertLink){
        this.favouriteArtistConcerts = favouriteArtistConcerts;
        this.concertLink = concertLink;
    }

    public String getFavouriteArtistConcerts(){
        return favouriteArtistConcerts;
    }

    public String getConcertLink(){
        return concertLink;
    }

}
