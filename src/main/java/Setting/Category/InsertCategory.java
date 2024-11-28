package Setting.Category;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import LoginAndLogoutForm.LoginFormJdailog;
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
public class InsertCategory extends javax.swing.JDialog {

    private String departmentId = "-1";
    private String divisionId = "-1";
    private Integer id;
    private String code;
    private JPanel listGetCategory;
    private Integer movePosition;
    private Integer parentId;
    private JPanel category;
    private LoginFormJdailog jdLogin;
    private String pageNumber;
    private Category obj;

    public InsertCategory(java.awt.Frame parent, boolean modal, String codeType) {
        super(parent, modal);
        initComponents();
        nameEn.requestFocus();
        setCode(codeType);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        cmdDivision();
    }

    //Value Edit
    public void setValueEdit(
            String cateNameEn,
            String cateNameKh,
            String idDivision,
            String idDepartment
    ) throws IOException {
        if (cateNameEn != null && cateNameEn != "") {
            nameEn.setText(cateNameEn);
        }

        if (cateNameKh != null && cateNameKh != "") {
            nameKh.setText(cateNameKh);
        }

        comboDivision.setSelectedItem(idDivision);
        comboDepartment.setSelectedItem(idDepartment);
    }
     
    // SELECT COMBOBOX
    private void cmdDivision(){
        JavaComboBoxSelection.addComboBox(comboDivision,
                JavaRoute.category,
                "catNameEn",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                divisionId = id;
                cmdDepartment(divisionId);
            }
        };
        comboDivision.initEvent(event);
    }
    
    private void cmdDepartment(String divisionId){
       
        JavaComboBoxSelection.addComboBox(comboDepartment,
                JavaRoute.getParentById + divisionId,
                "catNameEn",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                departmentId = id;
            }
        };
        comboDepartment.initEvent(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        comboDivision = new FormComponent.combobox.JavaCombobox();
        comboDepartment = new FormComponent.combobox.JavaCombobox();
        nameEn = new FormComponent.JavaTextField();
        nameKh = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Add Category");

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

        comboDivision.setLabelName("Division *");

        comboDepartment.setLabelName("Department *");

        nameEn.setLabelName("Category Name *");
        nameEn.setPlaceHolder("Category Name");

        nameKh.setLabelName("Category Name (KH)");
        nameKh.setPlaceHolder("Category Name (KH)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(comboDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
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
            
            boolean isCheck = JavaValidation.checkValidation(jPanel1);
            
            if (isCheck) {
                 
                JSONObject json = new JSONObject();
                json.put("catNameEn", categoryName);
                if (categoryNameKh == null) {
                    json.put("catNameKh", categoryNameKh);
                } else {
                    if (categoryNameKh.isEmpty()) {
                        json.put("catNameKh", JSONObject.NULL);
                    } else {
                        json.put("catNameKh", categoryNameKh);
                    }
                }
                json.put("parentId", departmentId);
                
                // create response 
                Response response = null;
                if (id != null) { // add new
                    json.put("movePosition", movePosition);
                    response = JavaConnection.put(JavaRoute.addCategory + '/' + id, json);
                } else { // update 
                    json.put("createBy", JavaConstant.cashierId);
                    json.put("code", code);
                    response = JavaConnection.post(JavaRoute.addCategory, json);
                }
                
                System.out.println("json : " + json);
                System.out.println("response : " + response);

                // check if name already exist
                List<JavaConflicValidation> fields = new ArrayList<>();

                fields.add(JavaConflicValidation.builder()
                        .key("name") // specific word that exist in key "reason"
                        .msg("The field name is already existed!") // message to show 
                        .field(nameKh) // obj of JavaTextField
                        .build());

                /* 
                        isExist = true ( name not yet used )
                        isExist =  false ( name already used )
                 */
                boolean isExist = JavaValidation.checkNameExistSecondFunction(response, fields);

                try {
                    if (response.isSuccessful() && isExist) {

                        Category list = new Category(new JFrame(), true, code);
                        listGetCategory.removeAll();
                        listGetCategory.revalidate();
                        listGetCategory.repaint();
                        list.getCategory(listGetCategory, code, true, pageNumber);
                        dispose();
                    }

                } catch (Exception e) {
                    System.err.println("error post category : " + e);
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
    private FormComponent.combobox.JavaCombobox comboDepartment;
    private FormComponent.combobox.JavaCombobox comboDivision;
    private javax.swing.JPanel jPanel1;
    private FormComponent.JavaTextField nameEn;
    private FormComponent.JavaTextField nameKh;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
