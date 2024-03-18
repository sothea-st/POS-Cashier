/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Customer;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Event.ButtonEvent;
import PointCustomer.CustomerPointModel;
import PointCustomer.PointCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;

/**
 *
 * @author MOBILE-APP.02
 */
public class JdailogCustomer extends javax.swing.JDialog {

     /**
      * Creates new form JdailogCustomer
      */
     DecimalFormat dm = new DecimalFormat("$ #,##0.00");

     public JdailogCustomer(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          buttonSave.setTitleButton("+ Create New Customer");
          buttonSave.setBgColor(WindowColor.primary);
          pCustomer.setBackground(WindowColor.mediumGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          event();
          getPointCustomer();
          txtCustomerName.requestFocusInWindow();
//          txtPoint.disabledTextField(false);
//          txtAmount.disabledTextField(false);
     }

     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtCustomerName.initEvent(btnevent);
          txtPhone.initEvent(btnevent);
          txtPoint.initEvent(btnevent);
          txtAmount.initEvent(btnevent);
     }

     void getPointCustomer() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    String phone = txtPhone.getValueTextField();
                    if( phone == null ) phone = txtCustomerName.getValueTextField();
                  
                    Response response = JavaConnection.get(JavaRoute.getPoint + phone);
                    System.err.println("resonpse er = " + response);
            
                    try {
                         if (response.isSuccessful()) {
                              String data = response.body().string();
                            
                              ObjectMapper objMap = new ObjectMapper();
                              PointCustomer obj = objMap.readValue(data, PointCustomer.class);
                              CustomerPointModel cusData = obj.getData();
                              txtPoint.setValueTextField("" + cusData.getPointEarned());
                              if (cusData.getTotalAmountEarned() == null) {
                                   txtAmount.setValueTextField(dm.format(0));
                              } else {
                                   txtAmount.setValueTextField(dm.format(cusData.getTotalAmountEarned()));
                              }
                              txtCustomerName.setValueTextField(cusData.getCustomerID());
                              txtPhone.setValueTextField(cusData.getContact());

                         }
                    } catch (Exception e) {
                    }
               }
          };

          txtPhone.initEvent(event);
          txtCustomerName.initEvent(event);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          labelPopUpTitle1 = new Components.LabelPopUpTitle();
          pCustomer = new javax.swing.JPanel();
          buttonSave = new ButtonPackage.ButtonSave();
          txtCustomerName = new Components.TextField();
          txtPhone = new Components.TextField();
          txtPoint = new Components.TextField();
          txtAmount = new Components.TextField();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle1.setLabelTitle("Loyal Customer");

          buttonSave.setBackground(new java.awt.Color(0, 153, 255));
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          txtCustomerName.setLabelTextField("Customer Code");

          txtPhone.setLabelTextField("Phone Number");

          txtPoint.setEnabled(false);
          txtPoint.setLabelTextField("Total Point earned");

          txtAmount.setLabelTextField("Total Amount earned");

          javax.swing.GroupLayout pCustomerLayout = new javax.swing.GroupLayout(pCustomer);
          pCustomer.setLayout(pCustomerLayout);
          pCustomerLayout.setHorizontalGroup(
               pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(pCustomerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(pCustomerLayout.createSequentialGroup()
                              .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                              .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(152, 152, 152))
          );
          pCustomerLayout.setVerticalGroup(
               pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
               .addComponent(pCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          dispose();
          Customer c = new Customer(new JFrame(), true);
          c.setVisible(true);

     }//GEN-LAST:event_buttonSaveMouseClicked

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
               java.util.logging.Logger.getLogger(JdailogCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(JdailogCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(JdailogCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(JdailogCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    JdailogCustomer dialog = new JdailogCustomer(new javax.swing.JFrame(), true);
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
     private ButtonPackage.ButtonSave buttonSave;
     private Components.LabelPopUpTitle labelPopUpTitle1;
     private javax.swing.JPanel pCustomer;
     private Components.TextField txtAmount;
     private Components.TextField txtCustomerName;
     private Components.TextField txtPhone;
     private Components.TextField txtPoint;
     // End of variables declaration//GEN-END:variables
}
