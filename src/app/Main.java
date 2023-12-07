package app;

import data_access.FileUserDataAccessObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import view.*;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {

        JFrame application = new JFrame("Melody Map");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/logo.png");
        application.setIconImage(icon.getImage());

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // manages which view is currently being shown to user
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        UpcomingViewModel upcomingShowsViewModel = new UpcomingViewModel();
        ShowConcertsViewModel showConcertsViewModel = new ShowConcertsViewModel();
        NotifyViewModel notifyViewModel = new NotifyViewModel();
        ArtistViewModel artistViewModel = new ArtistViewModel();
        ShowArtistViewModel showArtistViewModel = new ShowArtistViewModel();

        FileUserDataAccessObject userDataAccessObject;
        userDataAccessObject = new FileUserDataAccessObject();

        UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, showConcertsViewModel, userDataAccessObject);
        views.add(upcomingShowsView, upcomingShowsView.viewName);

        ShowConcertsView showConcertsView = ShowConcertsUseCaseFactory.create(viewManagerModel, showConcertsViewModel, notifyViewModel, userDataAccessObject);
        views.add(showConcertsView, showConcertsView.viewName);

        NotifyView notifyView = NotifyUseCaseFactory.create(viewManagerModel, notifyViewModel, artistViewModel, userDataAccessObject);
        views.add(notifyView,notifyView.viewName);

        ArtistVenueView artistVenueView = ArtistVenueUseCaseFactory.create(viewManagerModel, artistViewModel, showArtistViewModel, userDataAccessObject);
        views.add(artistVenueView, artistVenueView.viewName);

//        ShowArtistView showArtistView = ShowArtistUseCaseFactory.create(viewManagerModel, showArtistViewModel, userDataAccessObject);
//        views.add(showArtistView, showArtistView.viewName);


        viewManagerModel.setActiveView(upcomingShowsView.viewName);
        viewManagerModel.firePropertyChanged();

//        viewManagerModel.setActiveView(artistVenueView.viewName);
//        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}