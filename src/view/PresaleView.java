/**
 * This class represents the view for the presale functionality in the application.
 * It extends JPanel and implements ActionListener and PropertyChangeListener interfaces.
 */
package view;
import interface_adapter.notify_user_tour.NotifyViewModel;
import interface_adapter.presale.PresaleController;
import interface_adapter.presale.PresaleState;
import interface_adapter.presale.PresaleViewModel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


/**
 * The PresaleView class provides a graphical user interface for the presale functionality.
 */
public class PresaleView extends JPanel implements ActionListener, PropertyChangeListener  {
    /**
     * The name of the view, used for identification.
     */
    public final String viewName = "presale";

    /**
     * The associated PresaleViewModel for managing the view's data.
     */
    private final PresaleViewModel presaleViewModel;


    /**
     * Text field for user input of postal code.
     */
    private final JTextField postalCodeInputField = new JTextField(15);

    /**
     * Text field for user input of favorite artist.
     */
    private final JTextField favouriteArtistInputField = new JTextField(15);

    /**
     * The associated PresaleController for handling user actions.
     */
    private final PresaleController presaleController;

    /**
     * Button for triggering the presale functionality.
     */
    private final JButton enter;

    /**
     * Hyperlink labels for additional information.
     */
    JLabel hyperlink;
    JLabel hyperlink2;
    JLabel hyperlink3;
    JLabel hyperlink4;
    JLabel hyperlink5;


    /**
     * Constructs a new PresaleView with the given controller and view model.
     *
     * @param controller        The PresaleController associated with this view.
     * @param presaleViewModel  The PresaleViewModel associated with this view.
     */
    public PresaleView(PresaleController controller,
                       PresaleViewModel presaleViewModel) {
        this.presaleController = controller;
        this.presaleViewModel = presaleViewModel;
        presaleViewModel.addPropertyChangeListener(this);
        presaleViewModel.addPropertyChangeListener(this::presalePropertyChange);


        //JLabel title = new JLabel(PresaleViewModel.TITLE_LABEL);
        JLabel title = new JLabel("Upcoming Shows Near You");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(PresaleViewModel.POSTALCODE_LABEL), postalCodeInputField);

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);


        //setting hyperlinks to be clickable
        hyperlink = new JLabel();
        hyperlink.setFont(new Font("Arial", Font.PLAIN,12));
        hyperlink.setForeground(Color.white);

        hyperlink2 = new JLabel();
        hyperlink2.setFont(new Font("Arial", Font.PLAIN,12));
        hyperlink2.setForeground(Color.white);

        hyperlink3 = new JLabel();
        hyperlink3.setFont(new Font("Arial", Font.PLAIN,12));
        hyperlink3.setForeground(Color.white);

        hyperlink4 = new JLabel();
        hyperlink4.setFont(new Font("Arial", Font.PLAIN,12));
        hyperlink4.setForeground(Color.white);

        hyperlink5 = new JLabel();
        hyperlink5.setFont(new Font("Arial", Font.PLAIN,12));
        hyperlink5.setForeground(Color.white);




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

        this.add(hyperlink);
        this.add(hyperlink2);
        this.add(hyperlink3);
        this.add(hyperlink4);
        this.add(hyperlink5);

        this.add(userPostalCode);
        this.add(userFavouriteArtist);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Error, not expected.");
    }


    /**
     * Reacts to changes in the properties of the PresaleViewModel.
     *
     * @param evt The PropertyChangeEvent representing a change in the PresaleViewModel.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PresaleState state = (PresaleState) evt.getNewValue();
        if (state.getPostalCodeError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostalCodeError());
        }

    }

    /**
     * Reacts to changes in the properties of the PresaleViewModel specific to presale information.
     *
     * @param evt The PropertyChangeEvent representing a change in presale information.
     */
    public void presalePropertyChange(PropertyChangeEvent evt){
        PresaleState state = (PresaleState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, state.getFormatOutputPresale());

    }

}

