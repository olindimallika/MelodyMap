package use_case.artist_venue;

import entity.Artist;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ArtistVenueInteractor implements ArtistVenueInputBoundary {
    final ArtistVenueDataAccess artistVenueDataAccessObject;

    final ArtistVenueOutputBoundary artistPresenter;

    public ArtistVenueInteractor(ArtistVenueDataAccess artistVenueDataAccess, ArtistVenueOutputBoundary artistPresenter) {
        this.artistVenueDataAccessObject = artistVenueDataAccess;
        this.artistPresenter = artistPresenter;
    }

    @Override
    public void execute(ArtistVenueInputData artistVenueInputData) throws IOException {
        String postalCode = artistVenueInputData.getPostalCode();
        ArrayList<Artist> favouriteArtists = artistVenueInputData.getFavouriteArtist();
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(0.0);


        if (artistVenueDataAccessObject.getLatLong(postalCode) == coordinates) {
            artistPresenter.prepareFailView("This is an invalid Canadian Postal Code. Please try again.");
        } else {
            if (artistVenueDataAccessObject.artistEvents(postalCode, favouriteArtists).isEmpty()) {
                artistPresenter.prepareFailView("Sorry none of your favourite artists have shows playing near you.");
            } else { //There are upcoming events
//                for (TestArtist artist : favouriteArtists) {
//                    String artistName = artist.getName();
//                iterate through artistVenueDataAccessObject.artistEvents(postalCode, favouriteArtists)
                // get artist name, and get their events create hashmap with artist name as key, value is an array list of events
                ArtistVenueOutputData artistVenuesData = new ArtistVenueOutputData();

                // Adding events for artists
                //artistVenuesData.addEvent("Artist1", new ArtistVenueOutputData.Event("Event1"));
                // ArtistVenueOutputData artistVenueOutputData = new ArtistVenueOutputData(new);
                //THIS ISNT CORRECT
                }
            }
        }
    }



