package Components;

import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author FRONT-END.06
 */
public class PasswordField extends javax.swing.JPanel {

     private String valuePassword;
     private String textPassowrd;

     public PasswordField() {
          initComponents();
          setBackground(WindowColor.white);
          JavaConstant.setPointer(password);
     }

     //=================================================Create Shadow Box
     private ShadowType shadowType;
     private int shadowSize = 3;
     private float shadowOpacity = 0.8f;
     private Color shadowColor = Color.GRAY;

     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          createShadow(grphcs);
          super.paintComponent(grphcs);
     }

     private void createShadow(Graphics grphcs) {
          Graphics2D g2 = (Graphics2D) grphcs;
          int size = shadowSize * 2;
          int x = 0;
          int y = 0;
          int width = getWidth() - size;
          int height = getHeight() - size;
          if (shadowType == ShadowType.TOP) {
               x = shadowSize;
               y = size;
          } else if (shadowType == ShadowType.BOT) {
               x = shadowSize;
               y = 0;
          } else if (shadowType == ShadowType.TOP_LEFT) {
               x = size;
               y = size;
          } else if (shadowType == ShadowType.TOP_RIGHT) {
               x = 0;
               y = size;
          } else if (shadowType == ShadowType.BOT_LEFT) {
               x = size;
               y = 0;
          } else if (shadowType == ShadowType.BOT_RIGHT) {
               x = 0;
               y = 0;
          } else {
               //  Center
               x = shadowSize;
               y = shadowSize;
          }
          BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g = img.createGraphics();
          g.setColor(getBackground());
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g.fillRoundRect(0, 0, width, height, 10, 10);

          //  Create Shadow
          ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
          g2.drawImage(render.createShadow(img), 0, 0, null);
          g2.drawImage(img, x, y, null);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          password = new javax.swing.JPasswordField();

          password.setBorder(null);
          password.addFocusListener(new java.awt.event.FocusAdapter() {
               public void focusGained(java.awt.event.FocusEvent evt) {
                    passwordFocusGained(evt);
               }
               public void focusLost(java.awt.event.FocusEvent evt) {
                    passwordFocusLost(evt);
               }
          });
          password.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    passwordActionPerformed(evt);
               }
          });
          password.addKeyListener(new java.awt.event.KeyAdapter() {
               public void keyReleased(java.awt.event.KeyEvent evt) {
                    passwordKeyReleased(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents

     private void passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyReleased
          String pass = new String(password.getPassword());
          setValuePassword(pass);
     }//GEN-LAST:event_passwordKeyReleased

     private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained

         if( password.getText().trim().equals("Password")){
              password.setText("");
         }
     }//GEN-LAST:event_passwordFocusGained

     private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        
        if( password.getText().trim().equals("")){
              password.setText(textPassowrd);
              password.setForeground(WindowColor.lightGray);
         }
     }//GEN-LAST:event_passwordFocusLost

     private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
          // TODO add your handling code here:
     }//GEN-LAST:event_passwordActionPerformed


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPasswordField password;
     // End of variables declaration//GEN-END:variables

     public String getValuePassword() {
          return valuePassword;
     }

     public void setValuePassword(String valuePassword) {
          this.valuePassword = valuePassword;
          password.setText(valuePassword);
          password.setForeground(WindowColor.black);
     }

    public String getTextPassowrd() {
        return textPassowrd;
    }

    public void setTextPassowrd(String textPassowrd) {
        this.textPassowrd = textPassowrd;
        password.setText(textPassowrd);
        password.setForeground(WindowColor.lightGray);
    }

}
