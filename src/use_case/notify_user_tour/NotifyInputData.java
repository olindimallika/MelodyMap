package use_case.notify_user_tour;

public class NotifyInputData {
    final private String favouriteArtistName;

    public NotifyInputData(String favouriteArtistName){
        this.favouriteArtistName = favouriteArtistName;
    }

    String getFavouriteArtistName(){
        return favouriteArtistName;
    }
}
