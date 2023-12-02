//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.notify_user_tour.NotifyViewModel;
//import interface_adapter.upcoming_shows.UpcomingViewModel;
//
//import view.NotifyView;
//import view.UpcomingView;
//import view.ViewManager;
//
//import javax.swing.*;
//import java.awt.*;
//
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        JFrame application = new JFrame("Melody Map");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        // manages which view is currently being shown to user
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        UpcomingViewModel upcomingShowsViewModel = new UpcomingViewModel();
//        NotifyViewModel notifyViewModel = new NotifyViewModel();
//
//        FileUserDataAccessObject userDataAccessObject;
//        userDataAccessObject = new FileUserDataAccessObject();
//
//        UpcomingView upcomingShowsView = UpcomingUseCaseFactory.create(viewManagerModel, upcomingShowsViewModel, userDataAccessObject);
//        views.add(upcomingShowsView, upcomingShowsView.viewName);
//
//        NotifyView notifyView = NotifyUseCaseFactory.create(viewManagerModel, notifyViewModel, userDataAccessObject);
//        views.add(notifyView, notifyView.viewName);
//
////        viewManagerModel.setActiveView(upcomingShowsView.viewName);
////        viewManagerModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(notifyView.viewName);
//        viewManagerModel.firePropertyChanged();
//
//        application.pack();
//        application.setVisible(true);
//    }
//}
