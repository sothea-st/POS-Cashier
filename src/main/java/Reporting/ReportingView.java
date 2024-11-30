package Reporting;

 
import Constant.JavaConstant;
 
import CustomeUI.CustomScrollBarUI;
import Reporting.ReportInventory.ReportInventoryForm;
 
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
          reportImport.setVisible(false);
          reportPurhaseCheck.setVisible(false);
          reportPurhaseApproval.setVisible(false);
     }

     private void getImageAndTitle() {
          reportImport.setTitle("Reporting Import");
          reportSale.setTitle("Reporting Sale");
          reportPurhaseRequest.setTitle("<html>" + "Reporting Purchase Order" + "</html>");
          reportPurhaseCheck.setTitle("<html>" + "Reporting Purchase Check" + "</html>");
          reportPurhaseApproval.setTitle("<html>" + "Reporting Purchase Approval" + "</html>");
          reportPurhaseReceive.setTitle("<html>" + "Reporting Purchase Receive" + "</html>");

//          TimerTask task = new TimerTask() {
//               @Override
//               public void run() {
//                    try {
//                         // Task to be executed
//
//                         reportSale.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "sale.png");
//                         reportImport.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "import.png");
////                    reportPurhaseRequest.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "2964eeeb-ee20-4b17-80f9-a6bcfe11a277");
//                         reportPurhaseRequest.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "PurchaseOrder.png");
//                         reportPurhaseCheck.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "c4b2a597-abc9-4c8a-ba89-c03c6cf1ab5f");
//                         reportPurhaseApproval.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "34f2863b-ff40-4323-b997-e31a810f9679");
//                       
//                         reportPurhaseReceive.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "63157d93-b4c9-4c60-b9f3-8eb7e789c039");
//                    } catch (IOException ex) {
//                         Logger.getLogger(ActionProduct.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//               }
//          };
//
//          Timer timer = new Timer();
//          timer.schedule(task, 500); // Delays task execution by 1 second
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jScrollPane1 = new javax.swing.JScrollPane();
          jPanel1 = new javax.swing.JPanel();
          reportImport = new Components.SettingBox();
          reportSale = new Components.SettingBox();
          reportPurhaseRequest = new Components.SettingBox();
          reportPurhaseCheck = new Components.SettingBox();
          reportPurhaseApproval = new Components.SettingBox();
          reportPurhaseReceive = new Components.SettingBox();
          reportInventory = new Components.SettingBox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          jScrollPane1.setBorder(null);

          reportImport.setTitle("Reporting Import");
          reportImport.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportImportMouseClicked(evt);
               }
          });

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

          reportPurhaseCheck.setTitle("Reporting Purchase Check");
          reportPurhaseCheck.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportPurhaseCheckMouseClicked(evt);
               }
          });

          reportPurhaseApproval.setTitle("Reporting Purchase Aapproval");
          reportPurhaseApproval.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportPurhaseApprovalMouseClicked(evt);
               }
          });

          reportPurhaseReceive.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseReceive.png"))); // NOI18N
          reportPurhaseReceive.setTitle("Reporting Purchase Receive");
          reportPurhaseReceive.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportPurhaseReceiveMouseClicked(evt);
               }
          });

          reportInventory.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseReceive.png"))); // NOI18N
          reportInventory.setTitle("Reporting Inventory");
          reportInventory.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reportInventoryMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(reportPurhaseCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportPurhaseApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(reportImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(jPanel1Layout.createSequentialGroup()
                              .addComponent(reportSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportPurhaseRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportPurhaseReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(reportInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(18, Short.MAX_VALUE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(reportPurhaseCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportPurhaseApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(reportImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(215, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(jPanel1);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane1)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGap(15, 15, 15))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void reportSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportSaleMouseClicked
          ReportingSaled reportingSaled = new ReportingSaled(new JFrame(), true);
          reportingSaled.setVisible(true);
     }//GEN-LAST:event_reportSaleMouseClicked

    private void reportPurhaseRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseRequestMouseClicked
//         ReportingPurchaseOrder purchaseOrder = new ReportingPurchaseOrder(new JFrame(), true);
//         purchaseOrder.setVisible(true);
         ReportingPurchaseOrderV2 request = new ReportingPurchaseOrderV2(new JFrame(), true);
         request.setTitle("Reporting Purchase Order");
         request.setVisible(true);

    }//GEN-LAST:event_reportPurhaseRequestMouseClicked

    private void reportPurhaseReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseReceiveMouseClicked
         ReportingPurchaseReceive receive = new ReportingPurchaseReceive(new JFrame(), true);
         receive.setVisible(true);
    }//GEN-LAST:event_reportPurhaseReceiveMouseClicked

     private void reportPurhaseApprovalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseApprovalMouseClicked
          ReportingPurchaseOrderV2 approve = new ReportingPurchaseOrderV2(new JFrame(), true);
          approve.setTitle("Reporting Purchase Approval");
          approve.setVisible(true);
     }//GEN-LAST:event_reportPurhaseApprovalMouseClicked

     private void reportPurhaseCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseCheckMouseClicked
          ReportingPurchaseOrderV2 check = new ReportingPurchaseOrderV2(new JFrame(), true);
          check.setTitle("Reporting Purchase Check");
          check.setVisible(true);
     }//GEN-LAST:event_reportPurhaseCheckMouseClicked

     private void reportImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportImportMouseClicked
          ReportingImportDetail reportingImportDetail = new ReportingImportDetail(new JFrame(), true);
          reportingImportDetail.setVisible(true);
     }//GEN-LAST:event_reportImportMouseClicked

     private void reportInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportInventoryMouseClicked
          ReportInventoryForm reportInventoryForm = new ReportInventoryForm(new JFrame(), true);
          reportInventoryForm.setVisible(true);
     }//GEN-LAST:event_reportInventoryMouseClicked

   
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
     private Components.SettingBox reportImport;
     private Components.SettingBox reportInventory;
     private Components.SettingBox reportPurhaseApproval;
     private Components.SettingBox reportPurhaseCheck;
     private Components.SettingBox reportPurhaseReceive;
     private Components.SettingBox reportPurhaseRequest;
     private Components.SettingBox reportSale;
     // End of variables declaration//GEN-END:variables
}
