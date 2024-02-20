package OpenAndCloseShift;

import Button.Button;
import Color.WindowColor;
import Components.LabelTitle;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionProduct.ActionProduct;
import DefaultPrice.DataModelDefaultPrice;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import LoginAndLogoutForm.LoginFormJdailog;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author FRONT-END.06
 */
public class OpenShiftJdailog extends javax.swing.JDialog {

     private Button btnOpenShift;
     private LoginFormJdailog jdLoginForm;
     private DataModelDefaultPrice dataSuccess;
     private JPanel category;
     private JPanel panelProduct;
     private LoginFormJdailog jdFormLogin;
     private int limit;
     private JPanel panelPagination;

     public OpenShiftJdailog(java.awt.Frame parent, boolean modal, Button btnOpenShift) {
          super(parent, modal);
          initComponents();
          panelOpenShift.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          currenDateTime();
          event();
          setText();
          this.btnOpenShift = btnOpenShift;
          txtTotalUsd.requestFocus();
     }

     private void currenDateTime() {
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
          LocalDateTime date = LocalDateTime.now();
          txtDateTime.setUneditText(dtf.format(date));
     }

     private void setText() {
          txtCashierName.setUneditText(JavaConstant.fullName);
          txtUserId.setUneditText(JavaConstant.userCode);
          txtPosId.setUneditText(JavaConstant.posId);
     }

     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtTotalUsd.initEvent(btnevent);
          txtTotalKhr.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpenShift = new javax.swing.JPanel();
        txtTotalUsd = new Components.TextField();
        txtPosId = new Components.UnEditableTextField();
        txtUserId = new Components.UnEditableTextField();
        txtDateTime = new Components.UnEditableTextField();
        txtCashierName = new Components.UnEditableTextField();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbPosId = new Components.Label();
        IbUserId = new Components.Label();
        lbOpenDate = new Components.Label();
        lbTotalUsd = new Components.Label();
        lbCashier = new Components.Label();
        LbTotalKhr = new Components.Label();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        txtTotalKhr = new Components.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelOpenShift.setForeground(new java.awt.Color(0, 0, 0));

        txtTotalUsd.setLabelTextField("$ 0.00");

        txtPosId.setUneditText("01");

        txtUserId.setUneditText("0006");

        txtDateTime.setUneditText("02-01- 2024 03:50:00 PM");

        txtCashierName.setUneditText("KIMSRON");

        labelPopUpTitle1.setLabelTitle("Open Shift");

        lbPosId.setLabelName("POS ID");

        IbUserId.setLabelName("User ID");

        lbOpenDate.setLabelName("Open Date / Time");

        lbTotalUsd.setLabelName("Total Cash ( USD)");

        lbCashier.setLabelName("Cashier Name");

        LbTotalKhr.setLabelName("Total Cash ( KHR)");

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

        txtTotalKhr.setLabelTextField("0.00");

