package view;

import interface_adapter.show_artist_concerts.ShowArtistController;
import interface_adapter.show_artist_concerts.ShowArtistState;
import interface_adapter.show_artist_concerts.ShowArtistViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


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

public class ShowArtistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "show concerts";

    private final ShowArtistViewModel showArtistViewModel;

    private final ShowArtistController showArtistController;

    final JButton presale;

    public ShowArtistView(ShowArtistController controller, ShowArtistViewModel showArtistViewModel) {
        this.showArtistController = controller;
        this.showArtistViewModel = showArtistViewModel;
        this.showArtistViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Artists Shows Near You");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial Bold", Font.PLAIN,12));
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
                        if (e.getSource().equals(presale)){
                            showArtistController.execute();
                        }
                    }
                }
        );
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ShowArtistState state = (ShowArtistState) evt.getNewValue();
        LinkedHashMap<String, List<String>> shows = state.getAllConcerts();

    }

}
