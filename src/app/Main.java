package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import view.UpcomingView;
import view.ViewManager;
import entity.ArtistModelFactory;

import javax.swing.*;
import java.awt.*;


public class Main{
    public static void main(String[] args) {

        JFrame application = new JFrame("Melody Map");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        UpcomingViewModel upcomingShowsViewModel = new UpcomingViewModel();

        FileUserDataAccessObject userDataAccessObject;
        userDataAccessObject = new FileUserDataAccessObject(new ArtistModelFactory());

        UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, userDataAccessObject);
        views.add(upcomingShowsView, upcomingShowsView.viewName);

        viewManagerModel.setActiveView(upcomingShowsView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
