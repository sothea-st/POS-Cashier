package feature.company_profile.individual;

import Components.Event.ButtonEvent;
import Constant.JavaRoute;
import feature.company_profile.individual.component.JavaComboBoxSelectionV1;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class IndividualCreate extends javax.swing.JDialog {

     public IndividualCreate(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          custom();
          cmdProvince();
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          objGender = new FormComponent.combobox.JavaCombobox();
          objFirstName = new FormComponent.JavaTextField();
          objDob = new FormComponent.datepicker.JavaDatePicker();
          objLastName = new FormComponent.JavaTextField();
          objPhoneNumber = new FormComponent.JavaTextField();
          objEmail = new FormComponent.JavaTextField();
          objHome = new FormComponent.JavaTextField();
          objLat = new FormComponent.JavaTextField();
          objLng = new FormComponent.JavaTextField();
          objStreet = new FormComponent.JavaTextField();
          objDistrict = new FormComponent.combobox.JavaCombobox();
          objProvince = new FormComponent.combobox.JavaCombobox();
          objCommune = new FormComponent.combobox.JavaCombobox();
          objVillage = new FormComponent.combobox.JavaCombobox();
          btnCancel = new Button.Button();
          btnCancel1 = new Button.Button();
          buttonSave = new ButtonPackage.ButtonSave();
          objNationality = new FormComponent.combobox.JavaCombobox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          objGender.setLabelName(" Gender *");
          objGender.setName(""); // NOI18N

          objFirstName.setLabelName("First Name *");
          objFirstName.setPlaceHolder("First Name");

          objDob.setLabelName("Date of Birth *");

          objLastName.setLabelName("Last Name *");
          objLastName.setPlaceHolder("Last Name");

          objPhoneNumber.setLabelName("Phone Number *");
          objPhoneNumber.setPlaceHolder("Phone Number");

          objEmail.setLabelName("Email *");
          objEmail.setPlaceHolder("Email");

          objHome.setLabelName("Home");
          objHome.setPlaceHolder("Home");

          objLat.setLabelName("Latitude");
          objLat.setPlaceHolder("Latitude");

          objLng.setLabelName("Longitude");
          objLng.setPlaceHolder("Longitude");

          objStreet.setLabelName("Street");
          objStreet.setPlaceHolder("Street");

          objDistrict.setLabelName(" District  *");
          objDistrict.setName(""); // NOI18N

          objProvince.setLabelName(" City / Province *");
          objProvince.setName(""); // NOI18N

          objCommune.setLabelName(" Commune *");
          objCommune.setName(""); // NOI18N

          objVillage.setLabelName(" Village *");
          objVillage.setName(""); // NOI18N

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          btnCancel1.setButtonName("Close");
          btnCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancel1MouseClicked(evt);
               }
          });

          objNationality.setLabelName("Nationality");
          objNationality.setName(""); // NOI18N

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(objFirstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(objGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(objLat, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objLng, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(objPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(objDob, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(objStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(layout.createSequentialGroup()
                              .addGap(406, 406, 406)
                              .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(20, Short.MAX_VALUE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addGap(579, 579, 579)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addContainerGap(593, Short.MAX_VALUE)))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(objPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                         .addComponent(objEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objDob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objHome, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objLng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(23, Short.MAX_VALUE))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addGap(333, 333, 333)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addContainerGap(223, Short.MAX_VALUE)))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void btnCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MouseClicked
          // TODO add your handling code here:
     }//GEN-LAST:event_btnCancel1MouseClicked

     private void cmdProvince() {
          // name is field from response 
          JavaComboBoxSelectionV1.addComboBox(
               objProvince,
               JavaRoute.province,
               "nameKh",
               JavaComboBoxSelectionV1.DESC);

          // event select company
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objProvince.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objDistrict,
                              JavaRoute.district + "/" + objProvince.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);

                         cmdCommune();
                    } else {
                         objDistrict.setToFirstItem();
                    }
               }
          };
          objProvince.initEvent(event);
     }

     private void cmdCommune() {

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objDistrict.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objCommune,
                              JavaRoute.commune + "/" + objDistrict.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);

                         cmdVillage();
                    } else {
                         objVillage.setToFirstItem();
                    }
               }
          };
          objDistrict.initEvent(event);

     }

     private void cmdVillage() {

          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelected(String id) {
                    if (!objCommune.getSelectedItem().equals("0")) {
                         // name is field from response 
                         JavaComboBoxSelectionV1.addComboBox(
                              objVillage,
                              JavaRoute.village + "/" + objCommune.getSelectedItem(),
                              "nameKh",
                              JavaComboBoxSelectionV1.DESC);
                    } else {
                         objVillage.setToFirstItem();
                    }
               }
          };
          objCommune.initEvent(event);

     }
     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    IndividualCreate dialog = new IndividualCreate(new javax.swing.JFrame(), true);
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
     private Button.Button btnCancel;
     private Button.Button btnCancel1;
     private ButtonPackage.ButtonSave buttonSave;
     private ButtonPackage.ButtonSave buttonSave1;
     private ButtonPackage.ButtonSave buttonSave2;
     private FormComponent.combobox.JavaCombobox objCommune;
     private FormComponent.combobox.JavaCombobox objDistrict;
     private FormComponent.datepicker.JavaDatePicker objDob;
     private FormComponent.JavaTextField objEmail;
     private FormComponent.JavaTextField objFirstName;
     private FormComponent.combobox.JavaCombobox objGender;
     private FormComponent.JavaTextField objHome;
     private FormComponent.JavaTextField objLastName;
     private FormComponent.JavaTextField objLat;
     private FormComponent.JavaTextField objLng;
     private FormComponent.combobox.JavaCombobox objNationality;
     private FormComponent.JavaTextField objPhoneNumber;
     private FormComponent.combobox.JavaCombobox objProvince;
     private FormComponent.combobox.JavaCombobox objReason1;
     private FormComponent.combobox.JavaCombobox objReason2;
     private FormComponent.JavaTextField objStreet;
     private FormComponent.combobox.JavaCombobox objVillage;
     // End of variables declaration//GEN-END:variables
}
