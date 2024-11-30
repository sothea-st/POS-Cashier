package Staff;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import static DatePicker.DatePicker.isValidDateOfBirth;
import Event.ButtonEvent;
import Model.Role.RoleModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
@Setter
public class InsertStaff extends javax.swing.JDialog {

     String path;
     private String genderId;
     private String roleId;
     private JPanel listGetStaff;
     private String pageNumber;
     private StaffInformation staffInformation;
     private Icon file;
     private Integer id;

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
          labelPopUpTitle1.setLabelTitle("Edit Staff");
     }

     public void setValueEdit(
          String nameEn,
          String dob,
          String joinDate,
          String addressValue,
          String genderIdValue,
          String roleIdValue,
          String contactValue,
          String urlImg
     ) throws IOException {
          staffName.setText(nameEn);
//          dobDate.setValueTextField(dob);
//          startDate.setValueTextField(joinDate);
          address.setText(addressValue);
          gender.setSelectedItem(genderIdValue);
          role.setSelectedItem(roleIdValue);
          phoneNumber.setValueTextField(contactValue);
          JavaConstant.coverImage(urlImg, lbFile, 150, 135);
     }

     public InsertStaff(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          staffName.requestFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

//          // action get select 
//          ButtonEvent eventtss = new ButtonEvent() {
//               @Override
//               public void onSelect(String key) {
//                    genderId = key;
//               }
//          };
//          gender.initEvent(eventtss);
//          addComboGender();
//
//          // action get select 
//          ButtonEvent event = new ButtonEvent() {
//               @Override
//               public void onSelect(String key) {
//                    roleId = key;
//               }
//          };
//          role.initEvent(event);
//          addComboRole();

//          phoneNumber.add3digitsToPhoneNumber();
//          dobDate.setValueTextField("");
//          dobDate.setLabelTextField("Select Date");
//          startDate.setValueTextField("");
//          startDate.setLabelTextField("Select Date");

          try {
               JavaConstant.coverImage(JavaBaseUrl.baseUrlDefaultImageStaff, lbFile, 150, 135);
          } catch (IOException ex) {
               Logger.getLogger(InsertStaff.class.getName()).log(Level.SEVERE, null, ex);
          }

          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
     }
     
     
//     //Set Combo box role
//     private void addComboRole() {
//          try {
//               HashMap<String, String> map = new HashMap<>();
//               ArrayList<RoleModel> roleModel = new ArrayList<>();
//               Response response = JavaConnection.get(JavaRoute.role);
//
//               if (response.isSuccessful()) {
//                    String responseData = response.body().string();
//                    JSONObject jsonObject = new JSONObject(responseData);
//                    JSONArray data = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < data.length(); i++) {
//                         JSONObject obj = data.getJSONObject(i);
//                         RoleModel role = new RoleModel(
//                              obj.getInt("id"),
//                              obj.getString("role_name")
//                         );
//
//                         roleModel.add(role);
//
//                         int idRole = roleModel.get(i).getRoleId();
//                         String roleName = roleModel.get(i).getRoleName();
//                         map.put(roleName, "" + idRole);
//                    }
//                    role.setMap(map);
//               } else {
//                    System.err.println("fail loading data");
//               }
//          } catch (Exception e) {
//               System.err.println("error = " + e);
//          }
//     }

     //Set Combo box Gender
