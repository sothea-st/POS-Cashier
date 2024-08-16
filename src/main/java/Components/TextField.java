package Components;

import Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextField extends javax.swing.JPanel {

     private String labelTextField;
     private String valueTextField;
     private String comma;

     public TextField() {
          initComponents();
          setBackground(WindowColor.white);
          txtText.setFont(WindowFonts.timeNewRoman14);
          JavaConstant.setPointer(txtText);
     }

     public static boolean isKhmerCharacter(char c) {
          return (c >= '\u1780' && c <= '\u17FF') || (c >= '\u19E0' && c <= '\u19FF');
     }

     // Method to detect if a string contains any Khmer characters
     public static boolean containsKhmer(String text) {
          if (text == null || text.isEmpty()) {
               return false;
          }
          for (char c : text.toCharArray()) {
               if (isKhmerCharacter(c)) {
                    return true;
               }
          }
          return false;
     }

     //Create Placeholder
     public void initEvent(ButtonEvent event) {

          txtText.addFocusListener(new FocusListener() {
               @Override
               public void focusGained(FocusEvent e) {
                    if (txtText.getText().trim().equals(labelTextField)) {
                         txtText.setText("");
                    }
                    txtText.setForeground(Color.BLACK);
               }

               @Override
               public void focusLost(FocusEvent e) {
                    if (txtText.getText().trim().equals("")) {
                         txtText.setText(labelTextField);
                         txtText.setFont(WindowFonts.timeNewRoman14);
                         txtText.setForeground(Color.LIGHT_GRAY);
                    }

                    if (txtText.getText().trim().equals(labelTextField)) {
                         txtText.setForeground(Color.LIGHT_GRAY);
                    }
               }
          });

          txtText.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
                    if (containsKhmer(txtText.getText())) {
                         txtText.setFont(WindowFonts.khmerOsContent12);
                    }
               }

               @Override
               public void keyPressed(KeyEvent e) {
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String text = txtText.getText();
                    setValueTextField(text);
                    event.onKeyRelease();
                    if (containsKhmer(txtText.getText())) {
                         txtText.setFont(WindowFonts.khmerOsContent12);
                    }
               }
          });
     }

     // add phone number 3 digits add space
     public void add3digits() {
          txtText.addKeyListener(new KeyAdapter() {
               @Override
               public void keyReleased(KeyEvent e) {
                    String text = txtText.getText().replaceAll("\\s", ""); // Remove all spaces
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
                    txtText.removeKeyListener(this); // Temporarily remove listener to avoid recursion
                    txtText.setText(formatted.toString());
                    txtText.addKeyListener(this); // Re-add listener after updating text
               }
          });
     }

     public void disabledTextField(boolean value) {
          txtText.setEnabled(value);
     }

     public void setFocus() {
          txtText.requestFocus();
     }

     //=================Create Shadow Box======================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
     }

     public String getComma() {
          return comma;
     }

     public void setComma(String comma) {
          this.comma = comma;
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          txtText = new javax.swing.JTextField();

          txtText.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
          txtText.setBorder(null);
          txtText.addKeyListener(new java.awt.event.KeyAdapter() {
               public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtTextKeyPressed(evt);
               }
               public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtTextKeyReleased(evt);
               }
               public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtTextKeyTyped(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtText)
                    .addContainerGap())
          );
     }// </editor-fold>//GEN-END:initComponents

     public String getLabelTextField() {
          return labelTextField;
     }

     public void setLabelTextField(String labelTextField) {
          this.labelTextField = labelTextField;
          txtText.setText(labelTextField);
          txtText.setForeground(Color.LIGHT_GRAY);
     }

     public static boolean onlyDigits(String str) {
          for (int i = 0; i < str.length(); i++) {
               if (str.charAt(i) == '.' || str.charAt(i) == ',') {
                    continue;
               }
               if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    return false;
               }
          }
          return true;
     }

     void _checkText(String txt) {
          if (comma != null) {
               if (txt.length() == 1 && txt.equals(".")) {
                    txtText.setText(null);
                    return;
               }
               if (txt.length() == 1 && txt.equals(",")) {
                    txtText.setText(null);
                    return;
               }

               int count = 0;

               for (int i = 0; i < txt.length(); i++) {

                    if (txt.charAt(i) == '.') {
                         count++;
                    }

                    if (count > 1) {
                         String newValue = txt.substring(0, txt.length() - 1) + "";
                         txtText.setText(newValue);
                         return;
                    }

               }

               boolean isCheck = onlyDigits(txt);
               if (!isCheck) {
                    String newValue = txt.substring(0, txt.length() - 1) + "";
                    txtText.setText(newValue);
                    return;
               }

               if (txt.contains(".")) {
                    return;
               }
               // ================ 3 length insert comma =========
               if (txt.length() > 3) {
                    StringBuilder builder = new StringBuilder(txt.replaceAll(",", ""));
                    for (int i = builder.length() - 3; i > 0; i -= 3) {
                         builder.insert(i, ",");
                    }
                    setValueTextField(builder.toString());
               }
          }
     }

     private void txtTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyReleased
          String txt = txtText.getText();
          _checkText(txt);

     }//GEN-LAST:event_txtTextKeyReleased

     private void txtTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyPressed
          String txt = txtText.getText();
          _checkText(txt);

     }//GEN-LAST:event_txtTextKeyPressed

     private void txtTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyTyped
          String txt = txtText.getText();
          _checkText(txt);
     }//GEN-LAST:event_txtTextKeyTyped

     public String getValueTextField() {
          return valueTextField;
     }

     public void setValueTextField(String valueTextField) {
          this.valueTextField = valueTextField;
          txtText.setText(valueTextField);
          txtText.setForeground(Color.BLACK);
          if (containsKhmer(txtText.getText())) {
               txtText.setFont(WindowFonts.khmerOsContent12);
          }
     }
     
     
     // add phone number 3 digits add space
     public void add3digitsToPhoneNumber() {
        
          txtText.addKeyListener(new KeyAdapter() {
               @Override
               public void keyReleased(KeyEvent e) {
                    String text = txtText.getText().replaceAll("\\s", ""); // Remove all spaces
                    StringBuilder filteredText = new StringBuilder();

                    // Filter out non-digit characters
                    for (int i = 0; i < text.length(); i++) {
                         if (Character.isDigit(text.charAt(i))) {
                              filteredText.append(text.charAt(i));
                         }
                    }
                    
                    //Set Limit text to 10 digit 
                    if (filteredText.length() > 10) {
                        filteredText.setLength(10);
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
                    txtText.removeKeyListener(this); // Temporarily remove listener to avoid recursion
                    txtText.setText(formatted.toString());
                    txtText.addKeyListener(this); // Re-add listener after updating text
               }
          });
     }


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JTextField txtText;
     // End of variables declaration//GEN-END:variables

}
