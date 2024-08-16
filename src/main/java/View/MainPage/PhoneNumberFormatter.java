/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author MOBILE-APP.02
 */
public class PhoneNumberFormatter {

     public static void main(String[] args) {
          JFrame frame = new JFrame("Phone Number Formatter");
          JTextField textField = new JTextField(20);

          textField.addKeyListener(new KeyAdapter() {
               @Override
               public void keyReleased(KeyEvent e) {
                    String text = textField.getText().replaceAll("\\s", ""); // Remove all spaces
                    StringBuilder filteredText = new StringBuilder();

                    // Filter out non-digit characters
                    for (int i = 0; i < text.length(); i++) {
                         if (Character.isDigit(text.charAt(i))) {
                              filteredText.append(text.charAt(i));
                         }
                    }

                    // Rebuild the string with spaces after every 3 digits
                    StringBuilder formatted = new StringBuilder();
                    for (int i = 0; i < filteredText.length(); i++) {
                         formatted.append(filteredText.charAt(i));
                         // Add a space after every 3rd digit
                         if ((i + 1) % 3 == 0 && i + 1 < filteredText.length()) {
                              formatted.append(" ");
                         }
                    }

                    // Update the text field without triggering another event
                    textField.removeKeyListener(this); // Temporarily remove listener to avoid recursion
                    textField.setText(formatted.toString());
                    textField.addKeyListener(this); // Re-add listener after updating text
               }
          });

          frame.add(textField);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
     }
}
