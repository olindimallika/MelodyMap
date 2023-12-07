package interface_adapter.artist_venue;

import java.util.LinkedHashMap;
//
public class ArtistState {

    private LinkedHashMap<String, String> artistShows = new LinkedHashMap<>();

    private String onTourOrNotError = null;

    public ArtistState(ArtistState copy){
        artistShows = copy.artistShows;
        onTourOrNotError = copy.onTourOrNotError; // when none of their favourite artists have shows
    }

    public ArtistState() {

    }

    public LinkedHashMap<String, String> getArtistShows(){

        return artistShows;
    }

    public String getArtistShowsError(){
        return onTourOrNotError;
    }

    public void setArtistShows(LinkedHashMap<String, String> artistShows){

        this.artistShows = artistShows;
    }

    public void setArtistShowsError(String onTourOrNotError){

        this.onTourOrNotError = onTourOrNotError;
    }
}