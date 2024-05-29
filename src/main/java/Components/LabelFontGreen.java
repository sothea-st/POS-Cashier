package Components;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author FRONT-END.06
 */
public class LabelFontGreen extends javax.swing.JPanel {

     /**
      * @return the labelName
      */
     public String getLabelName() {
          return labelName;
     }

     /**
      * @param labelName the labelName to set
      */
     public void setLabelName(String labelName) {
          this.labelName = labelName;
          lbLabel.setText(labelName);
     }

     /**
      * Creates new form LabelFontGreen
      */
     public LabelFontGreen() {
          initComponents();
          setBackground(WindowColor.white);
          lbLabel.setFont(WindowFonts.timeNewRomanBold14);
          lbLabel.setForeground(WindowColor.darkGreen);
          JavaConstant.setPointer(lbLabel);
     }

     public void initEvent(ButtonEvent event) {
          lbLabel.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onMouseClick();
               }

               @Override
               public void mousePressed(MouseEvent e) {
                    System.out.println("");
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    System.out.println("");
               }

               @Override
               public void mouseEntered(MouseEvent e) {
                    System.out.println("");
               }

               @Override
               public void mouseExited(MouseEvent e) {
                    System.out.println("");
               }

          });
     }

     // =================================================Create Shadow Box
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
               // Center
               x = shadowSize;
               y = shadowSize;
          }
          BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g = img.createGraphics();
          g.setColor(getBackground());
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g.fillRoundRect(0, 0, width, height, 10, 10);

          // Create Shadow
          ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
          g2.drawImage(render.createShadow(img), 0, 0, null);
          g2.drawImage(img, x, y, null);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLabel = new javax.swing.JLabel();

        lbLabel.setForeground(new java.awt.Color(255, 255, 255));
        lbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLabel.setText("jLabel1");
        lbLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     private void lbLabelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lbLabelMouseClicked
          // TODO add your handling code here:
     }// GEN-LAST:event_lbLabelMouseClicked

     private String labelName;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbLabel;
    // End of variables declaration//GEN-END:variables
}
