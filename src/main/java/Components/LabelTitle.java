package Components;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author FRONT-END.06
 */
public class LabelTitle extends javax.swing.JPanel {

     /**
      * @return the labelTitle
      */
     public String getLabelTitle() {
          return labelTitle;
     }

     /**
      * @param labelTitle the labelTitle
      * to set
      */
     private String lbCatId;

     public String getLbCatId() {
          return lbCatId;
     }

     public void setLbCatId(String lbCatId) {
          this.lbCatId = lbCatId;

     }

     public void setLabelTitle(String labelTitle) {
          this.labelTitle = labelTitle;
          lbTitle.setText(labelTitle);

     }

     public void initEvent(ButtonEvent event) {
          lbTitle.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onMouseClick();
               }

               @Override
               public void mousePressed(MouseEvent e) {

               }

               @Override
               public void mouseReleased(MouseEvent e) {

               }

               @Override
               public void mouseEntered(MouseEvent e) {

               }

               @Override
               public void mouseExited(MouseEvent e) {

               }
          });
     }

     /**
      * Creates new form LabelTitle
      */
     public LabelTitle() {
          initComponents();
          setBackground(WindowColor.darkGreen);
          lbTitle.setFont(WindowFonts.timeNewRomanBold12);
          lbTitle.setForeground(WindowColor.white);
//          lbTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          JavaConstant.setPointer(lbTitle);
          setSize(new Dimension(115, 41));
          lbTitle.setSize((new Dimension(115, 41)));
          lbTitle.setBorder(new EmptyBorder(5, 0, 0, 0)); // Top margin of 10 pixels
     }

     public void textCenter() {
          lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbTitle = new javax.swing.JLabel();

          lbTitle.setForeground(new java.awt.Color(255, 255, 255));
          lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTitle.setText("jLabel1");
          lbTitle.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lbTitleMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents

    private void lbTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTitleMouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_lbTitleMouseClicked

     private String labelTitle;
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel lbTitle;
     // End of variables declaration//GEN-END:variables
}
