package Staff;

import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.Role.RoleModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
 
public class InsertStaff extends javax.swing.JDialog {

     String path;
     private String genderId;
     private String roleId;
     private JPanel listGetStaff;

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
          staffName.setValueTextField(nameEn);
          dobDate.setValueTextField(dob);
          startDate.setValueTextField(joinDate);
          address.setValueTextField(addressValue);
          gender.setToLastItem(genderIdValue);
          role.setToLastItem(roleIdValue);
          contact.setValueTextField(contactValue);
          JavaConstant.coverImage(urlImg, lbFile, 150, 135);
     }

     public InsertStaff(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          event();
          staffName.requestFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          labelPopUpTitle1.setLabelTitle("Add Staff");

          // action get select 
          ButtonEvent eventtss = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    genderId = key;
               }
          };
          gender.initEvent(eventtss);
          addComboGender();

          // action get select 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    roleId = key;
               }
          };
          role.initEvent(event);
          addComboRole();
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          staffName.initEvent(btnevent);
          dobDate.initEvent(btnevent);
          startDate.initEvent(btnevent);
          address.initEvent(btnevent);
          contact.initEvent(btnevent);
     }

     //Set Combo box role
     private void addComboRole() {
          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<RoleModel> roleModel = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.role);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         RoleModel role = new RoleModel(
                              obj.getInt("id"),
                              obj.getString("role_name")
                         );

                         roleModel.add(role);

                         int idRole = roleModel.get(i).getRoleId();
                         String roleName = roleModel.get(i).getRoleName();
                         map.put(roleName, "" + idRole);
                    }
                    role.setMap(map);
               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     //Set Combo box Gender
     private void addComboGender() {
          try {
               HashMap<String, String> map = new HashMap<>();
               map.put("Male", "male");
               map.put("Female", "female");
               gender.setMap(map);
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelAddStaff = new javax.swing.JPanel();
          labelPopUpTitle1 = new Components.LabelPopUpTitle();
          label1 = new Components.Label();
          staffName = new Components.TextField();
          label6 = new Components.Label();
          label4 = new Components.Label();
          address = new Components.TextField();
          label3 = new Components.Label();
          label5 = new Components.Label();
          gender = new Components.ComboBox();
          jLabel7 = new javax.swing.JLabel();
          jLabel11 = new javax.swing.JLabel();
          jLabel13 = new javax.swing.JLabel();
          jLabel14 = new javax.swing.JLabel();
          jLabel16 = new javax.swing.JLabel();
          buttonCancel = new ButtonPackage.ButtonCancel();
          buttonSave = new ButtonPackage.ButtonSave();
          label7 = new Components.Label();
          contact = new Components.TextField();
          role = new Components.ComboBox();
          label9 = new Components.Label();
          jLabel15 = new javax.swing.JLabel();
          label8 = new Components.Label();
          lbFile = new javax.swing.JLabel();
          buttonUpload = new Button.Button();
          dobDate = new DatePicker.DatePicker();
          startDate = new DatePicker.DatePicker();
          jLabel17 = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle1.setLabelTitle("Add Staff");

          label1.setLabelName("Staff Name");

          staffName.setLabelTextField("Staff Name");

          label6.setLabelName("Date of Birth");

          label4.setLabelName("Address");

          address.setLabelTextField("Address");

          label3.setLabelName("Start Date");

          label5.setLabelName("Gender");

          jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel7.setForeground(new java.awt.Color(204, 0, 0));
          jLabel7.setText("*");

          jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel11.setForeground(new java.awt.Color(204, 0, 0));
          jLabel11.setText("*");

          jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel13.setForeground(new java.awt.Color(204, 0, 0));
          jLabel13.setText("*");

          jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel14.setForeground(new java.awt.Color(204, 0, 0));
          jLabel14.setText("*");

          jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel16.setForeground(new java.awt.Color(204, 0, 0));
          jLabel16.setText("*");

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

          label7.setLabelName("Contact");

          contact.setLabelTextField("000 000 0000");

          label9.setLabelName("Role");

          jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel15.setForeground(new java.awt.Color(204, 0, 0));
          jLabel15.setText("*");

          label8.setLabelName("File Upload");

          lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          buttonUpload.setBackground(new java.awt.Color(47, 152, 70));
          buttonUpload.setButtonName("Browse to Upload");
          buttonUpload.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonUploadMouseClicked(evt);
               }
          });

          dobDate.setLabelTextField("Select Date");

          startDate.setLabelTextField("Select Date");

          jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel17.setForeground(new java.awt.Color(204, 0, 0));
          jLabel17.setText("*");

          javax.swing.GroupLayout panelAddStaffLayout = new javax.swing.GroupLayout(panelAddStaff);
          panelAddStaff.setLayout(panelAddStaffLayout);
          panelAddStaffLayout.setHorizontalGroup(
               panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(panelAddStaffLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addGroup(panelAddStaffLayout.createSequentialGroup()
                                        .addComponent(buttonUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelAddStaffLayout.createSequentialGroup()
                                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                  .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(2, 2, 2)
                                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(2, 2, 2)
                                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                  .addGap(39, 39, 39)
                                                  .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                       .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                             .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                  .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, 0)
                                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                            .addGap(1, 1, 1)))
                                                  .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(dobDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                  .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                  .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(2, 2, 2)
                                                  .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(panelAddStaffLayout.createSequentialGroup()
                                                  .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(0, 0, 0)
                                                  .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                              .addGap(0, 20, Short.MAX_VALUE))
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(0, 0, Short.MAX_VALUE))))
          );
          panelAddStaffLayout.setVerticalGroup(
               panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelAddStaffLayout.createSequentialGroup()
                    .addComponent(labelPopUpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(staffName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(panelAddStaffLayout.createSequentialGroup()
                                        .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(label9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(dobDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelAddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelAddStaffLayout.createSequentialGroup()
                              .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(8, 8, 8)
                              .addComponent(buttonUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelAddStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelAddStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String staffNameEn = staffName.getValueTextField();
         String dateOfBirth = dobDate.getValueTextField();
         String staffStartDate = startDate.getValueTextField();
         String staffAddress = address.getValueTextField();
         String staffContact = contact.getValueTextField();

         if (staffNameEn == null || staffNameEn.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Staff Name can not be empty!");
              return;
         }
         if (dateOfBirth == null || dateOfBirth.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Date of Birth can not be empty!");
              return;
         }
         if (staffStartDate == null || staffStartDate.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Start Date can not be empty!");
              return;
         }
         if (staffAddress == null || staffAddress.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Address can not be empty!");
              return;
         }
         if (genderId == null) {
              JOptionPane.showMessageDialog(this, "Please select a gender!");
              return;
         }
         if (roleId == null) {
              JOptionPane.showMessageDialog(this, "Please select a role!");
              return;
         }
         if (staffContact == null || staffContact.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Contact can not be empty!");
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
              .addFormDataPart("contact", staffContact);

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
                        JOptionPane.showMessageDialog(this, "The phone number already uesd!");
                        return;
                   }

                   if (response.isSuccessful()) {
                        StaffInformation list = new StaffInformation(new JFrame(), true);
                        listGetStaff.removeAll();
                        listGetStaff.revalidate();
                        listGetStaff.repaint();
                        list.getStaff(listGetStaff);
                        dispose();
                   }
              } else {
                   Response response = client.newCall(request).execute();
                   if (response.code() == 500) {
                        JOptionPane.showMessageDialog(this, "The phone number already uesd!");
                        return;
                   }
                   if (response.isSuccessful()) {
                        StaffInformation obj = new StaffInformation(new JFrame(), true);
                        listGetStaff.removeAll();
                        listGetStaff.revalidate();
                        listGetStaff.repaint();
                        obj.getStaff(listGetStaff);
                        dispose();
                   }

              }

              // Do something with the response.
         } catch (IOException e) {
              System.out.println("err = " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUploadMouseClicked
         try {
              path = JNAFileChooser.funChooseFile();
              JavaConstant.coverImagePath(path, lbFile, 124, 235);
         } catch (IOException ex) {
              Logger.getLogger(InsertStaff.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_buttonUploadMouseClicked

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


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Components.TextField address;
     private ButtonPackage.ButtonCancel buttonCancel;
     private ButtonPackage.ButtonSave buttonSave;
     private Button.Button buttonUpload;
     private Components.TextField contact;
     private DatePicker.DatePicker dobDate;
     private Components.ComboBox gender;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel14;
     private javax.swing.JLabel jLabel15;
     private javax.swing.JLabel jLabel16;
     private javax.swing.JLabel jLabel17;
     private javax.swing.JLabel jLabel7;
     private Components.Label label1;
     private Components.Label label3;
     private Components.Label label4;
     private Components.Label label5;
     private Components.Label label6;
     private Components.Label label7;
     private Components.Label label8;
     private Components.Label label9;
     private Components.LabelPopUpTitle labelPopUpTitle1;
     private javax.swing.JLabel lbFile;
     private javax.swing.JPanel panelAddStaff;
     private Components.ComboBox role;
     private Components.TextField staffName;
     private DatePicker.DatePicker startDate;
     // End of variables declaration//GEN-END:variables
}
