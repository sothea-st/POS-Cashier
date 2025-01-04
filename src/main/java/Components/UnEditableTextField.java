package Components;

import Components.Color.WindowColor;
import Components.Shadow.ShadowRenderer;
import Components.Shadow.ShadowType;
import Components.Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author FRONT-END.06
 */
public class UnEditableTextField extends javax.swing.JPanel {

     private String labelTextField;
     private String valueTextField;
     
     


     /**
      * Creates new form
      * EditableTextField
      */
     public UnEditableTextField() {
          initComponents();
          setBackground(WindowColor.white);
          textField.setFont(WindowFonts.timeNewRoman14);
          textField.setBackground(WindowColor.white);
          textField.setForeground(WindowColor.dark);
     }

     public String getLabelTextField() {
          return labelTextField;
     }

     public void setLabelTextField(String labelTextField) {
          this.labelTextField = labelTextField;
          textField.setText(labelTextField);
     }

     public String getValueTextField() {
          return valueTextField;
     }

     public void setValueTextField(String valueTextField) {
          this.valueTextField = valueTextField;
     }

     
     
     /**
      * @return the uneditText
      */
     public String getUneditText() {
          return uneditText;
     }

     /**
      * @param uneditText the uneditText
      * to set
      */
     public void setUneditText(String uneditText) {
          this.uneditText = uneditText;
          textField.setText(uneditText);
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

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          textField = new javax.swing.JTextField();

          textField.setEditable(false);
          textField.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
          textField.setBorder(null);
          textField.setFocusable(false);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textField)
                    .addContainerGap())
          );
     }// </editor-fold>//GEN-END:initComponents

     private String uneditText;

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JTextField textField;
     // End of variables declaration//GEN-END:variables
}
