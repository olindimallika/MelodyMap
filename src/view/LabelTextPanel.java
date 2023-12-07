package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JTextField textField) {
        this.add(textField);
    }
}