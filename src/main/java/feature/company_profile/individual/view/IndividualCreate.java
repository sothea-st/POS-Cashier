package feature.company_profile.individual.view;

import feature.company_profile.individual.view.IndividualView;
import Components.Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.company_profile.individual.controller.IndividualCreateController;
import feature.company_profile.individual.model.IndividualResponseModel;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lombok.Getter;
import lombok.Setter;
import main.mainJNAFileChooser.JavaChooseFile;
import main.mainJNAFileChooser.model.FileUploadResponse;
import okhttp3.Response;

@Setter
@Getter
public class IndividualCreate extends javax.swing.JDialog {

     private IndividualCreateController individualCreateController;
     private IndividualView individualView;
     private String pathImg;
     private IndividualResponseModel.IndividualResponseDetail detail;

     public IndividualCreate(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();

          individualCreateController = new IndividualCreateController(
               this,
               objProvince,
               objDistrict,
               objCommune,
               objVillage
          );
          individualCreateController.init(); // initialize 
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          // cusome textField
          objPhoneNumber.setValidatePhoneNumber();
          objEmail.setValidateEmail();

          setTitle("Individual Create");
     }

     public void update(IndividualResponseModel.IndividualResponseDetail detail, IndividualView individualView) {
          this.detail = detail;
          this.individualView = individualView;

          objFirstName.setText(detail.getFirstName());
          objLastName.setText(detail.getLastName());
          objGender.setSelectedItem(detail.getGender());
          objNationality.setSelectedItem(detail.getNationality());

          objDate.setSelectedDate(JavaConstant.formateDateDDMMYYYY(detail.getDob()));
          objEmail.setText(detail.getEmail());
          objPhoneNumber.setText(JavaConstant.formatPhoneNumber(detail.getPhoneNumber()));
          objHome.setText(detail.getHome());
          objLat.setText(detail.getLat());
          objLng.setText(detail.getLng());
          objStreet.setText(detail.getStreet());
          objProvince.setSelectedItem(detail.getProvince());
          objDistrict.setSelectedItem(detail.getDistrict());
          objCommune.setSelectedItem(detail.getCommune());
          objVillage.setSelectedItem(detail.getVillage());

          if (detail.getProfileImage() != null && !detail.getProfileImage().isEmpty()) {
               JavaConstant.coverImageUrl(detail.getProfileImage(), lbFile, 124, 235);
          }
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          objFirstName = new FormComponent.JavaTextField();
          objLastName = new FormComponent.JavaTextField();
          objGender = new FormComponent.combobox.JavaCombobox();
          objNationality = new FormComponent.combobox.JavaCombobox();
          objDate = new FormComponent.datepicker.JavaDatePicker();
          objEmail = new FormComponent.JavaTextField();
          objPhoneNumber = new FormComponent.JavaTextField();
          objHome = new FormComponent.JavaTextField();
          objLat = new FormComponent.JavaTextField();
          objLng = new FormComponent.JavaTextField();
          objStreet = new FormComponent.JavaTextField();
          objProvince = new FormComponent.combobox.JavaCombobox();
          objDistrict = new FormComponent.combobox.JavaCombobox();
          objCommune = new FormComponent.combobox.JavaCombobox();
          objVillage = new FormComponent.combobox.JavaCombobox();
          btnCancel1 = new Button.Button();
          buttonSave = new ButtonPackage.ButtonSave();
          browse = new javax.swing.JLabel();
          lbFile = new javax.swing.JLabel();
          label8 = new Components.Label();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          objFirstName.setLabelName("First Name *");
          objFirstName.setPlaceHolder("First Name");

          objLastName.setLabelName("Last Name *");
          objLastName.setPlaceHolder("Last Name");

          objGender.setLabelName(" Gender *");
          objGender.setName(""); // NOI18N

          objNationality.setLabelName("Nationality *");
          objNationality.setName(""); // NOI18N

          objDate.setLabelName("Date of Birth");

          objEmail.setLabelName("Email *");
          objEmail.setPlaceHolder("Email");

          objPhoneNumber.setLabelName("Phone Number *");
          objPhoneNumber.setPlaceHolder("Phone Number");

          objHome.setLabelName("Home");
          objHome.setPlaceHolder("Home");

          objLat.setLabelName("Latitude");
          objLat.setPlaceHolder("Latitude");

          objLng.setLabelName("Longitude");
          objLng.setPlaceHolder("Longitude");

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

          browse.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          browse.setForeground(new java.awt.Color(0, 51, 102));
          browse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          browse.setText("Browse here to Upload");
          browse.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    browseMouseClicked(evt);
               }
               public void mouseEntered(java.awt.event.MouseEvent evt) {
                    browseMouseEntered(evt);
               }
               public void mouseExited(java.awt.event.MouseEvent evt) {
                    browseMouseExited(evt);
               }
          });

          lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_default.jpg"))); // NOI18N
          lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          label8.setLabelName("Image");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(objFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(objGender, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(panelLayout.createSequentialGroup()
                                                  .addComponent(objDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                             .addGroup(panelLayout.createSequentialGroup()
                                                  .addComponent(objPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(6, 6, 6)))
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                             .addComponent(objHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(objEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(13, 13, 13))
                                   .addComponent(browse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(111, 111, 111))
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(objStreet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addGap(406, 406, 406)
                                        .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(objLat, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(objLng, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(20, 20, 20))))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addGroup(panelLayout.createSequentialGroup()
                              .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objHome, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objLng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCommune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objVillage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(75, Short.MAX_VALUE))
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
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancel1MouseClicked
          dispose();
     }//GEN-LAST:event_btnCancel1MouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
          individualCreateController.setIndividualView(individualView);
          individualCreateController.setDetail(detail);
          individualCreateController.create();
     }//GEN-LAST:event_buttonSaveMouseClicked

     private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
          try {
               File file = JavaChooseFile.funChooseFile();

               if (file != null) {

                    Response response = JavaConnection.uploadFile(file);

                    try {

                         String respnseData = response.body().string();

                         ObjectMapper objMapper = new ObjectMapper();

                         FileUploadResponse fileUpload = objMapper.readValue(respnseData, FileUploadResponse.class);

                         pathImg = fileUpload.getFileName();

                         JavaConstant.coverImagePath(file.getAbsolutePath(), lbFile, 124, 235); // display image to label

                    } catch (Exception e) {
                         System.err.print("Erro : " + e);
                    }
               } else {
                    pathImg = null;
               }

          } catch (IOException ex) {
               System.err.println("error : " + ex);
          }
     }//GEN-LAST:event_browseMouseClicked

     private void browseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseEntered
          browse.setForeground(WindowColor.light_Blue);
          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.light_Blue));
     }//GEN-LAST:event_browseMouseEntered

     private void browseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseExited
          browse.setForeground(WindowColor.darkBlue);
          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
     }//GEN-LAST:event_browseMouseExited

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
     private javax.swing.JLabel browse;
     private Button.Button btnCancel1;
     private ButtonPackage.ButtonSave buttonSave;
     private Components.Label label8;
     private javax.swing.JLabel lbFile;
     private FormComponent.combobox.JavaCombobox objCommune;
     private FormComponent.datepicker.JavaDatePicker objDate;
     private FormComponent.combobox.JavaCombobox objDistrict;
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
     private FormComponent.JavaTextField objStreet;
     private FormComponent.combobox.JavaCombobox objVillage;
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables
}
