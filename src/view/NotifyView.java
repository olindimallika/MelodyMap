package view;

import interface_adapter.notify_user_tour.NotifyController;
import interface_adapter.notify_user_tour.NotifyState;
import interface_adapter.notify_user_tour.NotifyViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NotifyView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notify user tour";
    private final NotifyViewModel notifyViewModel;
    private final JTextField favouriteArtistInputField = new JTextField(15);

    private final NotifyController notifyController;

    private final JButton check;

    public NotifyView(NotifyController controller,
                        NotifyViewModel notifyViewModel){
        this.notifyController = controller;
        this.notifyViewModel = notifyViewModel;
        notifyViewModel.addPropertyChangeListener(this);
        notifyViewModel.addPropertyChangeListener(this::notifyPropertyChange);

        JLabel title = new JLabel(NotifyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);

        JPanel buttons = new JPanel();
        check = new JButton(NotifyViewModel.CHECK_BUTTON_LABEL);
        buttons.add(check);

        check.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(check)){
                            NotifyState currentState = notifyViewModel.getState();

                            notifyController.execute(
                                    currentState.getFavouriteArtist()
                            );
                        }
                    }
                }
        );

        favouriteArtistInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        NotifyState currentState = notifyViewModel.getState();
                        String text = favouriteArtistInputField.getText() + e.getKeyChar();
                        currentState.setFavouriteArtist(text);
                        notifyViewModel.setState(currentState);
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
        NotifyState state = (NotifyState) evt.getNewValue();
        if (state.getFavouriteArtistError() != null) {
            JOptionPane.showMessageDialog(this, state.getFavouriteArtistError());
        }
    }

    public void notifyPropertyChange(PropertyChangeEvent evt){
        NotifyState state = (NotifyState) evt.getNewValue();

        String hyperlinkText = state.getConcertLink();

        // making the link clickable for user
        JLabel hyperlink = new JLabel(state.getFavouriteArtistUpcoming() + hyperlinkText);
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(hyperlinkText));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });

        JOptionPane.showMessageDialog(this, hyperlink);
    }

}

