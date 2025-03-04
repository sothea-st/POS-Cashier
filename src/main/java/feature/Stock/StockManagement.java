package feature.Stock;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Components.CustomeUI.CustomScrollBarUI;
import feature.LoginAndLogoutForm.LoginFormJdailog;
import feature.LoginAndLogoutForm.model.RoleHasPermissionModel;
import feature.Stock.Products.ListProduct;
import feature.Stock.PurchaseOrderView.PurchaseOrderView;
import feature.Stock.PurchaseReceive.ListPurchaseReceive;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.adjustment.AdjustmentForm;
import feature.company_profile.CompanyProfileView;
import feature.invoice_listing.InvoiceListingView;
import feature.promotion.view.PromotionView;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;

public class StockManagement extends javax.swing.JDialog {

     private JPanel panelProduct;
     private JPanel panelCategory;
     private LoginFormJdailog jdLogin;
     //private RoleHasPermissionModel.RoleHasPermissionDetail roleHasPermissionDetail;

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

          custom();

          // check permission
          checkPermission();
     }

     private void custom() {
          jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     }

     private void checkPermission() {

          // note: parentId = 5 from table pos_permission  
          Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "?roleId=" + JavaConstant.roleId + "&parentId=5");

          try {
               // convert response to string 
               String responseData = response.body().string();

               // create object mapper
               ObjectMapper object = new ObjectMapper();

               // convert responseData to objectMapper
               RoleHasPermissionModel model = object.readValue(responseData, RoleHasPermissionModel.class);

               boolean isProduct = false;
               boolean isPurchaseOrder = false;
               boolean isPurchaseReceive = false;

               for (RoleHasPermissionModel.RoleHasPermissionDetail data : model.getData()) {

                    if (data.getPermissionName().equals("Product")) {
                         isProduct = data.getIsVisible();
                         JavaConstant.permissionDetail = data;
                    }

                    if (data.getPermissionName().equals("Purchase Order")) {
                         isPurchaseOrder = data.getIsVisible();
                         JavaConstant.permissionDetail = data;
                    }

                    if (data.getPermissionName().equals("Purchase Receive")) {
                         isPurchaseReceive = data.getIsVisible();
                         JavaConstant.permissionDetail = data;
                    }

               }

               product.setVisible(isProduct);
               purchaseOrder.setVisible(isPurchaseOrder);
               purchaseOrderReceive.setVisible(isPurchaseReceive);

          } catch (Exception e) {
               System.err.println("error :" + e);
          }

     }

     private void getImageAndTitle() {

          product.setTitle("Product");
          purchaseOrder.setTitle("Purchase Order");
          purchaseOrderReceive.setTitle("Purchase Receive");

     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          jPanel3 = new javax.swing.JPanel();
          product = new Components.SettingBox();
          purchaseOrder = new Components.SettingBox();
          purchaseOrderReceive = new Components.SettingBox();
          category = new Components.SettingBox();
          objPromotion = new Components.SettingBox();
          objPromotion1 = new Components.SettingBox();
          objCompanyProfile = new Components.SettingBox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          jScrollPane1.setBorder(null);

          product.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/item.png"))); // NOI18N
          product.setTitle("Product");
          product.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    productMouseClicked(evt);
               }
          });

          purchaseOrder.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/PurchaseOrder.png"))); // NOI18N
          purchaseOrder.setTitle("Purchase Order");
          purchaseOrder.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    purchaseOrderMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    purchaseOrderMouseEntered(evt);
               }
          });

          purchaseOrderReceive.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseReceive.png"))); // NOI18N
          purchaseOrderReceive.setTitle("Purchase Receive");
          purchaseOrderReceive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    purchaseOrderReceiveMouseClicked(evt);
               }
          });

          category.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/adjustment.png"))); // NOI18N
          category.setTitle("Adjustment");
          category.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    categoryMouseClicked(evt);
               }
          });

          objPromotion.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/promotion.png"))); // NOI18N
          objPromotion.setTitle("Promotion");
          objPromotion.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objPromotionMouseClicked(evt);
               }
          });

          objPromotion1.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/invoice_listing.png"))); // NOI18N
          objPromotion1.setTitle("Invoice Listing");
          objPromotion1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objPromotion1MouseClicked(evt);
               }
          });

          objCompanyProfile.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/company_profile.png"))); // NOI18N
          objCompanyProfile.setTitle("Company Profile");
          objCompanyProfile.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objCompanyProfileMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
          jPanel3.setLayout(jPanel3Layout);
          jPanel3Layout.setHorizontalGroup(
               jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel3Layout.createSequentialGroup()
                              .addComponent(objPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objPromotion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objCompanyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel3Layout.createSequentialGroup()
                              .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(purchaseOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(purchaseOrderReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(18, Short.MAX_VALUE))
          );
          jPanel3Layout.setVerticalGroup(
               jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(purchaseOrderReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(purchaseOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objPromotion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCompanyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(707, Short.MAX_VALUE))
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

         dispose();
         ListProduct list = new ListProduct(new JFrame(), true);
         //list.setRoleHasPermissionDetail(roleHasPermissionDetail); // assing roleHasPermission and get Data
         list.setPanelProduct(panelProduct);
         list.setJdLogin(jdLogin);
         list.setPanelCategory(panelCategory);
         list.setVisible(true);
    }//GEN-LAST:event_productMouseClicked

    private void purchaseOrderReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderReceiveMouseClicked
         dispose();
         ListPurchaseReceive list = new ListPurchaseReceive(new JFrame(), true);
         list.setVisible(true);
    }//GEN-LAST:event_purchaseOrderReceiveMouseClicked

    private void purchaseOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderMouseClicked
         dispose();
         PurchaseOrderView purchase = new PurchaseOrderView(new JFrame(), true);
         purchase.setVisible(true);
    }//GEN-LAST:event_purchaseOrderMouseClicked

     private void purchaseOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseOrderMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_purchaseOrderMouseEntered

     private void categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryMouseClicked
          AdjustmentForm adjustmentForm = new AdjustmentForm(new JFrame(), true);
          adjustmentForm.setVisible(true);
     }//GEN-LAST:event_categoryMouseClicked

     private void objPromotionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objPromotionMouseClicked
          PromotionView promotionView = new PromotionView(new JFrame(), true);
          promotionView.setVisible(true);
     }//GEN-LAST:event_objPromotionMouseClicked

     private void objPromotion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objPromotion1MouseClicked
          InvoiceListingView invoiceListingView = new InvoiceListingView(new JFrame(), true);
          invoiceListingView.setVisible(true);
     }//GEN-LAST:event_objPromotion1MouseClicked

     private void objCompanyProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objCompanyProfileMouseClicked

          CompanyProfileView companyProfileView = new CompanyProfileView(new JFrame(), true);
          companyProfileView.setVisible(true);
     }//GEN-LAST:event_objCompanyProfileMouseClicked

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
     private Components.SettingBox category;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.SettingBox objCompanyProfile;
     private Components.SettingBox objPromotion;
     private Components.SettingBox objPromotion1;
     private Components.SettingBox product;
     private Components.SettingBox purchaseOrder;
     private Components.SettingBox purchaseOrderReceive;
     // End of variables declaration//GEN-END:variables
}
