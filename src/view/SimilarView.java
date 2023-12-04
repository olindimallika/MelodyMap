package view;

import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.show_concerts.ShowConcertsController;
import interface_adapter.show_concerts.ShowConcertsState;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.similar_artist.SimilarArtistController;
import interface_adapter.similar_artist.SimilarArtistState;
import interface_adapter.similar_artist.SimilarArtistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class SimilarView extends JPanel implements ActionListener, PropertyChangeListener  {

    public final String viewName = "similar artists";
    private final SimilarArtistViewModel similarArtistViewModel;
    private final SimilarArtistController similarArtistController;

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JTextField favouriteArtistInputField = new JTextField(15);


    private final JButton enter;

    JLabel hyperlink;
    JLabel hyperlink2;
    JLabel hyperlink3;
    JLabel hyperlink4;
    JLabel hyperlink5;

    final JButton personalize;
    /**
     * A window with artists and their concert links.
     */
    public SimilarView(SimilarArtistController controller, SimilarArtistViewModel similarArtistViewModel, JButton personalize) throws Exception{
        this.similarArtistController = controller;
        this.similarArtistViewModel = similarArtistViewModel;
        similarArtistViewModel.addPropertyChangeListener(this);
        similarArtistViewModel.addPropertyChangeListener(this::similarPropertyChange);

        this.setSize(1000, 400);

        JLabel title = new JLabel("Similar Artists to Your Favourites");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        title.setForeground(Color.white);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(SimilarArtistViewModel.POSTALCODE_LABEL), postalCodeInputField);

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);

        JPanel buttons = new JPanel();
        enter = new JButton(SimilarArtistViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);

        hyperlink = new JLabel();
        hyperlink.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        hyperlink.setForeground(Color.white);

        hyperlink2 = new JLabel();
        hyperlink2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        hyperlink2.setForeground(Color.white);

        hyperlink3 = new JLabel

        enter.addActionListener(
                e -> {
                    if (e.getSource().equals(enter)){
                        SimilarArtistState currentState = similarArtistViewModel.getState();

                        try {
                            similarArtistController.execute(currentState.getPostalCode(), currentState.getFavouriteArtists());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );
        postalCodeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SimilarArtistState currentState = similarArtistViewModel.getState();
                        String text = postalCodeInputField.getText() + e.getKeyChar();
                        currentState.setPostalCode(text);
                        similarArtistViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );
        favouriteArtistInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SimilarArtistState currentState = similarArtistViewModel.getState();
                        String text = favouriteArtistInputField.getText() + e.getKeyChar();
                        currentState.setFavouriteArtists(text); //
                        similarArtistViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userPostalCode);
        this.add(userFavouriteArtist);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Error, not expected.");
    }
    public void propertyChange(PropertyChangeEvent evt) {
        SimilarArtistState state = (SimilarArtistState) evt.getNewValue();
        if (state.getPostalCodeError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostalCodeError());
        }
    }
    public void similarPropertyChange(PropertyChangeEvent evt){
        SimilarArtistState state = (SimilarArtistState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, state.getFinalFormatSimilarArtist());
    }



    /**
     * A window with artists and their concert links.
     */
    public ShowConcertsView(ShowConcertsController controller, ShowConcertsViewModel showConcertsViewModel) {
        this.showConcertsController = controller;
        this.showConcertsViewModel = showConcertsViewModel;
        this.showConcertsViewModel.addPropertyChangeListener(this);

        this.setSize(1000, 400);

        JLabel title = new JLabel("Upcoming Shows Near You");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        hyperlink = new JLabel();
        hyperlink2 = new JLabel();
        hyperlink3 = new JLabel();
        hyperlink4 = new JLabel();
        hyperlink5 = new JLabel();

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(hyperlink);
        this.add(hyperlink2);
        this.add(hyperlink3);
        this.add(hyperlink4);
        this.add(hyperlink5);
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

}
