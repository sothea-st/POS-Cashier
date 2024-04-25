package Return;

import Button.Button;
import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Controller.ActionScanBarcodeAddProduct.ActionScanBarcodeAddProduct;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.Package.ReasonModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import Button.Button;
import ButtonPackage.ButtonCancel;
import View.MainPage.MainPage;
import javax.swing.JPanel;

/**
 *
 * @author FRONT-END.06
 */
public class ReturnDialog extends javax.swing.JDialog {

     private String reasonId;
     private LoginFormJdailog jdFormLogin;
     private Button btnPayment;
     private ButtonCancel btnCancel;
     private Button btnHold;
     private Button btnReturn;
     private Button btnDiscount;
     
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
     
     

     public ReturnDialog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelReturn.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          addComboReason();
          txtinvoice.requestFocus();
          eventSelectReason();

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    String value = txtinvoice.getValueTextField();
//                    String barcodeValue = value.substring(2); // this working with device scanner 
                    Response responseData = JavaConnection.get(JavaRoute.getInvoice + value);
                    try {
                         String _data = responseData.body().string();
                         JSONObject obj = new JSONObject(_data);
                         String invoice = obj.getString("data");
                         txtinvoice.setValueTextField(invoice);
                    } catch (Exception e) {
                         System.err.println("response data 333= " + e);
                    }
               }
          };

          txtinvoice.initEvent(event);

     }

     void eventSelectReason() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    reasonId = key;
               }
          };
          comboBoxReason.initEvent(event);
     }

     //Action call function placeholder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtinvoice.initEvent(btnevent);
          txtBarcode.initEvent(btnevent);
     }

     private void addComboReason() {
          HashMap<String, String> map = new HashMap<>();
          try {
               ArrayList<ReasonModel> reason = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.reason + "return");
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         ReasonModel modelReason = new ReasonModel(
                              obj.getInt("id"),
                              obj.getString("reason")
                         );
                         reason.add(modelReason);
                         int idReason = reason.get(i).getIdReason();
                         String reasonName = reason.get(i).getReason();
                         map.put(reasonName, "" + idReason);
                    }
                    comboBoxReason.setMap(map);

               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReturn = new javax.swing.JPanel();
        labelPopUpTitle = new Components.LabelPopUpTitle();
        lbInvoiceNo = new Components.Label();
        lbBrcode = new Components.Label();
        lbReason = new Components.Label();
        txtinvoice = new Components.TextField();
        txtBarcode = new Components.TextField();
        comboBoxReason = new Components.ComboBox();
        buttonCancel = new ButtonPackage.ButtonCancel();
        button1 = new Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle.setLabelTitle("Return");

        lbInvoiceNo.setLabelName("Invoice №");

        lbBrcode.setLabelName("Barcode");

        lbReason.setLabelName("Reason");

        txtinvoice.setLabelTextField("Scan or input");

        txtBarcode.setLabelTextField("Scan or input");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        button1.setBackground(new java.awt.Color(47, 152, 70));
        button1.setButtonName("Search");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        javax.swing.GroupLayout panelReturnLayout = new javax.swing.GroupLayout(panelReturn);
        panelReturn.setLayout(panelReturnLayout);
        panelReturnLayout.setHorizontalGroup(
            panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelReturnLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelReturnLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(panelReturnLayout.createSequentialGroup()
                        .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelReturnLayout.createSequentialGroup()
                                .addComponent(lbInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelReturnLayout.createSequentialGroup()
                                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbBrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3)))
                        .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelReturnLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(txtinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReturnLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxReason, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))))))
        );
        panelReturnLayout.setVerticalGroup(
            panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReturnLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtinvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBrcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    
    
    
    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
         String barcode = txtBarcode.getValueTextField();
         String invoiceNo = txtinvoice.getValueTextField();

         if (invoiceNo == null || invoiceNo.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Invoice № can not be empty!");
              return;
         }

         if (reasonId == null) {
              JOptionPane.showMessageDialog(this, "Please select a reason!");
              return;
         }

         ActionScanBarcodeAddProduct obj = new ActionScanBarcodeAddProduct();
         
         if (barcode != null) {
              obj.scanBarcode(barcode, jdFormLogin);
         } else {
              obj.setBtnPayment(btnPayment);
              obj.setBtnReturn(btnReturn);
              obj.scanWithoutReturn(invoiceNo, jdFormLogin);
         }

         btnCancel.setBackground(WindowColor.lightGray);
         btnHold.setBackground(WindowColor.lightGray);
//         btnDiscount.setBackground(WindowColor.lightGray);


        
         JavaConstant.reasonId = reasonId;
         JavaConstant.invoiceNo = invoiceNo;
         dispose();
    }//GEN-LAST:event_button1MouseClicked

     public void setResetReturn() {
          JavaConstant.isReturn = null;
          JavaConstant.reasonId = null;
          JavaConstant.invoiceNo = null;
          JavaConstant.returnerId = null;
     }

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
               java.util.logging.Logger.getLogger(ReturnDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ReturnDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ReturnDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ReturnDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReturnDialog dialog = new ReturnDialog(new javax.swing.JFrame(), true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button button1;
    private ButtonPackage.ButtonCancel buttonCancel;
    private Components.ComboBox comboBoxReason;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private Components.LabelPopUpTitle labelPopUpTitle;
    private Components.Label lbBrcode;
    private Components.Label lbInvoiceNo;
    private Components.Label lbReason;
    private javax.swing.JPanel panelReturn;
    private Components.TextField txtBarcode;
    private Components.TextField txtinvoice;
    // End of variables declaration//GEN-END:variables
}
