package view;

import interface_adapter.notify_user_tour.NotifyViewModel;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class SimilarView extends JPanel implements ActionListener, PropertyChangeListener {

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

    /**
     * A window with artists and their concert links.
     */
    public SimilarView(SimilarArtistController controller, SimilarArtistViewModel similarArtistViewModel) throws Exception {
        this.similarArtistController = controller;
        this.similarArtistViewModel = similarArtistViewModel;
        similarArtistViewModel.addPropertyChangeListener(this);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(SimilarArtistViewModel.POSTALCODE_LABEL), postalCodeInputField);
        userPostalCode.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);
        userFavouriteArtist.setFont(new Font("New Times Roman", Font.PLAIN, 15));


        hyperlink = new JLabel();
        hyperlink.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        hyperlink.setForeground(Color.black);

        hyperlink2 = new JLabel();
        hyperlink2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        hyperlink2.setForeground(Color.black);

        hyperlink3 = new JLabel();
        hyperlink3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        hyperlink3.setForeground(Color.black);

        hyperlink4 = new JLabel();
        hyperlink4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        hyperlink4.setForeground(Color.black);

        JPanel buttons = new JPanel();
        enter = new JButton(SimilarArtistViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);
        buttons.setOpaque(false);

        enter.addActionListener(
                e -> {
                    if (e.getSource().equals(enter)) {
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
        this.add(userPostalCode);
        this.add(userFavouriteArtist);
        this.add(hyperlink);
        this.add(hyperlink2);
        this.add(hyperlink3);
        this.add(hyperlink4);
        this.add(buttons);
        this.setPreferredSize(new Dimension(1200, 300));
        this.setBackground(new Color(145, 172, 200));
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Error, not expected.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        SimilarArtistState state = (SimilarArtistState) evt.getNewValue();
        System.out.println(state.getFinalFormatSimilarArtist());
        HashMap<String, String> events = (state.getFinalFormatSimilarArtist());
        List<String> artistName = new ArrayList<>();
        List<String> url = new ArrayList<>();
        for (Map.Entry<String, String> entry : events.entrySet()){
            String key = entry.getKey();
            artistName.add(key);

            String value = entry.getValue();
            url.add(value);
        }

        hyperlink.setText(artistName.get(0) + ": " + url.get(0));
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                try {
                    // Check if the URL is valid
                    Desktop.getDesktop().browse(new URI(url.get(0)));

                    } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            });



    // Second concert
        hyperlink2.setText(artistName.get(1) + ": " + url.get(1));
        hyperlink2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url.get(1)));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            });

        // Third concert
        hyperlink3.setText(artistName.get(2) + ": " + url.get(2));
        hyperlink3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink3.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url.get(2)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // CONCERT 4
        hyperlink4.setText(artistName.get(3) + ": " + url.get(3));
        hyperlink4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink4.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url.get(3)));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

    }


    }








