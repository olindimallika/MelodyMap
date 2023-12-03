package view;

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

public class ShowConcertsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "show concerts";
    private final ShowConcertsViewModel showConcertsViewModel;
    private final ShowConcertsController showConcertsController;

    JLabel hyperlink;
    JLabel hyperlink2;
    JLabel hyperlink3;
    JLabel hyperlink4;
    JLabel hyperlink5;

    final JButton personalize;

    /**
     * A window with artists and their concert links.
     */
    public ShowConcertsView(ShowConcertsController controller, ShowConcertsViewModel showConcertsViewModel) {
        this.showConcertsController = controller;
        this.showConcertsViewModel = showConcertsViewModel;
        this.showConcertsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Upcoming Shows Near You");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial Bold", Font.PLAIN,10));

        hyperlink = new JLabel();
        hyperlink.setFont(new Font("Arial Bold", Font.PLAIN,10));

        hyperlink2 = new JLabel();
        hyperlink2.setFont(new Font("Arial Bold", Font.PLAIN,10));

        hyperlink3 = new JLabel();
        hyperlink3.setFont(new Font("Arial Bold", Font.PLAIN,10));

        hyperlink4 = new JLabel();
        hyperlink4.setFont(new Font("Arial Bold", Font.PLAIN,10));

        hyperlink5 = new JLabel();
        hyperlink5.setFont(new Font("Arial Bold", Font.PLAIN,10));

        JPanel buttons = new JPanel();
        personalize = new JButton(showConcertsViewModel.PERSONALIZE_BUTTON_LABEL);
        buttons.add(personalize);

        personalize.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(personalize)){
                            showConcertsController.execute();
                        }
                    }
                }
        );


        this.setLayout(new FlowLayout(FlowLayout.CENTER, 550, 10));
        this.add(title);
        this.add(hyperlink);
        this.add(hyperlink2);
        this.add(hyperlink3);
        this.add(hyperlink4);
        this.add(hyperlink5);
        this.add(buttons);
        this.setPreferredSize(new Dimension(1200, 250));
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShowConcertsState state = (ShowConcertsState) evt.getNewValue();

        LinkedHashMap<String, String> shows = state.getConcerts();
        ArrayList<String> concerts = new ArrayList<>(5);
        ArrayList<String> concertLinks = new ArrayList<>(5);

        for (Map.Entry<String, String> entry : shows.entrySet()) {
            String key = entry.getKey();
            concerts.add(key);

            String value = entry.getValue();
            concertLinks.add(value);
        }

        // CONCERT 1
        hyperlink.setText(concerts.get(0) + ": " + concertLinks.get(0));
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hyperlink.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(concertLinks.get(0)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // CONCERT 2
        hyperlink2.setText(concerts.get(1) + ": " + concertLinks.get(1));
        hyperlink2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hyperlink2.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(concertLinks.get(1)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // CONCERT 3
        hyperlink3.setText(concerts.get(2) + ": " + concertLinks.get(2));
        hyperlink3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hyperlink3.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(concertLinks.get(2)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // CONCERT 4
        hyperlink4.setText(concerts.get(3) + ": " + concertLinks.get(3));
        hyperlink4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hyperlink4.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(concertLinks.get(3)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });


        // CONCERT 5
        hyperlink5.setText(concerts.get(4) + ": " + concertLinks.get(4));
        hyperlink5.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hyperlink5.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(concertLinks.get(4)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }

}
