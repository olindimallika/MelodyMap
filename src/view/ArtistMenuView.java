package view;

import interface_adapter.artist_menu_tour.ArtistMenuController;
import interface_adapter.artist_menu_tour.ArtistMenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ArtistMenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Artist Menu";

    private final ArtistMenuViewModel artistMenuViewModel;
    private final ArtistMenuController artistMenuController;

    public ArtistMenuView(ArtistMenuController artistMenuController, ArtistMenuViewModel artistMenuViewModel) {
        this.artistMenuController = artistMenuController;
        this.artistMenuViewModel = artistMenuViewModel;
        artistMenuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ArtistMenuViewModel.TITLE_LABEL);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel buttonLabel1 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL1);
        JLabel buttonLabel2 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL2);

        buttonLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonLabel2.setFont(new Font("Arial", Font.PLAIN, 15));

        ArrayList<String> noTour = artistMenuViewModel.getState().getArtistNotOnTour();
        String[] noTourArray = noTour.toArray(new String[0]);

        ArrayList<String> onTour = artistMenuViewModel.getState().getArtistOnTour();
        String[] onTourArray = onTour.toArray(new String[0]);
        JPanel notOnTourPanel = createArtistPanel("Artists Not On Tour", noTourArray);
        JPanel onTourPanel = createArtistPanel("Artists On Tour", onTourArray);

        add(title);
        add(buttonLabel1);
        add(notOnTourPanel);
        add(buttonLabel2);
        add(onTourPanel);
    }

    private JPanel createArtistPanel(String panelTitle, String[] artists) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        // Add a label for the panel title
        panel.add(new JLabel(panelTitle));

        // Create buttons for each artist
        for (String artist : artists) {
            JButton artistButton = new JButton(artist);
            artistButton.addActionListener(this);
            panel.add(artistButton);
        }

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String selectedArtist = clickedButton.getText();
        artistMenuViewModel.setSelectedArtist(selectedArtist);
        artistMenuController.execute();
        System.out.println("Button Clicked for artist: " + selectedArtist);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("selectedArtist".equals(evt.getPropertyName())) {
            // React to the change in the selected artist
            System.out.println("x");
        }
    }

}



//package view;
//
//import interface_adapter.artist_menu_tour.ArtistMenuController;
//import interface_adapter.artist_menu_tour.ArtistMenuViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeListener;
//
//public class ArtistMenuView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    public final String viewName = "Artist Menu";
//
//    private final ArtistMenuViewModel artistMenuViewModel;
//    private final ArtistMenuController artistMenuController;
//
//    public ArtistMenuView(ArtistMenuController artistMenuController, ArtistMenuViewModel artistMenuViewModel) {
//        this.artistMenuController = artistMenuController;
//        this.artistMenuViewModel = artistMenuViewModel;
//        artistMenuViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel(ArtistMenuViewModel.TITLE_LABEL);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        title.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        JLabel buttonLabel1 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL1);
//        JLabel buttonLabel2 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL2);
//
//        buttonLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
//        buttonLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        JPanel notOnTourPanel = createArtistPanel("Artists Not On Tour", artistMenuViewModel.getState().getArtistNotOnTour());
//        JPanel onTourPanel = createArtistPanel("Artists On Tour", artistMenuViewModel.getState().getArtistOnTour());
//
//        add(title);
//        add(buttonLabel1);
//        add(notOnTourPanel);
//        add(buttonLabel2);
//        add(onTourPanel);
//    }
//
//    private JPanel createArtistPanel(String panelTitle, String[] artists) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(0, 1));
//
//        // Add a label for the panel title
//        panel.add(new JLabel(panelTitle));
//
//        // Create buttons for each artist
//        for (String artist : artists) {
//            JButton artistButton = new JButton(artist);
//            artistButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String selectedArtist = artistButton.getText();
//                    artistMenuController.execute();
//                    System.out.println("Button Clicked for artist: " + selectedArtist);
//                }
//            });
//            panel.add(artistButton);
//        }
//
//        return panel;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // Handle actions if needed
//    }
//
//    @Override
//    public void propertyChange(java.beans.PropertyChangeEvent evt) {
//        // Handle property changes if needed
//    }
//}



//package view;
//
//import interface_adapter.artist_menu_tour.ArtistMenuController;
//import interface_adapter.artist_menu_tour.ArtistMenuState;
//import interface_adapter.artist_menu_tour.ArtistMenuViewModel;
//import interface_adapter.upcoming_shows.UpcomingState;
//import interface_adapter.upcoming_shows.UpcomingViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeListener;
//
//public class ArtistMenuView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    public final String viewName = "Artist Menu";
//
//    private final ArtistMenuViewModel artistMenuViewModel;
//
//    private final ArtistMenuController artistMenuController;
//
//    public ArtistMenuView(ArtistMenuController artistMenuController, ArtistMenuViewModel artistMenuViewModel) {
//        this.artistMenuController = artistMenuController;
//        this.artistMenuViewModel = artistMenuViewModel;
//        artistMenuViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel(ArtistMenuViewModel.TITLE_LABEL);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        title.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        JLabel button_label1 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL1);
//        JLabel button_label2 = new JLabel(ArtistMenuViewModel.BUTTON_LABEL2);
//
//        button_label1.setFont(new Font("Arial", Font.PLAIN, 15));
//        button_label2.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        for (String artist : artistMenuViewModel.getState().getArtistNotOnTour()) {
//            JButton artistButton = new JButton(artist);
//            add(artistButton);
//            artistButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String selectedArtist = artistButton.getText();
//                    artistMenuController.execute();
//                    System.out.println("Button Clicked for artist: " + artist);
//                }
//            });
//        }
//
//        for (String artist : artistMenuViewModel.getState().getArtistOnTour()) {
//            JButton artistButton = new JButton(artist);
//            add(artistButton);
//            artistButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String selectedArtist = artistButton.getText();
//                    artistMenuController.execute();
//                    System.out.println("Button Clicked for artist: " + artist);
//                }
//            });
//        }
//    }