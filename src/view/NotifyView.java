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
    private final JTextField favouriteArtistInputField = new JTextField(50);

    private final NotifyController notifyController;

    private final JButton check;

    public NotifyView(NotifyController controller,
                      NotifyViewModel notifyViewModel){
        this.notifyController = controller;
        this.notifyViewModel = notifyViewModel;
        notifyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(NotifyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.PLAIN,15));
        title.setForeground(Color.white);

        JLabel favouriteArtistLabel = new JLabel(NotifyViewModel.ARTIST_LABEL);
        favouriteArtistLabel.setFont(new Font("Arial", Font.PLAIN,15));
        favouriteArtistLabel.setForeground(Color.white);

        LabelTextPanel favouriteArtist = new LabelTextPanel(favouriteArtistInputField);
        favouriteArtist.setOpaque(false);

        JPanel buttons = new JPanel();
        check = new JButton(NotifyViewModel.CHECK_BUTTON_LABEL);
        buttons.add(check);
        buttons.setOpaque(false);

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

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 550, 10));
        this.add(title);
        this.add(favouriteArtistLabel);
        this.add(favouriteArtist);
        this.add(buttons);
        this.setBackground(new Color(145, 172, 200));


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
        if (state.getArtistOnTourError() != null) {
            JOptionPane.showMessageDialog(this, state.getArtistOnTourError());
        }
    }

}