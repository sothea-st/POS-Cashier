package Staff;

import Constant.JavaConnection;
import Constant.JavaRoute;
import ResponseException.ResponseChangePassByAdmin;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
 
import okhttp3.Response;
import org.json.JSONObject;

public class ChangeUserPassword extends javax.swing.JDialog {

     private String userCode;
     private int emId;

     public int getEmId() {
          return emId;
     }

     public void setEmId(int emId) {
          this.emId = emId;
     }

     public String getUserCode() {
          return userCode;
     }

     public void setUserCode(String userCode) {
          this.userCode = userCode;
          codeUser.setUneditText(userCode);
     }

     public ChangeUserPassword(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          newPass.setFocus();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          titlePopUp = new Components.LabelPopUpTitle();
          label1 = new Components.Label();
          label2 = new Components.Label();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          newPass = new Components.PasswordField();
          confirmPass = new Components.PasswordField();
          label3 = new Components.Label();
          codeUser = new Components.UnEditableTextField();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
          setIconImages(null);

          titlePopUp.setLabelTitle("Change User Password");

          label1.setLabelName("New Password");

          label2.setLabelName("Confirm Password");

          buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonCancelMouseClicked(evt);
               }
          });

          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          newPass.setTextPassowrd("Password");

          confirmPass.setTextPassowrd("Password");

          label3.setLabelName("User Code");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(204, 0, 0));
          jLabel11.setText("*");

          jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel12.setForeground(new java.awt.Color(204, 0, 0));
          jLabel12.setText("*");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGap(19, 19, 19)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(codeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, Short.MAX_VALUE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(6, 6, 6)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(newPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(confirmPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGap(16, 16, 16))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(codeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(newPass, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                              .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(confirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(18, 18, 18)
                              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(18, 18, 18))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String newPassString = newPass.getValuePassword();
         String confirmPasswordString = confirmPass.getValuePassword();

         if (newPassString == null || newPassString.isEmpty()) {
              JOptionPane.showMessageDialog(this, "New Password is required!");
              return;
         }

         if (confirmPasswordString == null || confirmPasswordString.isEmpty()) {
              JOptionPane.showMessageDialog(this, "confirm Password is required!");
              return;
         }

         JSONObject json = new JSONObject();
         json.put("empId", emId);
         json.put("newPassword", newPassString);
         json.put("confirmPassword", confirmPasswordString);
         Response response = JavaConnection.post(JavaRoute.changePasswordByAdmin, json);

         try {
              String responseData = response.body().string();
              ObjectMapper obj = new ObjectMapper();
              ResponseChangePassByAdmin data = obj.readValue(responseData, ResponseChangePassByAdmin.class);
              if (data.getMsg() != null) {
                   if (data.getMsg().equals("success")) {
                        dispose();
                        JOptionPane.showMessageDialog(this, data.getMsg());
                   } else {
                        JOptionPane.showMessageDialog(this, data.getMsg());
                   }
              } else {
                   JOptionPane.showMessageDialog(this, "<html>" + data.getNewPassword() + "</html>");
              }

         } catch (Exception e) {
              System.out.println("err = " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

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
               java.util.logging.Logger.getLogger(ChangeUserPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ChangeUserPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ChangeUserPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ChangeUserPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ChangeUserPassword dialog = new ChangeUserPassword(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                         @Override
                         public void windowClosing(java.awt.event.WindowEvent e) {
                              System.exit(0);
                         }
                    });
                       dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                    dialog.setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Components.UnEditableTextField codeUser;
     private Components.PasswordField confirmPass;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JPanel jPanel1;
     private Components.Label label1;
     private Components.Label label2;
     private Components.Label label3;
     private Components.PasswordField newPass;
     private Components.LabelPopUpTitle titlePopUp;
     // End of variables declaration//GEN-END:variables
}
