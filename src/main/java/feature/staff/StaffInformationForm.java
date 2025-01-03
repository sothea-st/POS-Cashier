package feature.staff;

import feature.staff.Staff.StaffInformation;
import feature.staff.Staff.Userlogin;
import feature.user_permission.UserPermissionForm;
import javax.swing.JFrame;

public class StaffInformationForm extends javax.swing.JDialog {

     public StaffInformationForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
         
          initComponents();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          jPanel1 = new javax.swing.JPanel();
          objStaffInformation = new Components.SettingBox();
          objUserLogin = new Components.SettingBox();
          objUserPermission = new Components.SettingBox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setTitle("Staff");

          jScrollPane1.setBorder(null);

          objStaffInformation.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/staff/staff_info.jpg"))); // NOI18N
          objStaffInformation.setTitle("Staff Information");
          objStaffInformation.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objStaffInformationMouseClicked(evt);
               }
          });

          objUserLogin.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/staff/user_login.png"))); // NOI18N
          objUserLogin.setTitle("User Login");
          objUserLogin.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objUserLoginMouseClicked(evt);
               }
          });

          objUserPermission.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/staff/user_permission.png"))); // NOI18N
          objUserPermission.setTitle("User Permission");
          objUserPermission.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objUserPermissionMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(objStaffInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(objUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(objUserPermission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(185, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objStaffInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objUserPermission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(391, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(jPanel1);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane1)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1)
                    .addGap(15, 15, 15))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void objStaffInformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objStaffInformationMouseClicked
          dispose();
          StaffInformation staffInfo = new StaffInformation(new JFrame(), true);
          staffInfo.setVisible(true);
     }//GEN-LAST:event_objStaffInformationMouseClicked

     private void objUserLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objUserLoginMouseClicked
          dispose();
          Userlogin user = new Userlogin(new JFrame(), true);
          user.setVisible(true);
     }//GEN-LAST:event_objUserLoginMouseClicked

     private void objUserPermissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objUserPermissionMouseClicked
          dispose();
          UserPermissionForm userPermissionForm = new UserPermissionForm(new JFrame(), true);
          userPermissionForm.setVisible(true);
     }//GEN-LAST:event_objUserPermissionMouseClicked

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    StaffInformationForm dialog = new StaffInformationForm(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                         @Override
                         public void windowClosing(java.awt.event.WindowEvent e) {
                              System.exit(0);
                         }
                    });
                    dialog.setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.SettingBox objStaffInformation;
     private Components.SettingBox objUserLogin;
     private Components.SettingBox objUserPermission;
     // End of variables declaration//GEN-END:variables
}
