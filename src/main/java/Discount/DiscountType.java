package Discount;

import Color.WindowColor;
import Components.BoxItem;
import Components.JavaAlertMessage;
import Components.SubtotalPanel;
import Constant.JavaConstant;
import LoginAndLogoutForm.LoginFormJdailog;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DiscountType extends javax.swing.JDialog {

     private SubtotalPanel subtotalPanel;
     private JPanel detailItem;
     private JPanel panelProduct;
     private JPanel category;
     private JPanel panelPagination;

     private LoginFormJdailog jdFormLogin;

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public DiscountType(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          panelDiscountType.setBackground(WindowColor.mediumGreen);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDiscountType = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        disByItem = new Button.Button();
        overallDis = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Discount");

        disByItem.setButtonName("Discount By Item");
        disByItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disByItemMouseClicked(evt);
            }
        });

        overallDis.setBackground(new java.awt.Color(47, 152, 70));
        overallDis.setButtonName("Overall Discount");
        overallDis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                overallDisMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDiscountTypeLayout = new javax.swing.GroupLayout(panelDiscountType);
        panelDiscountType.setLayout(panelDiscountTypeLayout);
        panelDiscountTypeLayout.setHorizontalGroup(
            panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiscountTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(disByItem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(overallDis, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDiscountTypeLayout.setVerticalGroup(
            panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiscountTypeLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDiscountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(disByItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(overallDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDiscountType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDiscountType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void disByItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disByItemMouseClicked
         this.dispose();
         ListProduct list = new ListProduct(new JFrame(), true);
         list.setPanelProduct(panelProduct);
         list.setJdFormLogin(jdFormLogin);
         list.setCategory(category);
         list.setPanelPagination(panelPagination);
         list.setVisible(true);
    }//GEN-LAST:event_disByItemMouseClicked

    private void overallDisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overallDisMouseClicked

         Component[] listCom1 = detailItem.getComponents();
         double sumDiscount = 0;
         if (listCom1.length > 0) {
              this.dispose();
              for (int i = 0; i < listCom1.length; i++) {
                   var obj = ((BoxItem) listCom1[i]);
                   sumDiscount += JavaConstant.getReplace(obj.getDiscountAmount());
              }

              if (sumDiscount <= 0) {
                   OverallDiscount overall = new OverallDiscount(new JFrame(), true);
                   overall.setTotalPanel(subtotalPanel);
                   overall.setVisible(true);
              } else {
                   JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                   j.setMessage("Cannot process this function!");
                   j.setVisible(true);
                   return;
              }
         } else {
              JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
              j.setMessage("Cannot process this function!");
              j.setVisible(true);
              return;
         }
    }//GEN-LAST:event_overallDisMouseClicked

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
               java.util.logging.Logger.getLogger(DiscountType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(DiscountType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(DiscountType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(DiscountType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    DiscountType dialog = new DiscountType(new javax.swing.JFrame(), true);
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

     public JPanel getCategory() {
          return category;
     }

     public void setCategory(JPanel category) {
          this.category = category;
     }

     public JPanel getPanelPagination() {
          return panelPagination;
     }

     public void setPanelPagination(JPanel panelPagination) {
          this.panelPagination = panelPagination;
     }

     
     
     
     public LoginFormJdailog getJdFormLogin() {
          return jdFormLogin;
     }

     public void setJdFormLogin(LoginFormJdailog jdFormLogin) {
          this.jdFormLogin = jdFormLogin;
     }

     public SubtotalPanel getSubtotalPanel() {
          return subtotalPanel;
     }

     public void setSubtotalPanel(SubtotalPanel subtotalPanel) {
          this.subtotalPanel = subtotalPanel;
     }

     public JPanel getDetailItem() {
          return detailItem;
     }

     public void setDetailItem(JPanel detailItem) {
          this.detailItem = detailItem;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.Button disByItem;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private Button.Button overallDis;
    private javax.swing.JPanel panelDiscountType;
    // End of variables declaration//GEN-END:variables
}
