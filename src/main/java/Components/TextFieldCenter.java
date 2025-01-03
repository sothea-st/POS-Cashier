package Components;

import Components.Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author FRONT-END.06
 */
public class TextFieldCenter extends javax.swing.JPanel {

     private String labelTextCenter;
     private String valueTextFieldCenter;

     //Create Placeholder
     public void initEvent(ButtonEvent event) {
          txtTextCenter.addFocusListener(new FocusListener() {
               @Override
               public void focusGained(FocusEvent e) {
                    if (txtTextCenter.getText().trim().equals(labelTextCenter)) {
                         txtTextCenter.setText("");
                    }
                    txtTextCenter.setForeground(Color.BLACK);
               }

               @Override
               public void focusLost(FocusEvent e) {
                    if (txtTextCenter.getText().trim().equals("")) {
                         txtTextCenter.setText(labelTextCenter);
                         txtTextCenter.setForeground(Color.LIGHT_GRAY);
                    }

                    if (txtTextCenter.getText().trim().equals(labelTextCenter)) {
                         txtTextCenter.setForeground(Color.LIGHT_GRAY);
                    }
               }
          });
          
          
          txtTextCenter.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {

               }

               @Override
               public void keyPressed(KeyEvent e) {

               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String text = txtTextCenter.getText();
                    setValueTextFieldCenter(text);
                    event.onKeyRelease();
               }
          });
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

     /**
      * Creates new form TextFieldKh
      */
     public TextFieldCenter() {
          initComponents();
          setBackground(WindowColor.white);
          txtTextCenter.setFont(WindowFonts.timeNewRoman14);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTextCenter = new javax.swing.JTextField();

        txtTextCenter.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTextCenter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTextCenter.setBorder(null);
        txtTextCenter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTextCenterFocusGained(evt);
            }
        });
        txtTextCenter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTextCenterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTextCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTextCenter)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

     public String getLabelTextCenter() {
          return labelTextCenter;
     }

     public void setLabelTextCenter(String labelTextCenter) {
          this.labelTextCenter = labelTextCenter;
          txtTextCenter.setText(labelTextCenter);
          txtTextCenter.setForeground(Color.LIGHT_GRAY);
     }

     public String getValueTextFieldCenter() {
          return valueTextFieldCenter;
     }

     public void setValueTextFieldCenter(String valueTextFieldCenter) {
          this.valueTextFieldCenter = valueTextFieldCenter;
          txtTextCenter.setText(valueTextFieldCenter);
          txtTextCenter.setForeground(Color.BLACK);
     }

    private void txtTextCenterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTextCenterFocusGained
         // TODO add your handling code here:
    }//GEN-LAST:event_txtTextCenterFocusGained

    private void txtTextCenterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextCenterKeyReleased
         String text = txtTextCenter.getText();
         setValueTextFieldCenter(text);
    }//GEN-LAST:event_txtTextCenterKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField txtTextCenter;
    // End of variables declaration//GEN-END:variables
}