//     private void addComboGender() {
//          try {
//               HashMap<String, String> map = new HashMap<>();
//               map.put("Male", "male");
//               map.put("Female", "female");
//               gender.setMap(map);
//          } catch (Exception e) {
//               System.err.println("error = " + e);
//          }
//     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAddStaff = new javax.swing.JPanel();
        labelPopUpTitle1 = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        label8 = new Components.Label();
        lbFile = new javax.swing.JLabel();
        browse = new javax.swing.JLabel();
        staffName = new FormComponent.JavaTextField();
        address = new FormComponent.JavaTextField();
        startDate = new FormComponent.datepicker.JavaDatePicker();
        gender = new FormComponent.combobox.JavaCombobox();
        role = new FormComponent.combobox.JavaCombobox();
        phoneNumber = new FormComponent.JavaTextField();
        dobDate = new FormComponent.datepicker.JavaDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle1.setLabelTitle("Add Staff");

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

        label8.setLabelName("Image");

        lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        staffName.setLabelName("Staff Name *");
        staffName.setPlaceHolder("Staff Name");
        staffName.setTypeTextField("");

        address.setLabelName("Address *");
        address.setPlaceHolder("Address");

        startDate.setLabelName("Start Date *");

        gender.setLabelName("Gender *");

        role.setLabelName("Role *");

        phoneNumber.setLabelName("Phone Number *");
        phoneNumber.setPlaceHolder("000 000 0000");

        dobDate.setLabelName("Date of Birth *");

        javax.swing.GroupLayout panelAddStaffLayout = new javax.swing.GroupLayout(panelAddStaff);
        panelAddStaff.setLayout(panelAddStaffLayout);
        panelAddStaffLayout.setHorizontalGroup(
            panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAddStaffLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddStaffLayout.createSequentialGroup()
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddStaffLayout.createSequentialGroup()
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(browse))))
                    .addGroup(panelAddStaffLayout.createSequentialGroup()
                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(staffName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(role, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(phoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panelAddStaffLayout.setVerticalGroup(
            panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddStaffLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         String dateOfBirth = dobDate.getSelectedDate();
         String staffNameEn = staffName.getValueTextField();
         String staffStartDate = startDate.getSelectedDate();
         String staffAddress = address.getValueTextField();

         if (staffNameEn == null || staffNameEn.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Staff Name is required!");
              return;
         }

         if (genderId == null) {
              JOptionPane.showMessageDialog(this, "Please select a gender!");
              return;
         }

         if (dateOfBirth == null || dateOfBirth.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Date of Birth is required!");
              return;
         }

         if (isValidDateOfBirth(dateOfBirth) == false) {
              JOptionPane.showMessageDialog(this, "Age must be at least 18 years old!");
              return;
         }

         if (roleId == null) {
              JOptionPane.showMessageDialog(this, "Please select a role!");
              return;
         }

         if (staffStartDate == null || staffStartDate.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Start Date is required!");
              return;
         }

         String staffContact = phoneNumber.getValueTextField();

         if (staffContact == null || staffContact.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Phone Number is required!");
              return;
         }

         String phone = staffContact.replace(" ", "");

         if (!phone.isEmpty() && phone.length() < 9 || phone.length() > 10) {
              JOptionPane.showMessageDialog(this, "Phone Number must be 9 or 10 charaters!");
              return;
         }

         if (staffAddress == null || staffAddress.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Address is required!");
              return;
         }

         String url = "";

         if (id == null) {
              url = new JavaBaseUrl().getBaseUrl() + JavaRoute.employee;
         } else {
              url = new JavaBaseUrl().getBaseUrl() + JavaRoute.employee + "/" + id;
         }

         OkHttpClient client = new OkHttpClient();
         // File to upload

         
         System.out.println("roleId : " + roleId);
         
         // Request body
         MultipartBody.Builder requestBody = new MultipartBody.Builder()
              .setType(MultipartBody.FORM)
              .addFormDataPart("nameEn", staffNameEn)
              .addFormDataPart("gender", genderId)
              .addFormDataPart("dob", dateOfBirth)
              .addFormDataPart("startDate", staffStartDate)
              .addFormDataPart("address", staffAddress)
              .addFormDataPart("roleId", roleId)
              .addFormDataPart("createBy", JavaConstant.cashierId + "")
              .addFormDataPart("contact", phone);

         if (path != null) {
              File fileToUpload = new File(path);
              requestBody.addFormDataPart("image", fileToUpload.getName(),
                   RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));
         }

         // Request
         Request request = new Request.Builder()
              .url(url)
              .post(requestBody.build())
              .header("Authorization", "Bearer " + JavaConstant.token)
              .build();

         try {
              if (id == null) {
                   Response response = client.newCall(request).execute();

                   if (response.code() == 500) {
                        JOptionPane.showMessageDialog(this, "The Phone Number is already existed!");
                        return;
                   }

                   if (response.isSuccessful()) {
                        System.out.println("success insert staff");
//                        StaffInformation list = new StaffInformation(new JFrame(), true);
                        listGetStaff.removeAll();
                        listGetStaff.revalidate();
                        listGetStaff.repaint();
                        staffInformation.getStaff(listGetStaff, true, pageNumber);
                        dispose();
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
                   }
              } else {
                   Response response = client.newCall(request).execute();

                   if (response.code() == 500) {
                        JOptionPane.showMessageDialog(this, "The Phone Number is already existed!");
                        return;
                   }
                   if (response.isSuccessful()) {
                        StaffInformation obj = new StaffInformation(new JFrame(), true);
                        listGetStaff.removeAll();
                        listGetStaff.revalidate();
                        listGetStaff.repaint();
                        obj.getStaff(listGetStaff, true, pageNumber);
                        dispose();
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
                   }

              }

              // Do something with the response.
         } catch (IOException e) {
              System.out.println("err = " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
         try {
              path = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(path, lbFile, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(InsertStaff.class.getName()).log(Level.SEVERE, null, ex);
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
               java.util.logging.Logger.getLogger(InsertStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(InsertStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(InsertStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(InsertStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    InsertStaff dialog = new InsertStaff(new javax.swing.JFrame(), true);
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

     public JPanel getListGetStaff() {
          return listGetStaff;
     }

     public void setListGetStaff(JPanel listGetStaff) {
          this.listGetStaff = listGetStaff;
     }

     public String getPageNumber() {
          return pageNumber;
     }

     public void setPageNumber(String pageNumber) {
          this.pageNumber = pageNumber;
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FormComponent.JavaTextField address;
    private javax.swing.JLabel browse;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private FormComponent.datepicker.JavaDatePicker dobDate;
    private FormComponent.combobox.JavaCombobox gender;
    private Components.Label label8;
    private Components.LabelPopUpTitle labelPopUpTitle1;
    private javax.swing.JLabel lbFile;
    private javax.swing.JPanel panelAddStaff;
    private FormComponent.JavaTextField phoneNumber;
    private FormComponent.combobox.JavaCombobox role;
    private FormComponent.JavaTextField staffName;
    private FormComponent.datepicker.JavaDatePicker startDate;
    // End of variables declaration//GEN-END:variables
}
