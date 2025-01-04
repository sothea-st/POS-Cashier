package Components;

import Components.Color.WindowColor;
import Constant.JavaConstant;
import Constant.UtilShadow;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 

public class LabelFontGreen extends javax.swing.JPanel {

     public String getLabelName() {
          return labelName;
     }

     public void setLabelName(String labelName) {
          this.labelName = labelName;
          lbLabel.setText(labelName);
     }

     public LabelFontGreen() {
          initComponents();
          setBackground(WindowColor.white);
          lbLabel.setFont(WindowFonts.timeNewRomanBold14);
          lbLabel.setForeground(WindowColor.darkGreen);
          JavaConstant.setPointer(lbLabel);
     }

     public void setTextColor(Color color){
          lbLabel.setForeground(color);
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

     //=====================Create Shadow Box============================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadow(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
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
