package StockManagement;

import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import Controller.ActionProduct.ActionProduct;
import CustomeUI.CustomScrollBarUI;
import LoginAndLogoutForm.LoginFormJdailog;
import Products.ListProduct;
import Stock.PurchaseOrder.PurchaseOrder;
import Stock.PurchaseOrderView.PurchaseOrderView;
import Stock.PurchaseReceive.ListPurchaseReceive;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class StockManagement extends javax.swing.JDialog {

     private JPanel panelProduct;
     private JPanel panelCategory;

     private LoginFormJdailog jdLogin;

     public StockManagement(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);

          JavaConstant.addTitleAndLogo(this, "Stock");
          getImageAndTitle();
     }

     private void getImageAndTitle() {

          product.setTitle("Product");
          purchaseOrder.setTitle("Purchase Order");
          purchaseOrderReceive.setTitle("Purchase Receive");

          TimerTask task = new TimerTask() {
               @Override
               public void run() {
                    try {
                         // Task to be executed
                         product.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "item.png");
                         purchaseOrder.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "PurchaseOrder.png");
                         purchaseOrderReceive.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "63157d93-b4c9-4c60-b9f3-8eb7e789c039");
                    } catch (IOException ex) {
                         Logger.getLogger(ActionProduct.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
          };

          Timer timer = new Timer();
          timer.schedule(task, 500); // Delays task execution by 1 second
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        product = new Components.SettingBox();
        purchaseOrder = new Components.SettingBox();
        purchaseOrderReceive = new Components.SettingBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        product.setTitle("Product");
        product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productMouseClicked(evt);
            }
        });

        purchaseOrder.setTitle("Purchase Order");
        purchaseOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseOrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                purchaseOrderMouseEntered(evt);
            }
        });

        purchaseOrderReceive.setTitle("Purchase Receive");
        purchaseOrderReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseOrderReceiveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseOrderReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(purchaseOrderReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchaseOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productMouseClicked
         ListProduct list = new ListProduct(new JFrame(), true);
         list.setPanelProduct(panelProduct);
         list.setJdLogin(jdLogin);
         list.setPanelCategory(panelCategory);
         list.setVisible(true);
    }//GEN-LAST:event_productMouseClicked

    private void purchaseOrderReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderReceiveMouseClicked
        ListPurchaseReceive list = new ListPurchaseReceive(new JFrame(), true);
        list.setVisible(true);
    }//GEN-LAST:event_purchaseOrderReceiveMouseClicked

    private void purchaseOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderMouseClicked
        PurchaseOrderView purchase = new PurchaseOrderView(new JFrame(), true);
        purchase.setVisible(true);
    }//GEN-LAST:event_purchaseOrderMouseClicked

     private void purchaseOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_purchaseOrderMouseEntered

     public JPanel getPanelProduct() {
          return panelProduct;
     }

     public void setPanelProduct(JPanel panelProduct) {
          this.panelProduct = panelProduct;
     }

     public JPanel getPanelCategory() {
          return panelCategory;
     }

     public void setPanelCategory(JPanel panelCategory) {
          this.panelCategory = panelCategory;
     }

     public LoginFormJdailog getJdLogin() {
          return jdLogin;
     }

     public void setJdLogin(LoginFormJdailog jdLogin) {
          this.jdLogin = jdLogin;
     }

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
               java.util.logging.Logger.getLogger(StockManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(StockManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(StockManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(StockManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    StockManagement dialog = new StockManagement(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.SettingBox product;
    private Components.SettingBox purchaseOrder;
    private Components.SettingBox purchaseOrderReceive;
    // End of variables declaration//GEN-END:variables
}
