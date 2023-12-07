package view;

import interface_adapter.artist_venue.ArtistController;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

//
public class ArtistVenueView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "show artist concerts";
    private final ArtistViewModel artistViewModel;
    private final ArtistController artistController;

    final JButton reload;

    final JButton back;


    /**
     * A window with artists and their concert links.
     */
    public ArtistVenueView(ArtistController artistController, ArtistViewModel artistViewModel) {
        this.artistController = artistController;
        this.artistViewModel = artistViewModel;
        this.artistViewModel.addPropertyChangeListener(this);
        //this.artistViewModel.addPropertyChangeListener(this::artistPropertyChange);

        JLabel title = new JLabel("Artist Venues for your favourite artists");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        reload = new JButton(artistViewModel.PERSONALIZE_BUTTON_LABEL1);
        buttons.add(reload);

        back = new JButton(artistViewModel.PERSONALIZE_BUTTON_LABEL2);
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            ArtistState currentState = artistViewModel.getState();

                            try {
                                artistController.execute(currentState.getArtistsOnTour());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArtistState state = (ArtistState) evt.getNewValue();
        if (state.getArtistShowsError() != null) {
            JOptionPane.showMessageDialog(this, state.getArtistShowsError());
        }
//        LinkedHashMap<String, List<String>> shows = state.getArtistShows();
//        ArrayList<String> artists = new ArrayList<>(5);
//        ArrayList<List<String>> artistShows = new ArrayList<>(5);
//
//        for (Map.Entry<String, List<String>> entry : shows.entrySet()) {
//            String key = entry.getKey();
//            artists.add(key);
//
//            List<String> value = entry.getValue();
//            artistShows.add(value);
//        }
//
//        // CONCERT 1
//        hyperlink.setText(artists.get(0) + ": " + artistShows.get(0));
//
//        // CONCERT 2
//        hyperlink2.setText(artists.get(1) + ": " + artistShows.get(1));
//
//        // CONCERT 3
//        hyperlink3.setText(artists.get(2) + ": " + artistShows.get(2));
//
//        // CONCERT 4
//        hyperlink4.setText(artists.get(3) + ": " + artistShows.get(3));
//
//        // CONCERT 5
//        hyperlink5.setText(artists.get(4) + ": " + artistShows.get(4));
//
//    }
//
//    public void artistPropertyChange(PropertyChangeEvent evt){
//        ArtistState state = (ArtistState) evt.getNewValue();
//
//        LinkedHashMap<String, List<String>> shows = state.getArtistShows();
////        ArrayList<String> concerts = new ArrayList<>(5);
////        ArrayList<String> concertLinks = new ArrayList<>(5);
////
////        for (Map.Entry<String, String> entry : shows.entrySet()) {
////            String key = entry.getKey();
////            concerts.add(key);
////
////            String value = entry.getValue();
////            concertLinks.add(value);
////        }
////
////        JPanel panel = new JPanel();
////
////        JLabel hyperlink = new JLabel();
////        JLabel hyperlink2 = new JLabel();
////        JLabel hyperlink3 = new JLabel();
////
////        // CONCERT 1
////        hyperlink.setText(concerts.get(0) + ": " + concertLinks.get(0));
////
////        // CONCERT 2
////        hyperlink2.setText(concerts.get(1) + ": " + concertLinks.get(1));
////
////        // CONCERT 3
////        hyperlink3.setText(concerts.get(2) + ": " + concertLinks.get(2));
////
////        panel.add(hyperlink);
////        panel.add(hyperlink2);
////        panel.add(hyperlink3);
//
//        JOptionPane.showMessageDialog(this, shows);
//
//    }
    }


//    public void artistPropertyChange(PropertyChangeEvent evt){
//        ArtistState state = (ArtistState) evt.getNewValue();
//        JOptionPane.showMessageDialog(this, state.getArtistShows());
//    }


}


