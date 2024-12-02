package Setting.Vendor;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaConflicValidation;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class AddVendor extends javax.swing.JDialog {

     private Integer id;
     private JPanel listGetVendor;
     private String pageNumber;
     private ListVendor obj;

     public AddVendor(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          txtPhoneNumber.setValidatePhoneNumber();
          txtEmail.setValidateEmail();
          txtVendorName.requestFocus();
     }

     //Value Edit
     public void setValueEdit(
          String vendorName,
          String phone,
          String email,
          String Web,
          String address
     ) throws IOException {

          if (vendorName != null && vendorName != "") {
               txtVendorName.setText(vendorName);
          }

          if (phone != null && phone != "") {
               txtPhoneNumber.setText(JavaConstant.formatPhoneNumber(phone));
          }

          if (email != null && email != "") {
               txtEmail.setText(email);
          }

          if (Web != null && Web != "") {
               txtWebsite.setText(Web);
          }

          if (address != null && address != "") {
               txtAddress.setText(address);
          }

     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        txtVendorName = new FormComponent.JavaTextField();
        txtPhoneNumber = new FormComponent.JavaTextField();
        txtEmail = new FormComponent.JavaTextField();
        txtAddress = new FormComponent.JavaTextField();
        txtWebsite = new FormComponent.JavaTextField();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        titlePopUp = new Components.LabelPopUpTitle();

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtVendorName.setLabelName("Vendor Name *");
        txtVendorName.setPlaceHolder("Vendor Name");

        txtPhoneNumber.setLabelName("Phone Number *");
        txtPhoneNumber.setPlaceHolder("000 000 0000");

        txtEmail.setLabelName("Email *");
        txtEmail.setPlaceHolder("Email");

        txtAddress.setLabelName("Address *");
        txtAddress.setPlaceHolder("Address");

        txtWebsite.setLabelName("Website");
        txtWebsite.setPlaceHolder("Website");

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

        titlePopUp.setLabelTitle("Add Vendor");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(txtVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String vendorName = txtVendorName.getValueTextField();
         String email = txtEmail.getValueTextField();
         String website = txtWebsite.getValueTextField();
         String address = txtAddress.getValueTextField();
         String phoneNumber = txtPhoneNumber.getValueTextField();
         
         if(phoneNumber!= null){
             phoneNumber = phoneNumber.replace(" ", "");
         }

         try {
             
            boolean isCheck = JavaValidation.checkValidation(panel);
            
            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("vendorName", vendorName);
                json.put("contact", phoneNumber);
                json.put("email", email);
                json.put("website", website);
                json.put("address", address);

                // create response 
                Response response = null;
                if (id != null) { // add new
                    response = JavaConnection.put(JavaRoute.vendor + '/' + id, json);
                } else { // update 
                    json.put("createBy", JavaConstant.cashierId);
                    response = JavaConnection.post(JavaRoute.vendor, json);
                }
                
                // check if name already exist
                List<JavaConflicValidation> fields = new ArrayList<>();

                fields.add(JavaConflicValidation.builder()
                        .key("contact") // specific word that exist in key "reason"
                        .msg("This phone number is already existed!") // message to show 
                        .field(txtPhoneNumber) // obj of JavaTextField
                        .build());
                
                fields.add(JavaConflicValidation.builder()
                        .key("email") // specific word that exist in key "reason"
                        .msg("This email is already existed!") // message to show 
                        .field(txtEmail) // obj of JavaTextField
                        .build());

                boolean isExist = JavaValidation.checkNameExist(response, fields);                
                System.out.println("json : " + json);
                System.out.println("response : " + response);
                
                try {
                    if (response.isSuccessful() && isExist) {

                        ListVendor list = new ListVendor(new JFrame(), true);
                        listGetVendor.removeAll();
                        listGetVendor.revalidate();
                        listGetVendor.repaint();
                        list.getVendor(listGetVendor, true,pageNumber);
                        dispose();
                    }

                } catch (Exception e) {
                    System.err.println("error post vendor : " + e);
                }
            }

         } catch (Exception e) {
              System.err.println("errr -- " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
          titlePopUp.setLabelTitle("Edit Vendor");
     }

     public JPanel getListGetVendor() {
          return listGetVendor;
     }

     public void setListGetVendor(JPanel listGetVendor) {
          this.listGetVendor = listGetVendor;
     }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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
               java.util.logging.Logger.getLogger(AddVendor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(AddVendor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(AddVendor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(AddVendor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AddVendor dialog = new AddVendor(new javax.swing.JFrame(), true);
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
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel panel;
    private Components.LabelPopUpTitle titlePopUp;
    private FormComponent.JavaTextField txtAddress;
    private FormComponent.JavaTextField txtEmail;
    private FormComponent.JavaTextField txtPhoneNumber;
    private FormComponent.JavaTextField txtVendorName;
    private FormComponent.JavaTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
