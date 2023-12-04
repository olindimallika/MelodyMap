package interface_adapter.artist_venue;

import interface_adapter.show_concerts.ShowConcertsState;

import java.util.LinkedHashMap;
import java.util.List;

public class ArtistState {

    private LinkedHashMap<String, List<String>> artistShows = new LinkedHashMap<>();

    private String onTourOrNotError = null;

    public ArtistState(ArtistState copy){
        artistShows = copy.artistShows;
        onTourOrNotError = copy.onTourOrNotError; // when none of their favourite artists have shows
    }

    public ArtistState() {

    }

    public LinkedHashMap<String, List<String>> getArtistShows(){

        return artistShows;
    }

    public void setArtistShows(LinkedHashMap<String, List<String>> artistShows){

        this.artistShows = artistShows;
    }

    public void setArtistShowsError(String onTourOrNotError){

        this.onTourOrNotError = onTourOrNotError;
    }
}

