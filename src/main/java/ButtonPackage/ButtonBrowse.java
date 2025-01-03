 
package ButtonPackage;

 
import Constant.JavaConstant;
import Components.Event.ButtonEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ButtonBrowse extends javax.swing.JPanel {

     public ButtonBrowse() {
          initComponents();
//          btn.setBorder(new EmptyBorder(0,0,0,0));
          btn.setBackground(Color.white); 
          setBackground(Color.white);
          setForeground(Color.blue);
          JavaConstant.setPointer(btn);
     }

     public void initEvent(ButtonEvent event,int row) {

          btn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    event.onClick(row);
               }
          });
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          btn = new javax.swing.JButton();

          btn.setForeground(new java.awt.Color(0, 0, 255));
          btn.setText("Link");
          btn.setBorder(null);
          btn.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnMouseExited(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
          );
     }// </editor-fold>//GEN-END:initComponents

     private void btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseEntered
//          btn.setForeground(WindowColor.light_Blue);
//          btn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.light_Blue));
     }//GEN-LAST:event_btnMouseEntered

     private void btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseExited
//          btn.setForeground(WindowColor.darkBlue);
//          btn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
     }//GEN-LAST:event_btnMouseExited


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton btn;
     // End of variables declaration//GEN-END:variables
}
