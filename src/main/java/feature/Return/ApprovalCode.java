package feature.Return;

import Components.Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import feature.LoginAndLogoutForm.LoginFormJdailog;
import Model.Login.LoginModel;
import feature.Print.ReprintJdailog;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import okhttp3.Response;
import org.json.JSONObject;
import Button.Button;
import ButtonPackage.ButtonCancel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main_validation.JavaValidation;

public class ApprovalCode extends javax.swing.JDialog {

    private Button btnPayment;
    private LoginFormJdailog jdFormLogin;
    private String typeForm;
    private ButtonCancel btnCancel;
    private Button btnHold;
    private Button btnReturn;
    private Button btnDiscount;
    private JLabel titleOrder;
    private JPanel boxOne;

    private JPanel detailItem;
    private JPanel panelProduct;

    public JPanel getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(JPanel detailItem) {
        this.detailItem = detailItem;
    }

    public JPanel getPanelProduct() {
        return panelProduct;
    }

    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    public JPanel getBoxOne() {
        return boxOne;
    }

    public void setBoxOne(JPanel boxOne) {
        this.boxOne = boxOne;
    }

    public ApprovalCode(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelApproval.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        txtCode.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelApproval = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonLogin = new ButtonPackage.ButtonLogin();
        txtCode = new FormComponent.JavaTextField();
        txtPassword = new FormComponent.JavaTextFieldPassword();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Approval Code");

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

        txtCode.setLabelName("Code *");
        txtCode.setPlaceHolder("Code");

        txtPassword.setLabelName("Password *");

        javax.swing.GroupLayout panelApprovalLayout = new javax.swing.GroupLayout(panelApproval);
        panelApproval.setLayout(panelApprovalLayout);
        panelApprovalLayout.setHorizontalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelApprovalLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelApprovalLayout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelApprovalLayout.setVerticalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelApprovalLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelApproval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        String password = txtPassword.getValueTextField();

        try {

            boolean isCheck = JavaValidation.checkValidation(panelApproval);

            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("userCode", userCode);
                json.put("password", password);
                // json.put("deviceName", null);

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
                                returnD.setBtnPayment(btnPayment);
                                returnD.setBtnCancel(btnCancel);
                                returnD.setBtnHold(btnHold);
                                returnD.setBtnReturn(btnReturn);
                                returnD.setBtnDiscount(btnDiscount);
                                returnD.setDetailItem(detailItem);
                                returnD.setPanelProduct(panelProduct);
                                returnD.setBoxOne(boxOne);
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
            }

        } catch (Exception e) {
            System.err.println("err  = " + e);
        }
    }//GEN-LAST:event_buttonLoginMouseClicked

    public Button getBtnPayment() {
        return btnPayment;
    }

    public void setBtnPayment(Button btnPayment) {
        this.btnPayment = btnPayment;
    }


    /**
     * @param args the command line arguments
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

    public Button getBtnDiscount() {
        return btnDiscount;
    }

    public void setBtnDiscount(Button btnDiscount) {
        this.btnDiscount = btnDiscount;
    }

    public Button getBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(Button btnReturn) {
        this.btnReturn = btnReturn;
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

    public ButtonCancel getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ButtonCancel btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getBtnHold() {
        return btnHold;
    }

    public void setBtnHold(Button btnHold) {
        this.btnHold = btnHold;
    }

    public JLabel getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(JLabel titleOrder) {
        this.titleOrder = titleOrder;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonLogin buttonLogin;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private javax.swing.JPanel panelApproval;
    private FormComponent.JavaTextField txtCode;
    private FormComponent.JavaTextFieldPassword txtPassword;
    // End of variables declaration//GEN-END:variables
}
