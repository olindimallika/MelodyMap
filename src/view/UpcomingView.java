package view;

import interface_adapter.upcoming_shows.UpcomingController;
import interface_adapter.upcoming_shows.UpcomingState;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpcomingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "upcoming shows";

    private final UpcomingViewModel upcomingShowsViewModel;

    private final JTextField postalCodeInputField = new JTextField(45);

    private final UpcomingController upcomingShowsController;

    private final JButton enter;

    public UpcomingView(UpcomingController controller,
                        UpcomingViewModel upcomingShowsViewModel){
        this.upcomingShowsController = controller;
        this.upcomingShowsViewModel = upcomingShowsViewModel;
        upcomingShowsViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(UpcomingViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.PLAIN,15));
        title.setForeground(Color.white);

        JLabel postalCodeLabel = new JLabel(UpcomingViewModel.POSTALCODE_LABEL);
        postalCodeLabel.setFont(new Font("Arial", Font.PLAIN,15));
        postalCodeLabel.setForeground(Color.white);

        LabelTextPanel userPostalCode = new LabelTextPanel(postalCodeInputField);
        userPostalCode.setFont(new Font("Arial", Font.PLAIN,15));
        userPostalCode.setOpaque(false);

        JPanel buttons = new JPanel();
        enter = new JButton(UpcomingViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enter);
        buttons.setOpaque(false);

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

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 550, 10));
        this.add(title);
        this.add(postalCodeLabel);
        this.add(userPostalCode);
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
        UpcomingState state = (UpcomingState) evt.getNewValue();
        if (state.getPostalCodeError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostalCodeError());
        }
    }

}

