package feature.staff.Staff;

import feature.LoginAndLogoutForm.model.RoleHasPermissionModel;
import feature.user_permission.JavaPermission;
import feature.user_permission.UserPermissionForm;
import javax.swing.JFrame;

public class Staff extends javax.swing.JDialog {

     public Staff(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          
//          btnUserPermission.setVisible(false);

               //checkPermission();
               checkPermission();
     }

     
       private void checkPermission() {

          boolean isStaffInformation = false;
          boolean isUserLogin = false;
          boolean isUserPermission = false;
 

          // parentId : 8 is primary key id from the table pos_permission
          for (RoleHasPermissionModel.RoleHasPermissionDetail data : JavaPermission.getPermissions(8)) {

               // Check each permission and set the corresponding flag
               String permissionName = data.getPermissionName().toLowerCase();

               switch (permissionName) {
                    case "staff information":
                         isStaffInformation = data.getIsVisible();
                         break;

                    case "user login":
                         isUserLogin = data.getIsVisible();
                         break;

                    case "user permission":
                         isUserPermission = data.getIsVisible();
                         break;

                   
                    default:
                         System.err.println("Unknown permission: " + data.getPermissionName());
                         break;
               }

          }

          // Set visibility for UI components
          btnStaffInfo.setVisible(isStaffInformation);
          btnUserlogin.setVisible(isUserLogin);
          btnUserPermission.setVisible(isUserPermission);
 
     }
     
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelReprint = new javax.swing.JPanel();
          btnStaffInfo = new Button.Button();
          btnUserlogin = new Button.Button();
          lbTitle = new Components.LabelPopUpTitle();
          btnUserPermission = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          panelReprint.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    panelReprintMouseClicked(evt);
               }
          });

          btnStaffInfo.setButtonName("Staff Information");
          btnStaffInfo.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnStaffInfoMouseClicked(evt);
               }
          });

          btnUserlogin.setBackground(new java.awt.Color(47, 155, 70));
          btnUserlogin.setButtonName("User Login");
          btnUserlogin.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnUserloginMouseClicked(evt);
               }
          });

          lbTitle.setLabelTitle("Staff");

          btnUserPermission.setButtonName("User Permission");
          btnUserPermission.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnUserPermissionMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelReprintLayout = new javax.swing.GroupLayout(panelReprint);
          panelReprint.setLayout(panelReprintLayout);
          panelReprintLayout.setHorizontalGroup(
               panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(panelReprintLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(btnStaffInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUserlogin, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnUserPermission, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
          );
          panelReprintLayout.setVerticalGroup(
               panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelReprintLayout.createSequentialGroup()
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(panelReprintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnUserlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnUserPermission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(68, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelReprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panelReprint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void btnStaffInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaffInfoMouseClicked
         
         dispose();
         StaffInformation staffInfo = new StaffInformation(new JFrame(), true);
         staffInfo.setVisible(true);
    }//GEN-LAST:event_btnStaffInfoMouseClicked

    private void btnUserloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserloginMouseClicked
         dispose();
         Userlogin user = new Userlogin(new JFrame(), true);
         user.setVisible(true);
    }//GEN-LAST:event_btnUserloginMouseClicked

    private void panelReprintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelReprintMouseClicked

    }//GEN-LAST:event_panelReprintMouseClicked

     private void btnUserPermissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserPermissionMouseClicked
        dispose();
        UserPermissionForm userPermissionForm = new UserPermissionForm(new JFrame(), true);
        userPermissionForm.setVisible(true);
     }//GEN-LAST:event_btnUserPermissionMouseClicked

     public static void main(String args[]) {
          /* Set the Nimbus look and feel */
          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
          /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
           */
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    Staff dialog = new Staff(new javax.swing.JFrame(), true);
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
     private Button.Button btnStaffInfo;
     private Button.Button btnUserPermission;
     private Button.Button btnUserlogin;
     private Components.LabelPopUpTitle lbTitle;
     private javax.swing.JPanel panelReprint;
     // End of variables declaration//GEN-END:variables
}
