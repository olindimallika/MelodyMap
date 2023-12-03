package interface_adapter.artist_menu_tour;

import java.util.ArrayList;

public class ArtistMenuState {


    private String favouriteArtist = "";
    private ArrayList<String> artistOnTour = new ArrayList<>();

    private ArrayList<String> artistNotOnTour = new ArrayList<>();

    private String artistMenuError = null;

    public ArtistMenuState(ArtistMenuState copy) {
        favouriteArtist = copy.favouriteArtist;
        artistOnTour = new ArrayList<>();
        artistNotOnTour = new ArrayList<>();
        artistMenuError = null;
    }
    //LIBKED STRING TO STRING METHID GET ARTIST ONTOUR, CALL METHOD IN VIEW

    public ArtistMenuState(){

    }

    public String getFavouriteArtist() {
        return favouriteArtist;
    }

    public void setFavouriteArtist(String favouriteArtist){

        this.favouriteArtist = favouriteArtist;
    }

    public ArrayList<String> getArtistOnTour(){

        return artistOnTour;
    }

    public void setArtistOnTour(ArrayList<String> artistOnTour) {

        this.artistOnTour = artistOnTour;
    }

    public ArrayList<String> getArtistNotOnTour(){
        return artistNotOnTour;
    }

    public void setArtistNotOnTour(ArrayList<String> artistNotOnTour) {
        this.artistNotOnTour = artistNotOnTour;
    }

    public String getArtistMenuError(){
        return artistMenuError;
    }

    public void setArtistMenuError(String artistMenuError){
        this.artistMenuError = artistMenuError;
    }
}
