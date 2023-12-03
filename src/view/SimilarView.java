package view;

import interface_adapter.similar_artist.SimilarArtistController;
import interface_adapter.similar_artist.SimilarArtistState;
import interface_adapter.similar_artist.SimilarArtistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;


public class SimilarView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Similar Artists";

    private final SimilarArtistViewModel similarArtistViewModel;

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JTextField favouriteArtistInputField = new JTextField(30);

    private final JButton enter;

    private final SimilarArtistController similarArtistController;


    public SimilarView(SimilarArtistController controller,
                             SimilarArtistViewModel similarArtistViewModel){
        this.similarArtistController = controller;
        this.similarArtistViewModel = similarArtistViewModel;
        similarArtistViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SimilarArtistViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(SimilarArtistViewModel.POSTALCODE_LABEL), postalCodeInputField);
        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(SimilarArtistViewModel.FAV_ARTIST_LABEL), favouriteArtistInputField);

        JPanel buttons = new JPanel();
        enter = new JButton(SimilarArtistViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);

        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)) {
                            SimilarArtistState currentState = similarArtistViewModel.getState();
                            SimilarArtistController.execute(currentState.getPostalCode(), currentState.getFavouriteArtists());
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
                        currentState.setFavouriteArtists(text);
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
        this.add(buttons);


    }
    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Error, not expected.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SimilarArtistState state = (SimilarArtistState) evt.getNewValue();
        if (state.getSimilarArtistError() != null) {
            JOptionPane.showMessageDialog(this, state.getSimilarArtists());
        }
    }
}
