/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.DocumentListener;
import org.w3c.dom.events.DocumentEvent;

/**
 *
 * @author MOBILE-APP.02
 */
public class MainFrame extends JFrame{

        private JTextField textField;

    public MainFrame() {
        setTitle("Insert Commas");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField = new JTextField(20);
        textField.getDocument().addDocumentListener(new DocumentListener() {
          
         

             @Override
             public void insertUpdate(javax.swing.event.DocumentEvent e) {
                     formatText();
//                  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void removeUpdate(javax.swing.event.DocumentEvent e) {
                     formatText();
//                  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void changedUpdate(javax.swing.event.DocumentEvent e) {
                     formatText();
//                  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }
        });

        JPanel panel = new JPanel();
        panel.add(textField);
        add(panel);

        setVisible(true);
    }

    private void formatText() {
        String text = textField.getText().replaceAll(",", ""); // Remove existing commas
        StringBuilder formattedText = new StringBuilder();
        int length = 0;
        for (char c : text.toCharArray()) {
            if (c == '.') {
                formattedText.append(c);
                break;
            }
            if (Character.isDigit(c)) {
                if (length > 0 && length % 3 == 0) {
                    formattedText.append(',');
                }
                formattedText.append(c);
                length++;
            }
        }
        textField.setText(formattedText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
