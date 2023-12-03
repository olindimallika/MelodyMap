package view;

import interface_adapter.artist_menu_tour.ArtistMenuController;
import interface_adapter.artist_menu_tour.ArtistMenuViewModel;

import javax.swing.*;

public class ArtistMenuView {

    public final String viewName = "Artist Menu";

    private final ArtistMenuViewModel artistMenuViewModel;

    private final ArtistMenuController artistMenuController;



    //    public final String viewName = "notify user tour";
    //    private final NotifyViewModel notifyViewModel;
    //    private final JTextField favouriteArtistInputField = new JTextField(15);
    //
    //    private final NotifyController notifyController;
    //
    //    private final JButton check;
    //
    //    public NotifyView(NotifyController controller,
    //                        NotifyViewModel notifyViewModel){
    //        this.notifyController = controller;
    //        this.notifyViewModel = notifyViewModel;
    //        notifyViewModel.addPropertyChangeListener(this);
    //        notifyViewModel.addPropertyChangeListener(this::notifyPropertyChange);
    //
    //        JLabel title = new JLabel(NotifyViewModel.TITLE_LABEL);
    //        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    //
    //        LabelTextPanel userFavouriteArtist = new LabelTextPanel(
    //                new JLabel(NotifyViewModel.ARTIST_LABEL), favouriteArtistInputField);
    //
    //        JPanel buttons = new JPanel();
    //        check = new JButton(NotifyViewModel.CHECK_BUTTON_LABEL);
    //        buttons.add(check);
    //
    //        check.addActionListener(
    //                new ActionListener() {
    //                    @Override
    //                    public void actionPerformed(ActionEvent e) {
    //                        if (e.getSource().equals(check)){
    //                            NotifyState currentState = notifyViewModel.getState();
    //
    //                            notifyController.execute(
    //                                    currentState.getFavouriteArtist()
    //                            );
    //                        }
    //                    }
    //                }
    //        );
    //
    //        favouriteArtistInputField.addKeyListener(
    //                new KeyListener() {
    //                    @Override
    //                    public void keyTyped(KeyEvent e) {
    //                        NotifyState currentState = notifyViewModel.getState();
    //                        String text = favouriteArtistInputField.getText() + e.getKeyChar();
    //                        currentState.setFavouriteArtist(text);
    //                        notifyViewModel.setState(currentState);
    //                    }
    //
    //                    @Override
    //                    public void keyPressed(KeyEvent e) {
    //                    }
    //
    //                    @Override
    //                    public void keyReleased(KeyEvent e) {
    //                    }
    //                }
    //        );
    //
    //        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    //
    //        this.add(title);
    //        this.add(userFavouriteArtist);
    //        this.add(buttons);
    //
    //    }
    //
    //
    //    /**
    //     * React to a button click that results in evt.
    //     */
    //    public void actionPerformed(ActionEvent evt) {
    //        JOptionPane.showConfirmDialog(this, "Error, not expected.");
    //    }
    //
    //    @Override
    //    public void propertyChange(PropertyChangeEvent evt) {
    //        NotifyState state = (NotifyState) evt.getNewValue();
    //        if (state.getArtistOnTourError() != null) {
    //            JOptionPane.showMessageDialog(this, state.getArtistOnTourError());
    //        }
    //    }
    //
    //    public void notifyPropertyChange(PropertyChangeEvent evt){
    //        NotifyState state = (NotifyState) evt.getNewValue();
    //        if (!state.getArtistOnTour().isEmpty()) {
    //            JOptionPane.showMessageDialog(this, state.getArtistOnTour());
    //        }
    //    }
    //
    //}
}