        javax.swing.GroupLayout panelOpenShiftLayout = new javax.swing.GroupLayout(panelOpenShift);
        panelOpenShift.setLayout(panelOpenShiftLayout);
        panelOpenShiftLayout.setHorizontalGroup(
                panelOpenShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addGroup(panelOpenShiftLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                                                .addComponent(LbTotalKhr,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txtTotalKhr,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 273,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                                                .addComponent(lbTotalUsd,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(txtTotalUsd,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 273,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpenShiftLayout
                                                .createSequentialGroup()
                                                .addGroup(panelOpenShiftLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(buttonCancel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(buttonSave,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                                                .addGroup(panelOpenShiftLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(lbPosId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                65,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(IbUserId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                0, Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(panelOpenShiftLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(txtPosId,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                151, Short.MAX_VALUE)
                                                                        .addComponent(txtUserId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                0, Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(panelOpenShiftLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(lbOpenDate,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(lbCashier,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelOpenShiftLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtDateTime,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                0, Short.MAX_VALUE)
                                                                        .addComponent(txtCashierName,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                0, Short.MAX_VALUE))))
                                                .addGap(14, 14, 14))))
                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 630,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        panelOpenShiftLayout.setVerticalGroup(
                panelOpenShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOpenShiftLayout.createSequentialGroup()
                                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPosId, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDateTime, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbPosId, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbOpenDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCashierName, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUserId, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(IbUserId, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCashier, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTotalUsd, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTotalUsd, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LbTotalKhr, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTotalKhr, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelOpenShiftLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelOpenShift, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelOpenShift, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonCancelMouseClicked
          this.dispose();
     }// GEN-LAST:event_buttonCancelMouseClicked
     ActionProduct pro = new ActionProduct();

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_buttonSaveMouseClicked

          String reserveUsd = txtTotalUsd.getValueTextField();
          String reserveKhr = txtTotalKhr.getValueTextField();
          String posId = txtPosId.getUneditText();
          String userCode = txtUserId.getUneditText();
          String openTime = txtDateTime.getUneditText();

          JSONObject json = new JSONObject();
          json.put("reserveUsd", reserveUsd);
          json.put("reserveKhr", reserveKhr);
          json.put("posId", posId);
          json.put("userCode", userCode);
          json.put("openTime", openTime);
          json.put("createBy", JavaConstant.cashierId);

          try {
               Response response = JavaConnection.post(JavaRoute.openShift, json);
               if (response.isSuccessful()) {
                    dispose();
                    btnOpenShift.setButtonName(JavaConstant.closeShift);
                    // jdLoginForm.setCheckOpenShift(true);
                    JavaConstant.checkOpenShift = true;
                    JavaConstant.checkCloseShift = 1l;

                    if (JavaConstant.checkOpenShift) {
                         System.err.println("dddddddddddddd");
                         Component[] listCom = category.getComponents();
                         listCom[0].setBackground(WindowColor.black);
                         String id = ((LabelTitle) listCom[0]).getLbCatId();
                         panelProduct.removeAll();
                         pro.product(Integer.parseInt(id), limit, panelProduct);
                         pro.setPanelProduct(panelProduct);
                         panelProduct.revalidate();
                         panelProduct.repaint();
                    } else {
                            System.err.println("nnnnnnnnnnnnn");
                    }

               } else {
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", WindowColor.mediumGreen);
                    UI.put("Panel.background", WindowColor.mediumGreen);
                    UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);
                    JOptionPane.showMessageDialog(null, "Save Failed!");

               }

          } catch (Exception e) {

          }
     }// GEN-LAST:event_buttonSaveMouseClicked

     public DataModelDefaultPrice getDataSuccess() {
          return dataSuccess;
     }

     public void setDataSuccess(DataModelDefaultPrice dataSuccess) {
          this.dataSuccess = dataSuccess;
          txtTotalUsd.setForeground(Color.red);
          assignValue(dataSuccess);
     }

     private void assignValue(DataModelDefaultPrice dataSuccess) {
          try {
               txtTotalUsd.setValueTextField("" + dataSuccess.getData()[0].getDefaultPriceUsd());
               txtTotalKhr.setValueTextField("" + dataSuccess.getData()[0].getDefaultPriceKhr());
          } catch (Exception e) {
               System.err.println("getting error at " + e);
          }
     }

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     public int getLimit() {
          return limit;
     }

     public void setLimit(int limit) {
          this.limit = limit;
     }

     public JPanel getCategory() {
          return category;
     }

     public void setCategory(JPanel category) {
          this.category = category;
     }

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public LoginFormJdailog getJdFormLogin() {
          return jdFormLogin;
     }

     public void setJdFormLogin(LoginFormJdailog jdFormLogin) {
          this.jdFormLogin = jdFormLogin;
     }

     /**
      * @param args the command line
      * arguments
      */
     public static void main(String args[]) {
          /* Set the Nimbus look and feel */
          // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
          // (optional) ">
          /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
           */
          try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
               }
          } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(OpenShiftJdailog.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(OpenShiftJdailog.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(OpenShiftJdailog.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(OpenShiftJdailog.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
          }
          // </editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    OpenShiftJdailog dialog = new OpenShiftJdailog(new javax.swing.JFrame(), true, null);
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

     public LoginFormJdailog getJdLoginForm() {
          return jdLoginForm;
     }

     public void setJdLoginForm(LoginFormJdailog jdLoginForm) {
          this.jdLoginForm = jdLoginForm;
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Label IbUserId;
    private Components.Label LbTotalKhr;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbCashier;
    private Components.Label lbOpenDate;
    private Components.Label lbPosId;
    private Components.Label lbTotalUsd;
    private javax.swing.JPanel panelOpenShift;
    private Components.UnEditableTextField txtCashierName;
    private Components.UnEditableTextField txtDateTime;
    private Components.UnEditableTextField txtPosId;
    private Components.TextField txtTotalKhr;
    private Components.TextField txtTotalUsd;
    private Components.UnEditableTextField txtUserId;
    // End of variables declaration//GEN-END:variables
}
