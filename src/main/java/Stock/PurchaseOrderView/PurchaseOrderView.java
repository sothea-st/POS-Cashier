package Stock.PurchaseOrderView;


import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import LoginAndLogoutForm.model.RoleHasPermissionModel;
import Stock.PurchaseOrderRequest.PurchaseOrder;
import Stock.PurchaseOrderCheck.ListPurchaseOrderCheck;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;

public class PurchaseOrderView extends javax.swing.JDialog {

     public PurchaseOrderView(java.awt.Frame parent, boolean modal) {
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

          JavaConstant.addTitleAndLogo(this, "Purchase Order");
          getImageAndTitle();
          
          // checkPermission
          checkPermission();
     }
     
     
     
     private void checkPermission() {

          // note: parentId = 11 from table pos_permission  
          Response response = JavaConnection.get(JavaRoute.roleHasPermissions + "?roleId=" + JavaConstant.roleId + "&parentId=11");

          try {
               // convert response to string 
               String responseData = response.body().string();

               // create object mapper
               ObjectMapper object = new ObjectMapper();

               // convert responseData to objectMapper
               RoleHasPermissionModel model = object.readValue(responseData, RoleHasPermissionModel.class);

                

               boolean isRequest = false;
               boolean isPOCheck = false;
               boolean isPOApproval = false;

               for (RoleHasPermissionModel.RoleHasPermissionDetail data : model.getData()) {

                    if (data.getPermissionName().equals("Purchase Request")) {
                         isRequest = data.getIsVisible();
                         //JavaConstant.permissionDetail = data;
                    }

                    if (data.getPermissionName().equals("Purchase Check")) {
                         isPOCheck = data.getIsVisible();
                         //JavaConstant.permissionDetail = data;
                    }

                    if (data.getPermissionName().equals("Purchase Approval")) {
                         isPOApproval = data.getIsVisible();
                         //JavaConstant.permissionDetail = data;
                    }

               }

               request.setVisible(isRequest);
               check.setVisible(isPOCheck);
               approve.setVisible(isPOApproval);

          } catch (Exception e) {
               System.err.println("error :" + e);
          }

     }

     private void getImageAndTitle() {

          request.setTitle("Purchase Request");
          check.setTitle("Purchase Check");
          approve.setTitle("Purchase Approval");


     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        request = new Components.SettingBox();
        check = new Components.SettingBox();
        approve = new Components.SettingBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(null);

        request.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseRequest.png"))); // NOI18N
        request.setTitle("Purchase Request");
        request.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestMouseClicked(evt);
            }
        });

        check.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseCheck.png"))); // NOI18N
        check.setTitle("Purchase Check");
        check.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkMouseClicked(evt);
            }
        });

        approve.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/purchaseApprove.png"))); // NOI18N
        approve.setTitle("Purchase Approval");
        approve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                approveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(request, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(approve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(request, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(388, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void requestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestMouseClicked
         dispose();
         PurchaseOrder purchase = new PurchaseOrder(new JFrame(), true);
         purchase.setVisible(true);
    }//GEN-LAST:event_requestMouseClicked

    private void checkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkMouseClicked
         dispose();
         ListPurchaseOrderCheck listCheck = new ListPurchaseOrderCheck(new JFrame(), true);
         listCheck.setTypeForm("checked");
         listCheck.setTitle("Purchase Check");
         listCheck.setVisible(true);
    }//GEN-LAST:event_checkMouseClicked

    private void approveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveMouseClicked
         dispose();
         ListPurchaseOrderCheck listCheck = new ListPurchaseOrderCheck(new JFrame(), true);
         listCheck.setTypeForm("approved");
         listCheck.setTitle("Purchase Approval");
         listCheck.setVisible(true);
    }//GEN-LAST:event_approveMouseClicked

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
               java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(PurchaseOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PurchaseOrderView dialog = new PurchaseOrderView(new javax.swing.JFrame(), true);
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
    private Components.SettingBox approve;
    private Components.SettingBox check;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.SettingBox request;
    // End of variables declaration//GEN-END:variables
}
