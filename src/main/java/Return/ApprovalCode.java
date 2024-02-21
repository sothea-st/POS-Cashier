package Return;

import Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.Login.LoginModel;
import Print.ReprintJdailog;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import okhttp3.Response;
import org.json.JSONObject;

public class ApprovalCode extends javax.swing.JDialog {

     private LoginFormJdailog jdFormLogin;
     private String typeForm;

     public ApprovalCode(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelApproval.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          txtCode.requestFocus();
          txtPassword.setTextPassowrd("Password");
     }

     //Action call function placeholder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtCode.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelApproval = new javax.swing.JPanel();
        lbPassword = new Components.Label();
        lbCode = new Components.Label();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        txtCode = new Components.TextField();
        txtPassword = new Components.PasswordField();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonLogin = new ButtonPackage.ButtonLogin();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbPassword.setLabelName("Password");

        lbCode.setLabelName("Code");

        labelPopUpTitle1.setLabelTitle("Approval Code");

        txtCode.setLabelTextField("Code");

        txtPassword.setTextPassowrd("Password");
        txtPassword.setValuePassword("");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        buttonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelApprovalLayout = new javax.swing.GroupLayout(panelApproval);
        panelApproval.setLayout(panelApprovalLayout);
        panelApprovalLayout.setHorizontalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelApprovalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelApprovalLayout.createSequentialGroup()
                        .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbCode, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34))
                    .addGroup(panelApprovalLayout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(panelApprovalLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        panelApprovalLayout.setVerticalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelApprovalLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelApproval, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLoginMouseClicked

         String userCode = txtCode.getValueTextField();
         String password = txtPassword.getValuePassword();

         if (userCode == null || userCode.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Code can not be empty!");
              return;
         }

         if (password == null || password.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Password can not be empty!");
              return;
         }

         JSONObject json = new JSONObject();
         json.put("userCode", userCode);
         json.put("password", password);
//         json.put("deviceName", null);

         Response response = JavaConnection.login(JavaRoute.login, json);

         try {
              String data = response.body().string();
              if (response.isSuccessful()) {
                   ObjectMapper objMap = new ObjectMapper();
                   LoginModel model = objMap.readValue(data, LoginModel.class);

                   if (model.getRoleName().equals(JavaConstant.supervisor)
                        || model.getRoleName().equals(JavaConstant.admin)) {
                        this.dispose();
                        if (typeForm.equals("return")) {
                             ReturnDialog returnD = new ReturnDialog(new JFrame(), true);
                             returnD.setJdFormLogin(jdFormLogin);
                             returnD.setVisible(true);
                             JavaConstant.returnerId = model.getID();
                        } else if (typeForm.equals("reprint")) {
                             ReprintJdailog rep = new ReprintJdailog(new JFrame(), true);
                             rep.setTitle("Reprint Invoice");
                             rep.setTextButtonLeft("Reprint by Last");
                             rep.setTextButtonRight("Reprint by Invoice №");
                             rep.setTypeForm("reprint");
                             rep.setVisible(true);
                        }
                   } else {
                        JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                        j.setMessage("You have no permission use this function!");
                        j.setVisible(true);
                   }
              } else {
                   JOptionPane.showMessageDialog(this, "Wrong code or password!");
                   return;
              }
         } catch (Exception e) {
              System.err.println("err  = " + e);
         }

    }//GEN-LAST:event_buttonLoginMouseClicked

     /**
      * @param args the command line
      * arguments
      */
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
               java.util.logging.Logger.getLogger(ApprovalCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ApprovalCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ApprovalCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ApprovalCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ApprovalCode dialog = new ApprovalCode(new javax.swing.JFrame(), true);
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

     public LoginFormJdailog getJdFormLogin() {
          return jdFormLogin;
     }

     public void setJdFormLogin(LoginFormJdailog jdFormLogin) {
          this.jdFormLogin = jdFormLogin;
     }

     public String getTypeForm() {
          return typeForm;
     }

     public void setTypeForm(String typeForm) {
          this.typeForm = typeForm;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonLogin buttonLogin;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbCode;
    private Components.Label lbPassword;
    private javax.swing.JPanel panelApproval;
    private Components.TextField txtCode;
    private Components.PasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
