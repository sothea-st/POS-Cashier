package feature.Customer;

import Components.Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import Components.Event.ButtonEvent;
import Model.PointCustomer.CustomerPointModel;
import Model.PointCustomer.PointCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
 

 
public class JdailogCustomer extends javax.swing.JDialog {

 
    DecimalFormat dm = new DecimalFormat("$ #,##0.00");

    public JdailogCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buttonSave.setTitleButton("+ Create New Customer");
        buttonSave.setBgColor(WindowColor.primary);
        pCustomer.setBackground(WindowColor.mediumGreen);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        getPointCustomer();
        txtCustomerName.requestFocusInWindow();
        txtPhone.setValidatePhoneNumber();
    }


    void getPointCustomer() {
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onKeyRelease() {
                String phone = txtPhone.getValueTextField();
                
                if (phone == null) {
                    phone = txtCustomerName.getValueTextField();
                }else{
                    phone = phone.replace(" ", "");
                }
                
                Response response = JavaConnection.get(JavaRoute.getPoint + phone);
                
                System.err.println("resonpse er = " + response);

                try {
                    if (response.isSuccessful()) {
                        String data = response.body().string();

                        ObjectMapper objMap = new ObjectMapper();
                        PointCustomer obj = objMap.readValue(data, PointCustomer.class);
                        CustomerPointModel cusData = obj.getData();

                        txtPoint.setText("" + cusData.getPointEarned());
                        if (cusData.getTotalAmountEarned() == null) {
                            txtAmount.setText(dm.format(0));
                        } else {
                            txtAmount.setText(dm.format(cusData.getTotalAmountEarned()));
                        }
                        txtCustomerName.setText(cusData.getCustomerID());
                        txtPhone.setText(cusData.getContact());

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
        txtCustomerName = new FormComponent.JavaTextField();
        txtPoint = new FormComponent.JavaTextField();
        txtPhone = new FormComponent.JavaTextField();
        txtAmount = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Loyal Customer");

        buttonSave.setBackground(new java.awt.Color(0, 153, 255));
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        txtCustomerName.setLabelName(" ");
        txtCustomerName.setPlaceHolder("Customer Code");

        txtPoint.setLabelName(" ");
        txtPoint.setPlaceHolder("Total Point Earned");

        txtPhone.setLabelName(" ");
        txtPhone.setPlaceHolder("000 000 0000");

        txtAmount.setLabelName("");
        txtAmount.setPlaceHolder("Total Amount Earned");

        javax.swing.GroupLayout pCustomerLayout = new javax.swing.GroupLayout(pCustomer);
        pCustomer.setLayout(pCustomerLayout);
        pCustomerLayout.setHorizontalGroup(
            pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCustomerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCustomerLayout.createSequentialGroup()
                        .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pCustomerLayout.createSequentialGroup()
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
            .addGroup(pCustomerLayout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pCustomerLayout.setVerticalGroup(
            pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private FormComponent.JavaTextField txtAmount;
    private FormComponent.JavaTextField txtCustomerName;
    private FormComponent.JavaTextField txtPhone;
    private FormComponent.JavaTextField txtPoint;
    // End of variables declaration//GEN-END:variables
}
