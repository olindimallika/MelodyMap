package use_case.presale_date;

import data_access.FileUserDataAccessObject;
import entity.ArtistModelFactory;
import entity.User;
import entity.UserFactory;
import entity.ArtistFactory;
import interface_adapter.upcoming_shows.UpcomingPresenter;
import org.json.JSONObject;
import use_case.artist_venue.ArtistVenueDataAccess;
import use_case.artist_venue.ArtistVenueOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PresaleInteractor {

    final PresaleDataAccess presaleDataAccessObject;

    final PresaleOutputBoundary presalePresenter;


    public PresaleInteractor(PresaleDataAccess presaleDataAccess, PresaleOutputBoundary presalePresenter) {
        this.presaleDataAccessObject = presaleDataAccess;
        this.presalePresenter = presalePresenter;
    }


    //ADD TEST FOR INTERACTOR

}
