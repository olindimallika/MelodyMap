package view;

import app.Main;

import interface_adapter.upcoming_shows.UpcomingController;
import interface_adapter.upcoming_shows.UpcomingViewModel;
import use_case.upcoming_shows.UpcomingInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The UpcomingShowsTest class contains JUnit tests for the graphical user interface (GUI) components
 * in the "view" package related to upcoming shows. It focuses on testing the presence and functionality of the
 * UI elements, such as buttons and text fields, in the context of entering a postal code to search for upcoming shows.
 *
 * The test cases cover scenarios like verifying the presence and text of the "Enter" button and simulating user
 * interactions with the postal code field. It ensures that the UI components are correctly initialized, and the user
 * input is processed as expected, leading to the correct values in the associated view model.
 *
 * Author: Bea Castro
 */
public class UpcomingShowsTest {

    /**
     * Get the "Enter" button from the main application.
     *
     * @return The "Enter" button.
     */
    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        UpcomingView uv = (UpcomingView) jp2.getComponent(0);

        JPanel buttons = (JPanel) uv.getComponent(3);

        return (JButton) buttons.getComponent(0); // enter button
    }

    /**
     *
     * Test that the Enter button is present and where it is expected to be
     */
    @org.junit.Test
    public void testEnterButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Enter"));
    }

    /**
     * Test the functionality of the upcoming view, including the postal code field and user interactions
     * to enter a postal code.
     */
    @org.junit.Test
    public void testUpcomingView() {

        // create the UI
        UpcomingInputBoundary uib = null;
        UpcomingController controller = new UpcomingController(uib);
        UpcomingViewModel viewModel = new UpcomingViewModel();
        JPanel upcomingView = new UpcomingView(controller, viewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(upcomingView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the postal code field
        LabelTextPanel panel = (LabelTextPanel) upcomingView.getComponent(2);
        JTextField postalField = (JTextField) panel.getComponent(0);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                postalField, // we are interacting with the postalField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'M'); // the character that is being typed

        panel.dispatchEvent(event);


        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal code field and view-model hold
        System.out.println("field 1: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());

        // move to the right in the postal code field
        KeyEvent eventRight = new KeyEvent(
                postalField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight);

        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                postalField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '5');
        panel.dispatchEvent(event2);


        // pause execution for 1 second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal code field and view-model hold
        System.out.println("field 2: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());


        // move to the right in the postal code field
        KeyEvent eventRight2 = new KeyEvent(
                postalField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight2);

        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a third character
        KeyEvent event3 = new KeyEvent(
                postalField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'V');
        panel.dispatchEvent(event3);


        // pause execution for 1 second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal code field and view-model hold
        System.out.println("field 3: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());

        // move to the right in the postal code field
        KeyEvent eventRight3 = new KeyEvent(
                postalField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight3);

        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a fourth character
        KeyEvent event4 = new KeyEvent(
                postalField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '3');
        panel.dispatchEvent(event4);


        // pause execution for 1 second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal field and view-model hold
        System.out.println("field 4: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());

        // move to the right in the postal code field
        KeyEvent eventRight4 = new KeyEvent(
                postalField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight4);

        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a fifth character
        KeyEvent event5 = new KeyEvent(
                postalField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'L');
        panel.dispatchEvent(event5);


        // pause execution for 1 second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal code field and view-model hold
        System.out.println("field 5: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());

        // move to the right in the postal code field
        KeyEvent eventRight5 = new KeyEvent(
                postalField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight5);

        // pause execution for 0.5 seconds
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event6 = new KeyEvent(
                postalField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '9');
        panel.dispatchEvent(event6);


        // pause execution for 1 second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the postal code field and view-model hold
        System.out.println("field 6: " + new String(postalField.getText()));
        System.out.println("view-model: " + viewModel.getState().getPostalCode());

        // assert that the values are as expected.
        assertEquals("M5V3L9", new String(postalField.getText()));
        assertEquals("M5V3L9", viewModel.getState().getPostalCode());
    }

}

