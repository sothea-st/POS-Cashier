/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporting;

/**
 *
 * @author MOBILE-APP.02
 */
import com.raven.datechooser.DateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class DateChooserExample {
      private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private DateChooser dateChooser;

    public DateChooserExample() {
        frame = new JFrame("Date Chooser Example");
        panel = new JPanel();
        textField = new JTextField(20);
        dateChooser = new DateChooser();

        // Set initial date (optional)
        // dateChooser.setDate(new Date());

        // Event listener for the JTextField
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the DateChooser when JTextField is clicked
                Point location = textField.getLocationOnScreen();
                dateChooser.setLocation((int) location.getX(), (int) location.getY() + textField.getHeight());
                dateChooser.setVisible(true);
            }
        });

        // Listener to set selected date back to JTextField
        dateChooser.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
//                textField.setText(dateChooser.getDateString());
                dateChooser.setVisible(false); // Hide DateChooser after selection
            }
        });

        panel.add(textField);
        frame.add(panel);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DateChooserExample());
    }
    
}
