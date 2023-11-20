package view;

import interface_adapter.upcoming_shows.UpcomingController;
import interface_adapter.upcoming_shows.UpcomingState;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpcomingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "upcoming shows";

    private final UpcomingViewModel upcomingShowsViewModel;

    private final JTextField postalCodeInputField = new JTextField(15);

    private final UpcomingController upcomingShowsController;

    private final JButton enter;

    public UpcomingView(UpcomingController controller,
                        UpcomingViewModel upcomingShowsViewModel){
        this.upcomingShowsController = controller;
        this.upcomingShowsViewModel = upcomingShowsViewModel;
        upcomingShowsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(UpcomingViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userPostalCode = new LabelTextPanel(
                new JLabel(UpcomingViewModel.POSTALCODE_LABEL), postalCodeInputField);

        JPanel buttons = new JPanel();
        enter = new JButton(UpcomingViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);

        enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(enter)){
                            UpcomingState currentState = upcomingShowsViewModel.getState();

                            upcomingShowsController.execute(
                                    currentState.getPostalCode()
                            );
                        }
                    }
                }
        );

        postalCodeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        UpcomingState currentState = upcomingShowsViewModel.getState();
                        String text = postalCodeInputField.getText() + e.getKeyChar();
                        currentState.setPostalCode(text);
                        upcomingShowsViewModel.setState(currentState);
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
        UpcomingState state = (UpcomingState) evt.getNewValue();
        if (state.getPostalCodeError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostalCodeError());
        }
    }
}

