//package view;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener
//
//public class SimilarView {
//
//    public final String viewName = "Similar Artists";
//
//    private final SimilarArtistViewModel similarArtistViewModel;
//
//    private final JTextField postalCodeInputField = new JTextField(15);
//
//    private final SimilarArtistController similarArtistController;
//
//    private final JButton enter;
//
//    public SimilarArtistView(SimilarArtistController controller,
//                             SimilarArtistViewModel similarArtistViewModel){
//        this.similarArtistController = controller;
//        this.similarArtistViewModel = similarArtistViewModel;
//        similarArtistViewModel.addPropertyChangeListener(this);
//        similarArtistViewModel.addPropertyChangeListener(this::showsPropertyChange);
//
//
//    }
//}
