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

/**
 * The `ArtistVenueView` class represents a Swing-based view for displaying artists and their concert links.
 * It extends JPanel and implements ActionListener and PropertyChangeListener interfaces
 * to handle user interactions and respond to property changes in the associated view model.
 */
public class ArtistVenueView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The unique name identifier for this view.
     */
    public final String viewName = "show artist concerts";

    /**
     * The view model responsible for managing the state of the artist and concert information.
     */
    private final ArtistViewModel artistViewModel;

    /**
     * The controller responsible for handling user interactions and executing corresponding use cases.
     */
    private final ArtistController artistController;

    /**
     * Button for reloading and seeing if there are presale dates.
     */
    final JButton reload;

    /**
     * Button for navigating back.
     */
    final JButton back;

    /**
     * Constructs an `ArtistVenueView` with the specified controller and view model.
     *
     * @param artistController The controller for handling user interactions.
     * @param artistViewModel  The view model managing the state of artist and concert information.
     */
    public ArtistVenueView(ArtistController artistController, ArtistViewModel artistViewModel) {
        this.artistController = artistController;
        this.artistViewModel = artistViewModel;
        this.artistViewModel.addPropertyChangeListener(this);

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
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property change events, updating the view based on changes in the associated view model's state.
     *
     * @param evt The PropertyChangeEvent representing a change in the associated view model.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArtistState state = (ArtistState) evt.getNewValue();
        if (state.getArtistShowsError() != null) {
            JOptionPane.showMessageDialog(this, state.getArtistShowsError());
        }
    }
}
