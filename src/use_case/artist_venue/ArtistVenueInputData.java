package use_case.artist_venue;

import java.util.LinkedHashMap;

//
public class ArtistVenueInputData {
    final private LinkedHashMap<String, String> artistTours;

    public ArtistVenueInputData(LinkedHashMap<String, String> artistTours){
        this.artistTours = artistTours;
    }

    LinkedHashMap<String, String> getArtistTours(){
        return artistTours;
    }
}







