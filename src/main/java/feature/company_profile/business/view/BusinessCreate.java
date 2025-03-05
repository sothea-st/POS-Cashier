package feature.company_profile.business.view;

import Components.Color.WindowColor;
import Constant.JavaConstant;

import feature.company_profile.business.controller.BusinessCreateController;
import feature.company_profile.business.model.BusinessModel.BusinessModelDetail;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessCreate extends javax.swing.JDialog {

     private BusinessCreateController businessCreateController;
     private BusinessView businessView;
     private BusinessModelDetail detail;

     public BusinessCreate(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();

          businessCreateController = new BusinessCreateController(
               this,
               objProvince,
               objDistrict,
               objCommune,
               objVillage
          );
     }

     private void custom() {
          setBackground(WindowColor.slightGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          setTitle("Business Create");

          objPhoneNumber.setValidatePhoneNumber();
          objEmail.setValidateEmail();
     }
     
     public void update(BusinessModelDetail detail,BusinessView businessView){
          this.detail = detail;
          this.businessView = businessView;
          objCustomerName.setText(detail.getCustomerName());
          objCompany.setText(detail.getCompanyName());
          objPhoneNumber.setText(JavaConstant.formatPhoneNumber(detail.getPhoneNumber()));
          objEmail.setText(detail.getEmail());
          objVatNumber.setText(detail.getVatNumber());
          objHome.setText(detail.getHome());
          objStreet.setText(detail.getStreet());
          objProvince.setSelectedItem(detail.getProvince());
          objDistrict.setSelectedItem(detail.getDistrict());
          objCommune.setSelectedItem(detail.getCommune());
          objVillage.setSelectedItem(detail.getVillage());
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          objCustomerName = new FormComponent.JavaTextField();
          objCompany = new FormComponent.JavaTextField();
          objEmail = new FormComponent.JavaTextField();
          objPhoneNumber = new FormComponent.JavaTextField();
          objHome = new FormComponent.JavaTextField();
          objVatNumber = new FormComponent.JavaTextField();
          objStreet = new FormComponent.JavaTextField();
          objProvince = new FormComponent.combobox.JavaCombobox();
          objDistrict = new FormComponent.combobox.JavaCombobox();
          objCommune = new FormComponent.combobox.JavaCombobox();
          objVillage = new FormComponent.combobox.JavaCombobox();
          btnCancel1 = new Button.Button();
          buttonSave = new ButtonPackage.ButtonSave();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          objCustomerName.setLabelName("Customer Name *");
          objCustomerName.setPlaceHolder("Customer Name");

          objCompany.setLabelName("Company Name");
          objCompany.setPlaceHolder("Company Name");

          objEmail.setLabelName("Email *");
          objEmail.setPlaceHolder("Email");

          objPhoneNumber.setLabelName("Phone Number *");
          objPhoneNumber.setPlaceHolder("000 000 0000");

          objHome.setLabelName("Home");
          objHome.setPlaceHolder("Home");

          objVatNumber.setLabelName("VAT Number");
          objVatNumber.setPlaceHolder("VAT Number");

          objStreet.setLabelName("Street");
          objStreet.setPlaceHolder("Street");

          objProvince.setLabelName(" City / Province *");
          objProvince.setName(""); // NOI18N

          objDistrict.setLabelName(" District  *");
          objDistrict.setName(""); // NOI18N

          objCommune.setLabelName(" Commune *");
          objCommune.setName(""); // NOI18N

          objVillage.setLabelName(" Village *");
          objVillage.setName(""); // NOI18N

          btnCancel1.setButtonName("Close");
          btnCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancel1MouseClicked(evt);
               }
          });

          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(panelLayout.createSequentialGroup()
                                   .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(panelLayout.createSequentialGroup()
                                   .addComponent(objStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(panelLayout.createSequentialGroup()
                                   .addComponent(objVatNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(objHome, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(objCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objCompany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(objPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(objHome, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                         .addComponent(objVatNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(9, 9, 9)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MouseClicked
          dispose();
     }//GEN-LAST:event_btnCancel1MouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          businessCreateController.setBusinessView(businessView);
          businessCreateController.setDetail(detail);
          businessCreateController.create();
     }//GEN-LAST:event_buttonSaveMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    BusinessCreate dialog = new BusinessCreate(new javax.swing.JFrame(), true);
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
     private Button.Button btnCancel1;
     private ButtonPackage.ButtonSave buttonSave;
     private FormComponent.combobox.JavaCombobox objCommune;
     private FormComponent.JavaTextField objCompany;
     private FormComponent.JavaTextField objCustomerName;
     private FormComponent.combobox.JavaCombobox objDistrict;
     private FormComponent.JavaTextField objEmail;
     private FormComponent.JavaTextField objHome;
     private FormComponent.JavaTextField objPhoneNumber;
     private FormComponent.combobox.JavaCombobox objProvince;
     private FormComponent.JavaTextField objStreet;
     private FormComponent.JavaTextField objVatNumber;
     private FormComponent.combobox.JavaCombobox objVillage;
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables
}
