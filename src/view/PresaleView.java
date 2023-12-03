package view;
import interface_adapter.notify_user_tour.NotifyState;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.presale.PresaleController;
import interface_adapter.presale.PresaleState;
import interface_adapter.presale.PresaleViewModel;
import interface_adapter.upcoming_shows.UpcomingState;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PresaleView extends JPanel implements ActionListener, PropertyChangeListener  {
    public final String viewName = "presale";

    private final PresaleViewModel presaleViewModel;

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JTextField favouriteArtistInputField = new JTextField(15);


    private final PresaleController presaleController;

    private final JButton enter;


    public PresaleView(PresaleController controller,
                       PresaleViewModel presaleViewModel) {
        this.presaleController = controller;
        this.presaleViewModel = presaleViewModel;
        presaleViewModel.addPropertyChangeListener(this);
        presaleViewModel.addPropertyChangeListener(this::presalePropertyChange);


        JLabel title = new JLabel(PresaleViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(PresaleViewModel.POSTALCODE_LABEL), postalCodeInputField);

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);



        JPanel buttons = new JPanel();
        enter = new JButton(PresaleViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);

        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)){
                            PresaleState currentState = presaleViewModel.getState();

                            //presaleController.execute();


                            try {
                                presaleController.execute(currentState.getPostalCode(), currentState.getFavArtists());
                            } catch (IOException ex) {
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
                        PresaleState currentState = presaleViewModel.getState();
                        String text = postalCodeInputField.getText() + e.getKeyChar();
                        currentState.setPostalCode(text);
                        presaleViewModel.setState(currentState);
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
                        PresaleState currentState = presaleViewModel.getState();
                        String text = favouriteArtistInputField.getText() + e.getKeyChar();
                        currentState.setFavArtists(text); //
                        presaleViewModel.setState(currentState);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PresaleState state = (PresaleState) evt.getNewValue();
        if (state.getPostalCodeError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostalCodeError());
        }
    }

    public void presalePropertyChange(PropertyChangeEvent evt){
        PresaleState state = (PresaleState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, state.getFormatOutputPresale());
    }

//    public void notifyPropertyChange(PropertyChangeEvent evt){
//        PresaleState state = (PresaleState) evt.getNewValue();
//
//        String hyperlinkText = state.getConcertLink();
//
//        // making the link clickable for user
//        JLabel hyperlink = new JLabel(state.getFavouriteArtistUpcoming() + hyperlinkText);
//        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        hyperlink.addMouseListener(new MouseAdapter() {
//
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Desktop.getDesktop().browse(new URI(hyperlinkText));
//                } catch (IOException | URISyntaxException e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//        });
//
//        JOptionPane.showMessageDialog(this, hyperlink);
//    }

}
