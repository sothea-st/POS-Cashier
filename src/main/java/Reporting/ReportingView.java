package Reporting;

import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import Controller.ActionProduct.ActionProduct;
import CustomeUI.CustomScrollBarUI;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    }

    private void getImageAndTitle() {
        reportImport.setTitle("Reporting Import");
        reportSale.setTitle("Reporting Sale");
        reportPurhaseRequest.setTitle("<html>" + "Reporting Purchase Request" + "</html>");
        reportPurhaseCheck.setTitle("<html>" + "Reporting Purchase Check" + "</html>");
        reportPurhaseApproval.setTitle("<html>" + "Reporting Purchase Approval" + "</html>");
        reportPurhaseReceive.setTitle("<html>" + "Reporting Purchase Receive" + "</html>");
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    // Task to be executed
                    
                    reportSale.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "sale.png");
                    reportImport.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "import.png");
                    reportPurhaseRequest.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "2964eeeb-ee20-4b17-80f9-a6bcfe11a277");
                    reportPurhaseCheck.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "c4b2a597-abc9-4c8a-ba89-c03c6cf1ab5f");
                    reportPurhaseApproval.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "34f2863b-ff40-4323-b997-e31a810f9679");
                    reportPurhaseReceive.setIconImage(new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + "63157d93-b4c9-4c60-b9f3-8eb7e789c039");

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
        jPanel1 = new javax.swing.JPanel();
        reportImport = new Components.SettingBox();
        reportSale = new Components.SettingBox();
        reportPurhaseRequest = new Components.SettingBox();
        reportPurhaseCheck = new Components.SettingBox();
        reportPurhaseApproval = new Components.SettingBox();
        reportPurhaseReceive = new Components.SettingBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        reportImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportImportMouseClicked(evt);
            }
        });

        reportSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportSaleMouseClicked(evt);
            }
        });

        reportPurhaseRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPurhaseRequestMouseClicked(evt);
            }
        });

        reportPurhaseCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPurhaseCheckMouseClicked(evt);
            }
        });

        reportPurhaseApproval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPurhaseApprovalMouseClicked(evt);
            }
        });

        reportPurhaseReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPurhaseReceiveMouseClicked(evt);
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
                        .addComponent(reportPurhaseReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reportSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportPurhaseRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportPurhaseCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportPurhaseApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reportPurhaseCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportPurhaseRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportPurhaseApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportPurhaseReceive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

     private void reportImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportImportMouseClicked
         ReportingImportDetail reportingImportDetail = new ReportingImportDetail(new JFrame(), true);
         reportingImportDetail.setVisible(true);
     }//GEN-LAST:event_reportImportMouseClicked

     private void reportSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportSaleMouseClicked
         ReportingSaled reportingSaled = new ReportingSaled(new JFrame(), true);
         reportingSaled.setVisible(true);
     }//GEN-LAST:event_reportSaleMouseClicked

    private void reportPurhaseRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseRequestMouseClicked
//         ReportingPurchaseOrder purchaseOrder = new ReportingPurchaseOrder(new JFrame(), true);
//         purchaseOrder.setVisible(true);
        ReportingPurchaseRequest request = new ReportingPurchaseRequest(new JFrame(), true);
        request.setTitle("Reporting Purchase Request");
        request.setVisible(true);

    }//GEN-LAST:event_reportPurhaseRequestMouseClicked

    private void reportPurhaseCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseCheckMouseClicked
        ReportingPurchaseRequest check = new ReportingPurchaseRequest(new JFrame(), true);
        check.setTitle("Reporting Purchase Check");
        check.setVisible(true);
    }//GEN-LAST:event_reportPurhaseCheckMouseClicked

    private void reportPurhaseApprovalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseApprovalMouseClicked
       ReportingPurchaseRequest approve = new ReportingPurchaseRequest(new JFrame(), true);
       approve.setTitle("Reporting Purchase Approval");
       approve.setVisible(true);
        
    }//GEN-LAST:event_reportPurhaseApprovalMouseClicked

    private void reportPurhaseReceiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPurhaseReceiveMouseClicked
        ReportingPurchaseReceive receive = new ReportingPurchaseReceive(new JFrame(), true);
        receive.setVisible(true);
    }//GEN-LAST:event_reportPurhaseReceiveMouseClicked

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
               java.util.logging.Logger.getLogger(ReportingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(ReportingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(ReportingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(ReportingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
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
    private Components.SettingBox reportPurhaseApproval;
    private Components.SettingBox reportPurhaseCheck;
    private Components.SettingBox reportPurhaseReceive;
    private Components.SettingBox reportPurhaseRequest;
    private Components.SettingBox reportSale;
    // End of variables declaration//GEN-END:variables
}
