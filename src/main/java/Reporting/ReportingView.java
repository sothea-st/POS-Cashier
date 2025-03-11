package Reporting;

import Constant.JavaConstant;

import Components.CustomeUI.CustomScrollBarUI;
import feature.LoginAndLogoutForm.model.RoleHasPermissionModel;
import Reporting.ReportInventory.ReportInventoryForm;
import feature.report.report_sale_return.ReportSaleReturnForm;
import feature.report.report_stock.view.ReportStockAvailableView;
import feature.report.report_vendor.view.ReportVendorView;
import feature.user_permission.JavaPermission;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ReportingView extends javax.swing.JDialog {

     public ReportingView(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          setResizable(false);
          JavaConstant.addTitleAndLogo(this, "Reporting");
          getImageAndTitle();

//          reportImport.setVisible(false);
//          reportPurhaseCheck.setVisible(false);
//          reportPurhaseApproval.setVisible(false);
          // checkPermission()
          checkPermission();
     }

     private void checkPermission() {

          boolean isReportSale = false;
          boolean isReportPurhaseRequest = false;
          boolean isReportPurhaseReceive = false;
          boolean isReportInventory = false;
          boolean isReportSaleReturn = false;

          // parentId : 7 is primary key id from the table pos_permission
          for (RoleHasPermissionModel.RoleHasPermissionDetail data : JavaPermission.getPermissions(7)) {

               // Check each permission and set the corresponding flag
               String permissionName = data.getPermissionName().toLowerCase();

               switch (permissionName) {
                    case "reporting sale":
                         isReportSale = data.getIsVisible();
                         break;

                    case "reporting purchase order":
                         isReportPurhaseRequest = data.getIsVisible();
                         break;

                    case "reporting purchase receive":
                         isReportPurhaseReceive = data.getIsVisible();
                         break;

                    case "reporting inventory":
                         isReportInventory = data.getIsVisible();
                         break;

                    case "reporting sale return":
                         isReportSaleReturn = data.getIsVisible();
                         break;

                    default:
                         System.err.println("Unknown permission: " + data.getPermissionName());
                         break;
               }

          }

          // Set visibility for UI components
          reportSale.setVisible(isReportSale);
          reportPurhaseRequest.setVisible(isReportPurhaseRequest);
          reportPurhaseReceive.setVisible(isReportPurhaseReceive);
          reportInventory.setVisible(isReportInventory);
          objReportReturn.setVisible(isReportSaleReturn);
     }

     private void getImageAndTitle() {
          //reportImport.setTitle("Reporting Import");
          reportSale.setTitle("Reporting Sale");
          reportPurhaseRequest.setTitle("<html>" + "Reporting Purchase Order" + "</html>");
          objReportReturn.setTitle("<html>" + "Reporting Return" + "</html>");
          //reportPurhaseApproval.setTitle("<html>" + "Reporting Purchase Approval" + "</html>");
          reportPurhaseReceive.setTitle("<html>" + "Reporting Purchase Receive" + "</html>");
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          jPanel1 = new javax.swing.JPanel();
          reportSale = new Components.SettingBox();
          reportPurhaseRequest = new Components.SettingBox();
          reportPurhaseReceive = new Components.SettingBox();
          reportInventory = new Components.SettingBox();
          objReportReturn = new Components.SettingBox();
          objStockAvailable = new Components.SettingBox();
          objStockUnavailable = new Components.SettingBox();
          objProductStockIn = new Components.SettingBox();
          objSupplier = new Components.SettingBox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          jScrollPane1.setBorder(null);

          jPanel1.setPreferredSize(new java.awt.Dimension(620, 580));

          reportSale.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/report image/sale.png"))); // NOI18N
          reportSale.setTitle("Reporting Sales");
          reportSale.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportSaleMouseClicked(evt);
               }
          });

          reportPurhaseRequest.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/report image/PurchaseOrder.png"))); // NOI18N
          reportPurhaseRequest.setTitle("Reporting Purchase Request");
          reportPurhaseRequest.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportPurhaseRequestMouseClicked(evt);
               }
          });

          reportPurhaseReceive.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseReceive.png"))); // NOI18N
          reportPurhaseReceive.setTitle("Reporting Purchase Receive");
          reportPurhaseReceive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportPurhaseReceiveMouseClicked(evt);
               }
          });

          reportInventory.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/balance.png"))); // NOI18N
          reportInventory.setTitle("Stock Balance Report");
          reportInventory.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportInventoryMouseClicked(evt);
               }
          });

          objReportReturn.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/return.jpg"))); // NOI18N
          objReportReturn.setTitle("Reporting Sale Return");
          objReportReturn.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objReportReturnMouseClicked(evt);
               }
          });

          objStockAvailable.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stock/stockAvailable.png"))); // NOI18N
          objStockAvailable.setTitle("Stock Available");
          objStockAvailable.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objStockAvailableMouseClicked(evt);
               }
          });

          objStockUnavailable.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stock/stockUnavailable.png"))); // NOI18N
          objStockUnavailable.setTitle(" Stock Unavailable");
          objStockUnavailable.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objStockUnavailableMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    objStockUnavailableMouseEntered(evt);
               }
          });

          objProductStockIn.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stock/productStockIn.png"))); // NOI18N
          objProductStockIn.setTitle("Product Stock In");
          objProductStockIn.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objProductStockInMouseClicked(evt);
               }
          });

          objSupplier.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stock/supplier.png"))); // NOI18N
          objSupplier.setTitle("Supplier");
          objSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objSupplierMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objReportReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(reportPurhaseRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportPurhaseReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(objStockAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objStockUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objProductStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(24, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(reportPurhaseRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportPurhaseReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objReportReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objProductStockIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objStockUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objStockAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(objSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(62, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(jPanel1);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void reportSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportSaleMouseClicked
          dispose();
          ReportingSaled reportingSaled = new ReportingSaled(new JFrame(), true);
          reportingSaled.setVisible(true);
     }//GEN-LAST:event_reportSaleMouseClicked

    private void reportPurhaseRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseRequestMouseClicked
         dispose();
         ReportingPurchaseOrderV2 request = new ReportingPurchaseOrderV2(new JFrame(), true);
         request.setTitle("Reporting Purchase Order");
         request.setVisible(true);
    }//GEN-LAST:event_reportPurhaseRequestMouseClicked

    private void reportPurhaseReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseReceiveMouseClicked
         dispose();
         ReportingPurchaseReceive receive = new ReportingPurchaseReceive(new JFrame(), true);
         receive.setVisible(true);
    }//GEN-LAST:event_reportPurhaseReceiveMouseClicked

     private void reportInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportInventoryMouseClicked
          dispose();
          ReportInventoryForm reportInventoryForm = new ReportInventoryForm(new JFrame(), true);
          reportInventoryForm.setVisible(true);
     }//GEN-LAST:event_reportInventoryMouseClicked

    private void objReportReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objReportReturnMouseClicked
         dispose();
         ReportSaleReturnForm reportSaleReturnForm = new ReportSaleReturnForm(new JFrame(), true);
         reportSaleReturnForm.setVisible(true);
    }//GEN-LAST:event_objReportReturnMouseClicked

     private void objStockAvailableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objStockAvailableMouseClicked
          ReportStockAvailableView reportStockAvailable = new ReportStockAvailableView(new JFrame(), true,2);
          //reportStockAvailable.setStatusId(2); // 2 = Available
          reportStockAvailable.setVisible(true);
     }//GEN-LAST:event_objStockAvailableMouseClicked

     private void objStockUnavailableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objStockUnavailableMouseClicked
          ReportStockAvailableView reportStockAvailable = new ReportStockAvailableView(new JFrame(), true,1);
          reportStockAvailable.setVisible(true);
     }//GEN-LAST:event_objStockUnavailableMouseClicked

     private void objStockUnavailableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objStockUnavailableMouseEntered
          // TODO add your handling code here:
     }//GEN-LAST:event_objStockUnavailableMouseEntered

     private void objProductStockInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objProductStockInMouseClicked

     }//GEN-LAST:event_objProductStockInMouseClicked

     private void objSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objSupplierMouseClicked
        ReportVendorView reportVendorView = new ReportVendorView(new JFrame(), true);
        reportVendorView.setVisible(true);
     }//GEN-LAST:event_objSupplierMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    ReportingView dialog = new ReportingView(new javax.swing.JFrame(), true);
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
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.SettingBox objProductStockIn;
     private Components.SettingBox objReportReturn;
     private Components.SettingBox objStockAvailable;
     private Components.SettingBox objStockUnavailable;
     private Components.SettingBox objSupplier;
     private Components.SettingBox reportInventory;
     private Components.SettingBox reportPurhaseReceive;
     private Components.SettingBox reportPurhaseRequest;
     private Components.SettingBox reportSale;
     // End of variables declaration//GEN-END:variables
}
