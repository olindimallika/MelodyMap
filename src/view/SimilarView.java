package view;

import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.similar_artist.SimilarArtistController;
import interface_adapter.similar_artist.SimilarArtistState;
import interface_adapter.similar_artist.SimilarArtistViewModel;
import use_case.similar_artist_venue.SimilarInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class SimilarView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "similar artists";
    private final SimilarArtistViewModel similarArtistViewModel;
    private final SimilarArtistController similarArtistController;

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JTextField favouriteArtistInputField = new JTextField(15);


    private final JButton enter;
//
JLabel hyperlink;
//    JLabel hyperlink2;
//    JLabel hyperlink3;
//    JLabel hyperlink4;
//    JLabel hyperlink5;
//
//    final JButton findSimilarArtistsButton;
    /**
     * A window with artists and their concert links.
     */
    public SimilarView(SimilarArtistController controller, SimilarArtistViewModel similarArtistViewModel) {
        this.similarArtistController = controller;
        this.similarArtistViewModel = similarArtistViewModel;
        similarArtistViewModel.addPropertyChangeListener(this);
        similarArtistViewModel.addPropertyChangeListener(this::similarPropertyChange);

        this.setSize(1000, 400);

        JLabel title = new JLabel("Similar Artists to Your Favourites");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//

//        hyperlink = new JLabel();
//        hyperlink2 = new JLabel();
//        hyperlink3 = new JLabel();
//        hyperlink4 = new JLabel();
//        hyperlink5 = new JLabel();
//
//        postalCodeTextField = new JTextField(20); // Initialize the postal code text field
//
//        JPanel buttons = new JPanel();
//        findSimilarArtistsButton = new JButton(similarArtistViewModel.TITLE_LABEL);
//        buttons.add(findSimilarArtistsButton);
//
//
//        findSimilarArtistsButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(findSimilarArtistsButton)) {
//                            SimilarArtistState currentState = similarArtistViewModel.getState();
//                            List<String> favouriteArtists = currentState.getFavouriteArtists();
//                            if (!favouriteArtists.isEmpty()) { // Check if the list is not empty
//                                String postalCode = postalCodeTextField.getText(); // Get the postal code from the text field
//                                SimilarInputData inputData = new SimilarInputData(postalCode, favouriteArtists);
//                                similarArtistController.execute(inputData);
//                            }
//                        }
//                    }
//                }
//        );
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(hyperlink);
//        this.add(hyperlink2);
//        this.add(hyperlink3);
//        this.add(hyperlink4);
//        this.add(hyperlink5);
//        this.add(buttons);
//    }
//
//    /**
//     * React to a button click that results in evt.
//     */
//    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());
//    }
//@Override
//public void propertyChange(PropertyChangeEvent evt) {
//    SimilarArtistState state = (SimilarArtistState) evt.getNewValue();
//
//    HashMap<String, List<String>> similarArtists = state.getSimilarArtists();
//    List<String> artists = new ArrayList<>(5);
//    List<String> artistLinks = new ArrayList<>(5);
//
//    for (Map.Entry<String, List<String>> entry : similarArtists.entrySet()) {
//        String key = entry.getKey();
//        artists.add(key);
//
//        String value = entry.getValue().toString();
//        artistLinks.add(value);
//    }
//
//    // ARTIST 1
//    setHyperlink(hyperlink, artists.get(0), artistLinks.get(0));
//
//    // ARTIST 2
//    setHyperlink(hyperlink2, artists.get(1), artistLinks.get(1));
//
//    // ARTIST 3
//    setHyperlink(hyperlink3, artists.get(2), artistLinks.get(2));
//
//    // ARTIST 4
//    setHyperlink(hyperlink4, artists.get(3), artistLinks.get(3));
//
//    // ARTIST 5
//    setHyperlink(hyperlink5, artists.get(4), artistLinks.get(4));
//}
//
//    private void setHyperlink(List<String> favouriteArtist, JLabel hyperlink, String artist, String link) {
//        hyperlink.setText(favouriteArtist);
//        hyperlink.setText(artist + ": " + link);
//        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        hyperlink.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Desktop.getDesktop().browse(new URI(link));
//                } catch (IOException | URISyntaxException ioException) {
//                    ioException.printStackTrace();
//                }
//            }
//        });
//    }
        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(SimilarArtistViewModel.POSTALCODE_LABEL), postalCodeInputField);

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);

        JPanel buttons = new JPanel();
        enter = new JButton(SimilarArtistViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);

        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)){
                            SimilarArtistState currentState = similarArtistViewModel.getState();

                            //presaleController.execute();


                            try {
                                similarArtistController.execute(currentState.getPostalCode(), currentState.getFavouriteArtists());
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }

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
        JOptionPane.showMessageDialog(this, state.getFormatOutputPresale());
    }


}
