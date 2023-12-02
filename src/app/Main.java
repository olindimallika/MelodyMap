package app;

import data_access.FileUserDataAccessObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import view.NotifyView;
import view.ShowConcertsView;
import view.UpcomingView;
import view.ViewManager;

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

        FileUserDataAccessObject userDataAccessObject;
        userDataAccessObject = new FileUserDataAccessObject();

        UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, showConcertsViewModel, userDataAccessObject);
        views.add(upcomingShowsView, upcomingShowsView.viewName);

        ShowConcertsView showConcertsView = ShowConcertsUseCaseFactory.create(viewManagerModel, notifyViewModel, userDataAccessObject, showConcertsViewModel);
        views.add(showConcertsView, showConcertsView.viewName);

        NotifyView notifyView = NotifyUseCaseFactory.create(viewManagerModel, notifyViewModel, userDataAccessObject);
        views.add(notifyView,notifyView.viewName);

        viewManagerModel.setActiveView(upcomingShowsView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
