package DeleteAndCancel;

import Button.Button;
import ButtonPackage.ButtonCancel;
import Color.WindowColor;
import Components.BoxItem;
import Components.SubtotalPanel;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.CustomerType.CustomerTypeModel;
import Model.Package.ReasonModel;
import Model.PackageProduct.ProductIDModel;
import Model.Sale.ProductSaleModel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.BevelBorder;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author FRONT-END.06
 */
public class CancelDialog extends javax.swing.JDialog {

     private JPanel detailItem;
     private SubtotalPanel totalPanel;
     private Button btnPayment;
     private HashMap<String, String> map = new HashMap<>();
     private String reasonId;
     private Component[] listCom;
     private ButtonCancel btnCancel;
     private Button buttonHoldOrder;

     /**
      * Creates new form DeleteDialog
      */
     public CancelDialog(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          panelCancel.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          addComboReason();
          // action get select customer type
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    reasonId = key;
               }
          };
          comboBoxReason.initEvent(events);
     }

     private void addComboReason() {

          try {
               ArrayList<ReasonModel> reason = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.reason + "cancel");
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

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCancel = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbReason = new Components.Label();
        comboBoxReason = new Components.ComboBox();
        cancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Cancel");

        lbReason.setLabelName("Reason");

        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        javax.swing.GroupLayout panelCancelLayout = new javax.swing.GroupLayout(panelCancel);
        panelCancel.setLayout(panelCancelLayout);
        panelCancelLayout.setHorizontalGroup(
            panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCancelLayout.createSequentialGroup()
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCancelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                    .addGroup(panelCancelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        panelCancelLayout.setVerticalGroup(
            panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCancelLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         JSONObject jsonData = new JSONObject();

         ArrayList<ProductIDModel> listCancelDetail = new ArrayList<>();
         for (int i = 0; i < listCom.length; i++) {
              var obj = ((BoxItem) listCom[i]);
              ProductIDModel pro = new ProductIDModel(
                   obj.getProductId()
              );
              listCancelDetail.add(pro);
         }

         jsonData.put("listCancelDetail", listCancelDetail);
         jsonData.put("reasonId", reasonId);
         jsonData.put("createBy", JavaConstant.cashierId);

         try {

              if (reasonId == null) {
                   JOptionPane.showMessageDialog(this, "Please select a reason!");
                   return;
              }

              Response response = JavaConnection.post(JavaRoute.cancelAndDelete + "cancel", jsonData);
              if (response.isSuccessful()) {
                   this.dispose();
                   detailItem.removeAll();
                   detailItem.revalidate();
                   detailItem.repaint();
                   clearTotal();
                   changeColorButtonPayment();
              } else {
                   JOptionPane.showMessageDialog(this, "Save Failed!");
                   return;
              }

         } catch (Exception e) {

         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     void changeColorButtonPayment() {
          Component[] listCom1 = detailItem.getComponents();
          if (listCom1.length == 0) {
               btnPayment.setBackground(WindowColor.lightGray);
               btnCancel.setBackground(WindowColor.lightGray);
               buttonHoldOrder.setBackground(WindowColor.lightGray);
               getDetailItem().setBorder(null);
          }
     }

     void clearTotal() {
          totalPanel.setLabelSubtotalKhr("0");
          totalPanel.setLabelSubtotalUsd("$ 0.00");
          totalPanel.setLableDiscountKhr("0");
          totalPanel.setLableDiscountUsd("$ 0.00");
          totalPanel.setLableDeliveryUsd("$ 0.00");
          totalPanel.setLableTotalKhr("0");
          totalPanel.setLableTotalUsd("$ 0.00");
          totalPanel.setLableDeliveryKhr("0");
          totalPanel.setLableDeliveryUsd("$ 0.00");
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
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(CancelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    CancelDialog dialog = new CancelDialog(new javax.swing.JFrame(), true);
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

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }

     public SubtotalPanel getTotalPanel() {
          return totalPanel;
     }

     public void setTotalPanel(SubtotalPanel totalPanel) {
          this.totalPanel = totalPanel;
     }

     public Button getBtnPayment() {
          return btnPayment;
     }

     public void setBtnPayment(Button btnPayment) {
          this.btnPayment = btnPayment;
     }

     public Component[] getListCom() {
          return listCom;
     }

     public void setListCom(Component[] listCom) {
          this.listCom = listCom;
     }

    public ButtonCancel getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ButtonCancel btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getButtonHoldOrder() {
        return buttonHoldOrder;
    }

    public void setButtonHoldOrder(Button buttonHoldOrder) {
        this.buttonHoldOrder = buttonHoldOrder;
    }

    
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonPackage.ButtonSave buttonSave;
    private ButtonPackage.ButtonCancel cancel;
    private Components.ComboBox comboBoxReason;
    private javax.swing.JLabel jLabel1;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbReason;
    private javax.swing.JPanel panelCancel;
    // End of variables declaration//GEN-END:variables
}
