package view;

import interface_adapter.show_artist_concerts.ShowArtistController;
import interface_adapter.show_artist_concerts.ShowArtistState;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The `ShowArtistView` class represents a Swing-based view for displaying shows of artists near the user.
 * It extends JPanel and implements ActionListener and PropertyChangeListener interfaces
 * to handle user interactions and respond to property changes in the associated view model.
 */
public class ShowArtistView extends JPanel implements ActionListener, PropertyChangeListener {


    /**
     * The view model responsible for managing the state of the shows of artists.
     */
    private final ShowArtistViewModel showArtistViewModel;

    /**
     * The controller responsible for handling user interactions and executing corresponding use cases.
     */
    private final ShowArtistController showArtistController;

    /**
     * Button for viewing presale information.
     */
    final JButton presale;

    /**
     * Constructs a `ShowArtistView` with the specified controller and view model.
     *
     * @param controller          The controller for handling user interactions.
     * @param showArtistViewModel The view model managing the state of shows of artists.
     */
    public ShowArtistView(ShowArtistController controller, ShowArtistViewModel showArtistViewModel) {
        this.showArtistController = controller;
        this.showArtistViewModel = showArtistViewModel;
        this.showArtistViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Artists Shows Near You");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial Bold", Font.PLAIN, 12));
        title.setForeground(Color.white);

        JPanel buttons = new JPanel();
        presale = new JButton(showArtistViewModel.PERSONALIZE_BUTTON_LABEL);
        buttons.add(presale);
        buttons.setOpaque(false);

        presale.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(presale)) {
                            showArtistController.execute();
                        }
                    }
                }
        );
    }

    /**
     * Handles button click events.
     *
     * @param e The ActionEvent representing the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * Handles property change events, updating the view based on changes in the associated view model's state.
     *
     * @param evt The PropertyChangeEvent representing a change in the associated view model.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShowArtistState state = (ShowArtistState) evt.getNewValue();
        LinkedHashMap<String, List<String>> shows = state.getAllConcerts();
    }
}
