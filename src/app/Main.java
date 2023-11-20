package app;

import data_access.FileUserDataAccessObject;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import org.json.JSONObject;
import view.UpcomingView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {
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
        userDataAccessObject = new FileUserDataAccessObject("GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP");

//        try {
//            System.out.println("\n");
//            int radius = 10;
//            String unit = "miles";
//
//            // Example: Get only music events based on geoPoint without specifying radius and unit
//            List<JSONObject> eventL = userDataAccessObject.getEventsFromLatLong(radius, unit, "music");
//            userDataAccessObject.printEventUrls(eventL);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, userDataAccessObject);
        views.add(upcomingShowsView, upcomingShowsView.viewName);

        viewManagerModel.setActiveView(upcomingShowsView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
