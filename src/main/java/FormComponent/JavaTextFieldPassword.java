package FormComponent;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JavaTextFieldPassword extends javax.swing.JPanel {

     private String labelName;
     private String valueTextField = null;

     public JavaTextFieldPassword() {
          initComponents();

          initEvent(); //to get value from field must be initialize initEvent without paramater

 
          initTxtPass();
     }

     private void initTxtPass() {
          txtPass.putClientProperty(FlatClientProperties.STYLE, ""
               + "showRevealButton:true;"
               + "showCapsLock:true");
          txtPass.setFont(WindowFonts.timeNewRoman14);
          txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password"); // add placeholder

          label.setFont(WindowFonts.timeNewRomanBold14); // set font
          // label error
          lbError.setVisible(false);
          lbError.setForeground(WindowColor.red);
          lbError.setFont(WindowFonts.timeNewRomanBold14);
          txtPass.putClientProperty(FlatClientProperties.STYLE, "arc:10;");
          setBackground(WindowColor.mediumGreen);
          txtPass.setBackground(WindowColor.white);
     }

     public void setLabelName(String labelName) {
          this.labelName = labelName;
          if (labelName.contains("*")) {
               labelName = labelName.replace("*", "");
               label.setText("<html>" + labelName + " <span style='color:red;font-size:16;'>*</span></html>");
          } else {
               label.setText(labelName);
          }
     }

     // method initEvent with paramate for do specific action 
     public void initEvent(ButtonEvent event) {
          txtPass.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
               }

               @Override
               public void keyPressed(KeyEvent e) {
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    valueTextField = txtPass.getText();
                    resetError();
                    //Check if khmer font
                    if (JavaConstant.containsKhmer(valueTextField)) {
                         txtPass.setFont(WindowFonts.khmerOsContent12);
                    } else {
                         txtPass.setFont(WindowFonts.timeNewRoman14);
                    }

                    txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                         + "showRevealButton:true;"
                         + "showCapsLock:true;"
                         + "arc:10;"
                    );
               }
          });
     }

     // method initEvent for get get value from text when user typing text
     public void initEvent() {

          txtPass.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
               }

               @Override
               public void keyPressed(KeyEvent e) {
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String password = new String(txtPass.getPassword());

                    valueTextField = password;

                    resetError();
                    //Check if khmer font
                    if (JavaConstant.containsKhmer(valueTextField)) {
                         txtPass.setFont(WindowFonts.khmerOsContent12);
                    } else {
                         txtPass.setFont(WindowFonts.timeNewRoman14);
                    }

                    txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                         + "showRevealButton:true;"
                         + "showCapsLock:true;"
                         + "arc:10;"
                    );
               }
          });
     }

     public void setFieldError(boolean value) {
          lbError.setVisible(value);
     }

     public void setFieldError(String text) {
          lbError.setText(text);
          lbError.setVisible(true);
     }

     public void setLabel(String text) {
          label.setText(text);
     }

     // Method to set the red border for JPasswordField with reveal button
     public void setErrorBorder() {
          txtPass.putClientProperty(FlatClientProperties.STYLE, "borderColor:#FF0000; showRevealButton:true;arc:10;");
     }

     // Method to reset the border to the default color for JPasswordField
     private void resetError() {
          txtPass.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;");
     }

     public void setText(String value) {
          if (JavaConstant.containsKhmer(value)) {
               txtPass.setFont(WindowFonts.khmerOsContent12);
          } else {
               txtPass.setFont(WindowFonts.timeNewRoman14);
          }

          txtPass.setText(value);
          setValueTextField(value);

          txtPass.putClientProperty(FlatClientProperties.STYLE, ""
               + "showRevealButton:true;"
               + "showCapsLock:true;"
               + "arc:10;"
          );
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          label = new javax.swing.JLabel();
          lbError = new javax.swing.JLabel();
          txtPass = new javax.swing.JPasswordField();

          label.setText("Label Name");
          label.setPreferredSize(new java.awt.Dimension(63, 20));

          lbError.setText("The field is required.");

          txtPass.setPreferredSize(new java.awt.Dimension(300, 35));

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel label;
     private javax.swing.JLabel lbError;
     private javax.swing.JPasswordField txtPass;
     // End of variables declaration//GEN-END:variables
}
