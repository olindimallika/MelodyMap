package use_case.notify_user_tour;

public class NotifyInputData {
    final private String favouriteArtistNames;

    public NotifyInputData(String favouriteArtistNames){
        this.favouriteArtistNames = favouriteArtistNames;
    }

    String getFavouriteArtistNames(){
        return favouriteArtistNames;
    }
}
