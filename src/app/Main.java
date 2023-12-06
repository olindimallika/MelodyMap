package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.similar_artist.SimilarArtistViewModel;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {

            JFrame application = new JFrame("Melody Map");
            application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            CardLayout cardLayout = new CardLayout();

            JPanel views = new JPanel(cardLayout);
            application.add(views);

            // manages which view is currently being shown to user
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            new ViewManager(views, cardLayout, viewManagerModel);

            UpcomingViewModel upcomingShowsViewModel = new UpcomingViewModel();
            ShowConcertsViewModel showConcertsViewModel = new ShowConcertsViewModel();
            NotifyViewModel notifyViewModel = new NotifyViewModel();
            SimilarArtistViewModel similarViewModel = new SimilarArtistViewModel();

            FileUserDataAccessObject userDataAccessObject;
            userDataAccessObject = new FileUserDataAccessObject();

            UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, showConcertsViewModel, userDataAccessObject);
            views.add(upcomingShowsView, upcomingShowsView.viewName);

            ShowConcertsView showConcertsView = ShowConcertsUseCaseFactory.create(viewManagerModel, showConcertsViewModel, notifyViewModel, userDataAccessObject);
            views.add(showConcertsView, showConcertsView.viewName);

            NotifyView notifyView = NotifyUseCaseFactory.create(viewManagerModel, notifyViewModel, userDataAccessObject);
            views.add(notifyView, notifyView.viewName);

            viewManagerModel.setActiveView(upcomingShowsView.viewName);
            viewManagerModel.firePropertyChanged();

            SimilarView similarView = SimilarArtistUseCaseFactory.create(viewManagerModel, similarViewModel, userDataAccessObject);
            views.add(similarView, similarView.viewName);

            viewManagerModel.setActiveView(similarView.viewName);
            viewManagerModel.firePropertyChanged();

            application.pack();
            application.setVisible(true);
        }
}