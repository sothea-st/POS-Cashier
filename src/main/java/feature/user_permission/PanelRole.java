package feature.user_permission;

import Components.Color.WindowColor;
import Constant.JavaConstant;
import Components.Event.ButtonEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class PanelRole extends javax.swing.JPanel {

     private Integer id;
     private boolean isCheck = false;

     public PanelRole() {
          initComponents();
          // call init
          init();
     }

     private void init() {
          panel.setBackground(WindowColor.mediumGreen);
          setBackground(WindowColor.mediumGreen);
          JavaConstant.setPointer(btnCheck);
     }

     public void setText(String text) {
          permissionName.setText(text);
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public void initEvent(ButtonEvent event) {
          btnCheck.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    System.err.println("id : permissionID ; " + id);
                    event.onMouseClick(String.valueOf(id));
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

     public void setChecked() {
          btnCheck.setIcon(new ImageIcon(getClass().getResource("/icon/checked.png")));
     }

     public void setUnCheck() {
          btnCheck.setIcon(new ImageIcon(getClass().getResource("/icon/check.png")));
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          btnCheck = new javax.swing.JLabel();
          permissionName = new javax.swing.JLabel();

          btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
          btnCheck.setPreferredSize(new java.awt.Dimension(20, 20));

          permissionName.setText("jLabel1");
          permissionName.setPreferredSize(new java.awt.Dimension(37, 20));

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(permissionName, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addContainerGap())
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(permissionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnCheck;
     private javax.swing.JPanel panel;
     private javax.swing.JLabel permissionName;
     // End of variables declaration//GEN-END:variables
}
