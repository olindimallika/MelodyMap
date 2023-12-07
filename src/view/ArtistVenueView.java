package view;

import interface_adapter.artist_venue.ArtistController;
import interface_adapter.artist_venue.ArtistState;
import interface_adapter.artist_venue.ArtistViewModel;
import interface_adapter.notify_user_tour.NotifyController;
import interface_adapter.notify_user_tour.NotifyState;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsController;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArtistVenueView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "artist concerts";
    private final ArtistViewModel artistViewModel;
    private final ArtistController artistController;
//
//    JLabel hyperlink;
//    JLabel hyperlink2;
//    JLabel hyperlink3;
//
//    final JButton reload;


    final JButton back;


    /**
     * A window with artists and their concert links.
     */
    public ArtistVenueView(ArtistController artistController, ArtistViewModel artistViewModel) {
        this.artistController = artistController;
        this.artistViewModel = artistViewModel;
        this.artistViewModel.addPropertyChangeListener(this);
        this.artistViewModel.addPropertyChangeListener(this::artistPropertyChange);

        this.setSize(1000, 400);

        JLabel title = new JLabel("Artist Venues for your favourite artists");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        hyperlink = new JLabel();
//        hyperlink2 = new JLabel();
//        hyperlink3 = new JLabel();

        JPanel buttons = new JPanel();

//        reload = new JButton(artistViewModel.PERSONALIZE_BUTTON_LABEL1);
//        buttons.add(reload);

        back = new JButton(artistViewModel.PERSONALIZE_BUTTON_LABEL2);
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            try {
                                artistController.execute();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
//        this.add(hyperlink);
//        this.add(hyperlink2);
//        this.add(hyperlink3);
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
        if(state.getArtistShowsError() != null){
            JOptionPane.showMessageDialog(this, state.getArtistShowsError());
        }

    }

    public void artistPropertyChange(PropertyChangeEvent evt){
        ArtistState state = (ArtistState) evt.getNewValue();

        LinkedHashMap<String, String> shows = state.getArtistShows();
        ArrayList<String> concerts = new ArrayList<>(5);
        ArrayList<String> concertLinks = new ArrayList<>(5);

        for (Map.Entry<String, String> entry : shows.entrySet()) {
            String key = entry.getKey();
            concerts.add(key);

            String value = entry.getValue();
            concertLinks.add(value);
        }

        JPanel panel = new JPanel();

        JLabel hyperlink = new JLabel();
        JLabel hyperlink2 = new JLabel();
        JLabel hyperlink3 = new JLabel();

        // CONCERT 1
        hyperlink.setText(concerts.get(0) + ": " + concertLinks.get(0));

        // CONCERT 2
        hyperlink2.setText(concerts.get(1) + ": " + concertLinks.get(1));

        // CONCERT 3
        hyperlink3.setText(concerts.get(2) + ": " + concertLinks.get(2));

        panel.add(hyperlink);
        panel.add(hyperlink2);
        panel.add(hyperlink3);

        JOptionPane.showMessageDialog(this, panel);

    }
}