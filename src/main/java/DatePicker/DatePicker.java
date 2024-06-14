package DatePicker;

import Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

public class DatePicker extends javax.swing.JPanel {

    private String labelTextField;
    private String valueTextField;
    
    public DatePicker() {
        initComponents();
        setBackground(WindowColor.white);
        txtText.setFont(WindowFonts.timeNewRoman14);
        JavaConstant.setPointer(txtText);
    }
    
    //Create Placeholder
     public void initEvent(ButtonEvent event) {

          txtText.addFocusListener(new FocusListener() {
               @Override
               public void focusGained(FocusEvent e) {
                    if (txtText.getText().trim().equals(labelTextField)) {
                         txtText.setText("");
                    }else{
                        setValueTextField(txtText.getText());
                    }
                    txtText.setForeground(Color.BLACK);
               }

               @Override
               public void focusLost(FocusEvent e) {
                    if (txtText.getText().trim().equals("")) {
                         txtText.setText(labelTextField);
                         txtText.setForeground(Color.LIGHT_GRAY);
                    }

                    if (txtText.getText().trim().equals(labelTextField)) {
                         txtText.setForeground(Color.LIGHT_GRAY);
                    }
               }
          });
     }

    //=================================================Create Shadow Box
     private ShadowType shadowType;
     private int shadowSize = 1;
     private float shadowOpacity = 0.1f;
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

    public String getLabelTextField() {
        return labelTextField;
    }

    public void setLabelTextField(String labelTextField) {
        this.labelTextField = labelTextField;
        txtText.setText(labelTextField);
        txtText.setForeground(Color.LIGHT_GRAY);
    }

    public String getValueTextField() {
        return valueTextField;
    }

    public void setValueTextField(String valueTextField) {
        this.valueTextField = valueTextField;
        txtText.setText(valueTextField);
        txtText.setForeground(Color.BLACK);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        txtText = new javax.swing.JTextField();

        dateChooser1.setForeground(new java.awt.Color(47, 152, 70));
        dateChooser1.setTextRefernce(txtText);

        txtText.setBackground(new java.awt.Color(255, 255, 255));
        txtText.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtText.setBorder(null);
        txtText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTextActionPerformed(evt);
            }
        });
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyPressed

    }//GEN-LAST:event_txtTextKeyPressed

    private void txtTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyReleased

    }//GEN-LAST:event_txtTextKeyReleased

    private void txtTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextKeyTyped

    }//GEN-LAST:event_txtTextKeyTyped

    private void txtTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JTextField txtText;
    // End of variables declaration//GEN-END:variables
}
