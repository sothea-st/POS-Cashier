package OpenAndCloseShift;

import Button.Button;
import Color.WindowColor;
import Components.BackgroundImage;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

public class CloseShift extends javax.swing.JDialog {

     private Button btnOpenShift;
     private JPanel panelProduct;
     private JPanel panelPagination;
     private JPanel category;

     public CloseShift(java.awt.Frame parent, boolean modal, Button btnOpenShift) {
          super(parent, modal);
          initComponents();
          panelCloseShift.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          this.btnOpenShift = btnOpenShift;
          redexpress.requestFocus();
     }

     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }

          };
          redexpress.initEvent(btnevent);
          qrMnk.initEvent(btnevent);
          qrAba.initEvent(btnevent);
          abaCreditCard.initEvent(btnevent);
          cashUs.initEvent(btnevent);
          cashKh.initEvent(btnevent);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCloseShift = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        lbPosId = new Components.Label();
        IbUserId = new Components.Label();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        label1 = new Components.Label();
        label2 = new Components.Label();
        label3 = new Components.Label();
        label4 = new Components.Label();
        label5 = new Components.Label();
        label6 = new Components.Label();
        redexpress = new Components.TextField();
        qrMnk = new Components.TextField();
        qrAba = new Components.TextField();
        abaCreditCard = new Components.TextField();
        cashUs = new Components.TextField();
        cashKh = new Components.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelCloseShift.setForeground(new java.awt.Color(0, 0, 0));

        labelPopUpTitle1.setLabelTitle("Close Shift");

        lbPosId.setLabelName("Payment Type");

        IbUserId.setLabelName("Cash Amount");

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

        label1.setLabelName("RED ANT EXPRESS");

        label2.setLabelName("KHQR-MNK");

        label3.setLabelName("KHQR-ABA");

        label4.setLabelName("ABA-CREDIT CARD");

        label5.setLabelName("CASH (USD)");

        label6.setLabelName("CASH (KHR)");

        redexpress.setLabelTextField("$ 0.00");

        qrMnk.setLabelTextField("$ 0.00");

        qrAba.setLabelTextField("$ 0.00");

        abaCreditCard.setLabelTextField("$ 0.00");

        cashUs.setLabelTextField("$ 0.00");

        cashKh.setLabelTextField("0.00");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");

        javax.swing.GroupLayout panelCloseShiftLayout = new javax.swing.GroupLayout(panelCloseShift);
        panelCloseShift.setLayout(panelCloseShiftLayout);
        panelCloseShiftLayout.setHorizontalGroup(
            panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPosId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCloseShiftLayout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(abaCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qrAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cashUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cashKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qrMnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(redexpress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCloseShiftLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(IbUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCloseShiftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        panelCloseShiftLayout.setVerticalGroup(
            panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCloseShiftLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IbUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lbPosId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(redexpress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(qrMnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(qrAba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCloseShiftLayout.createSequentialGroup()
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(abaCreditCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cashUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cashKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCloseShiftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCloseShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCloseShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);

         if (!JavaConstant.listHoldData.isEmpty()) {
              j.setMessage("There are any trancsactions not complete yet in Hold function!");
              j.setVisible(true);
              return;
         }

         String express = redexpress.getValueTextField();
         String khqrMnk = qrMnk.getValueTextField();
         String khqrAba = qrAba.getValueTextField();
         String creditCard = abaCreditCard.getValueTextField();
         String cashKhr = cashKh.getValueTextField();
         String cashUsd = cashUs.getValueTextField();

         JSONObject json = new JSONObject();
         json.put("express", express);
         json.put("khqrMnk", khqrMnk);
         json.put("khqrAba", khqrAba);
         json.put("creditCard", creditCard);
         json.put("cashKhr", cashKhr);
         json.put("cashUsd", cashUsd);
         json.put("userCode", JavaConstant.userCode);
         json.put("userId", JavaConstant.cashierId);
         json.put("posId", JavaConstant.posId);

         try {

              if (express == null || express.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "RED ANT EXPRESS can not be empty!");
                   return;
              }
              if (khqrMnk == null || khqrMnk.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "KHQR-NMK can not be empty!");
                   return;
              }
              if (khqrAba == null || khqrAba.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "KHQR-ABA can not be empty!");
                   return;
              }
              if (creditCard == null || creditCard.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "ABA-CREDIT CARD can not be empty!");
                   return;
              }
              if (cashUsd == null || cashUsd.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "CASH (USD) can not be empty!");
                   return;
              }
              if (cashKhr == null || cashKhr.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "CASH (KHR) can not be empty!");
                   return;
              }

              Response response = JavaConnection.post(JavaRoute.closeShift, json);
              System.err.println("response f = " + response);
              if (response.isSuccessful()) {
                   panelProduct.removeAll();
                   panelProduct.revalidate();
                   panelProduct.repaint();
                   getPanelPagination().setVisible(false);
                   category.getComponents()[0].setBackground(WindowColor.darkGreen);
                
                   dispose();

                   System.err.println("dddddddddddddddddddd");
                   btnOpenShift.setButtonName("Open Shift");
                   JavaConstant.checkCloseShift = 0l;
                   JavaConstant.checkOpenShift = false;

              } else {
                   JOptionPane.showMessageDialog(this, "Save Failed!");

              }

         } catch (Exception e) {
              System.err.println("errr -- " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     public JPanel getCategory() {
          return category;
     }

     public void setCategory(JPanel category) {
          this.category = category;
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
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(CloseShift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    CloseShift dialog = new CloseShift(new javax.swing.JFrame(), true, null);
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
    private Components.Label IbUserId;
    private Components.TextField abaCreditCard;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private Components.TextField cashKh;
    private Components.TextField cashUs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private Components.Label label1;
    private Components.Label label2;
    private Components.Label label3;
    private Components.Label label4;
    private Components.Label label5;
    private Components.Label label6;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Components.Label lbPosId;
    private javax.swing.JPanel panelCloseShift;
    private Components.TextField qrAba;
    private Components.TextField qrMnk;
    private Components.TextField redexpress;
    // End of variables declaration//GEN-END:variables
}
