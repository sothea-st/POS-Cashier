package Setting.Category;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import LoginAndLogoutForm.LoginFormJdailog;
import Model.combobox.CategoryModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
@Setter
@Getter
public class InsertCategory extends javax.swing.JDialog {

     private String departmentId;
     private String divisionId;
     private Integer id;
     private String code;
     private JPanel listGetCategory;
     private Integer movePosition;
     private Integer parentId;
     private JPanel category;
     private LoginFormJdailog jdLogin;

     public InsertCategory(java.awt.Frame parent, boolean modal, String codeType) {
          super(parent, modal);
          initComponents();
          event();
          nameEn.requestFocus();
          setCode(codeType);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          // action get select 
          ButtonEvent eventtss = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    divisionId = key;
                    addComboDepartment(divisionId);
               }
          };
          comboDivision.initEvent(eventtss);
          addComboDivision();

          // action get select 
          ButtonEvent events = new ButtonEvent() {
               @Override
               public void onSelect(String key) {
                    departmentId = key;
               }
          };
          comboDepartment.initEvent(events);
     }

     //Value Edit
     public void setValueEdit(
          String cateNameEn,
          String cateNameKh,
          String idDivision,
          String idDepartment
     ) throws IOException {
          if (cateNameEn != null && cateNameEn != "") {
               nameEn.setValueTextField(cateNameEn);
          }

          if (cateNameKh != null && cateNameKh != "") {
               nameKh.setValueTextField(cateNameKh);
          }

          comboDivision.setToLastItem(idDivision);
          comboDepartment.setToLastItem(idDepartment);
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          nameEn.initEvent(btnevent);
          nameKh.initEvent(btnevent);
     }

     //Set Combo box division
     private void addComboDivision() {

          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<CategoryModel> category = new ArrayList<>();

               Response response = JavaConnection.get(JavaRoute.category);

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         CategoryModel cat = new CategoryModel(
                              obj.getInt("id"),
                              obj.getString("catNameEn")
                         );

                         category.add(cat);

                         int idCat = category.get(i).getCategoryId();
                         String catName = category.get(i).getCategoryName();
                         map.put(catName, "" + idCat);
                    }
                    comboDivision.setMap(map);

               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     //Set Combo box department
     private void addComboDepartment(String divisionId) {

          comboDepartment.revalidate();
          comboDepartment.invalidate();

          // remove item by index
          if (comboDepartment.countItem() > 1) {
               comboDepartment.removeAllItem();
          }

          try {
               HashMap<String, String> map = new HashMap<>();
               ArrayList<CategoryModel> category = new ArrayList<>();
               Response response = JavaConnection.get(JavaRoute.getParentById + divisionId);
               if (response.isSuccessful()) {

                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                         JSONObject obj = data.getJSONObject(i);
                         CategoryModel cat = new CategoryModel(
                              obj.getInt("id"),
                              obj.getString("catNameEn")
                         );

                         category.add(cat);

                         int idCat = category.get(i).getCategoryId();
                         String catName = category.get(i).getCategoryName();
                         map.put(catName, "" + idCat);
                    }

                    comboDepartment.setMap(map);

               } else {
                    System.err.println("fail loading data");
               }
          } catch (Exception e) {
               System.err.println("error = " + e);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        label1 = new Components.Label();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        label3 = new Components.Label();
        jLabel12 = new javax.swing.JLabel();
        nameKh = new Components.TextField();
        nameEn = new Components.TextField();
        label2 = new Components.Label();
        comboDivision = new Components.ComboBox();
        jLabel13 = new javax.swing.JLabel();
        label4 = new Components.Label();
        comboDepartment = new Components.ComboBox();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Add Category");

        label1.setLabelName("Category Name Kh");

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

        label3.setLabelName("Category Name");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        nameKh.setLabelTextField("Category Name Kh");

        nameEn.setLabelTextField("Category Name");

        label2.setLabelName("Division");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");

        label4.setLabelName("Department ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboDivision, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addComponent(nameEn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String categoryName = nameEn.getValueTextField();
         String categoryNameKh = nameKh.getValueTextField();

         try {
              if (divisionId == null || divisionId.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please select a division!");
                   return;
              }

              if (departmentId == null || departmentId.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please select a department!");
                   return;
              }

              if (categoryName == null || categoryName.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Category Name is required!");
                   return;
              }

              JSONObject json = new JSONObject();
              json.put("catNameEn", categoryName);
              json.put("catNameKh", categoryNameKh);
              json.put("parentId", departmentId);

              if (id != null) {

                   json.put("movePosition", movePosition);

                   Response response = JavaConnection.put(JavaRoute.addCategory + '/' + id, json);
                   System.out.println("response : " + response);
                   System.out.println("json : " + json);

                   if (response.isSuccessful()) {
                        Category list = new Category(new JFrame(), true, code);
                        listGetCategory.removeAll();
                        listGetCategory.revalidate();
                        listGetCategory.repaint();
                        list.getCategory(listGetCategory, code, true);
                        dispose();

                   } else if (response.code() == 500) {
                        JOptionPane.showMessageDialog(this, "The Name is already used!");
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
                   }

              } else {

                   json.put("createBy", JavaConstant.cashierId);
                   json.put("code", code);

                   Response response = JavaConnection.post(JavaRoute.addCategory, json);

                   System.out.println("response : " + response);
                   System.out.println("json : " + json);

                   if (response.isSuccessful()) {
                        Category list = new Category(new JFrame(), true, code);
                        listGetCategory.removeAll();
                        listGetCategory.revalidate();
                        listGetCategory.repaint();
                        list.getCategory(listGetCategory, code, true);
                        dispose();
                   } else if (response.code() == 500) {
                        JOptionPane.showMessageDialog(this, "The Name is already used!");
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
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
          titlePopUp.setLabelTitle("Edit Category");
     }

     public String getCode() {
          return code;
     }

     public void setCode(String code) {
          this.code = code;
     }

     public JPanel getListGetCategory() {
          return listGetCategory;
     }

     public void setListGetCategory(JPanel listGetCategory) {
          this.listGetCategory = listGetCategory;
     }

     public Integer getMovePosition() {
          return movePosition;
     }

     public void setMovePosition(Integer movePosition) {
          this.movePosition = movePosition;
     }

     public Integer getParentId() {
          return parentId;
     }

     public void setParentId(Integer parentId) {
          this.parentId = parentId;
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
               java.util.logging.Logger.getLogger(InsertCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(InsertCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(InsertCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(InsertCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    InsertCategory dialog = new InsertCategory(new javax.swing.JFrame(), true, null);
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
    private Components.ComboBox comboDepartment;
    private Components.ComboBox comboDivision;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private Components.Label label1;
    private Components.Label label2;
    private Components.Label label3;
    private Components.Label label4;
    private Components.TextField nameEn;
    private Components.TextField nameKh;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
