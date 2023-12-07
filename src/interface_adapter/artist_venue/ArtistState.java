package interface_adapter.artist_venue;

import java.util.LinkedHashMap;
import java.util.List;

//
public class ArtistState {
    private LinkedHashMap<String, String> artistsOnTour = new LinkedHashMap<>();

    private LinkedHashMap<String, List<String>> artistShows = new LinkedHashMap<>();

    private String artistsOnTourError = null;
    private String artistShowsError = null;

    public ArtistState(ArtistState copy){
        artistsOnTour = copy.artistsOnTour;
        artistsOnTourError = copy.artistsOnTourError;
        artistShows = copy.artistShows;
        artistShowsError = copy.artistShowsError;
    }

    public ArtistState() {

    }

    public void setArtistsOnTour(LinkedHashMap<String, String> artistsOnTour){
        this.artistsOnTour = artistsOnTour;
    }

    public LinkedHashMap<String, String> getArtistsOnTour(){
        return artistsOnTour;
    }

    public String getArtistsOnTourError(){
        return artistsOnTourError;
    }


    public LinkedHashMap<String, List<String>> getArtistShows(){
        return artistShows;
    }

    public String getArtistShowsError(){
        return artistShowsError;
    }

    public void setArtistShows(LinkedHashMap<String, List<String>> artistShows){

        this.artistShows = artistShows;
    }

    public void setArtistShowsError(String artistShowsError){

        this.artistShowsError = artistShowsError;
    }

}
