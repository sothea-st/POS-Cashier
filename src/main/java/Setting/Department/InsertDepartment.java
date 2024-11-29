package Setting.Department;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import Setting.Category.Category;
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
public class InsertDepartment extends javax.swing.JDialog {

    private String divisionId = "-1";
    private String code;
    private JPanel listGetCategory;
    private Integer id;
    private Integer movePosition;
    private String pageNumber;
    private Category obj;

    public InsertDepartment(java.awt.Frame parent, boolean modal, String codeType) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setCode(codeType);
        departmentName.requestFocus();

        // action get select 
        cmdDivision();
    }

    //Value Edit
    public void setValueEdit(
            String departmentEn,
            String departmentKh,
            String idDivision
    ) throws IOException {

        if (departmentEn != null && departmentEn != "") {
            departmentName.setText(departmentEn);
        }

        if (departmentKh != null && departmentKh != "") {
            departmentNameKh.setText(departmentKh);
        }

        comboDivision.setSelectedItem(idDivision);
    }

    private void cmdDivision(){
        JavaComboBoxSelection.addComboBox(comboDivision,
                JavaRoute.category,
                "catNameEn",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                divisionId = id;
            }
        };
        comboDivision.initEvent(event);
    }
     
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        comboDivision = new FormComponent.combobox.JavaCombobox();
        departmentName = new FormComponent.JavaTextField();
        departmentNameKh = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Add Department");

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

        departmentName.setLabelName("Department Name *");
        departmentName.setPlaceHolder("Department Name");

        departmentNameKh.setLabelName("Department Name (KH)");
        departmentNameKh.setPlaceHolder("Department Name (KH)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departmentNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String categoryName = departmentName.getValueTextField();
         String categoryNameKh = departmentNameKh.getValueTextField();
         
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
                json.put("parentId", divisionId);

                // create response 
                Response response = null;
                if (id != null) { // add new
                    json.put("movePosition", movePosition);
                    response = JavaConnection.put(JavaRoute.addCategory + '/' + id, json);
                } else { // update 
                    json.put("createBy", JavaConstant.cashierId);
                    json.put("code", "department");
                    response = JavaConnection.post(JavaRoute.addCategory, json);
                }
                
                System.out.println("json : " + json);
                System.out.println("response : " + response);

                // check if name already exist
                List<JavaConflicValidation> fields = new ArrayList<>();

                fields.add(JavaConflicValidation.builder()
                        .key("name") // specific word that exist in key "reason"
                        .msg("This name is already existed!") // message to show 
                        .field(departmentNameKh) // obj of JavaTextField
                        .build());

                /* 
                        isExist = true ( name not yet used )
                        isExist =  false ( name already used )
                 */
                boolean isExist = JavaValidation.checkNameExistSecondFunction(response, fields);

                System.out.println("isExist : " + isExist);

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        titlePopUp.setLabelTitle("Edit Department");
    }

    public Integer getMovePosition() {
        return movePosition;
    }

    public void setMovePosition(Integer movePosition) {
        this.movePosition = movePosition;
    }

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(InsertDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InsertDepartment dialog = new InsertDepartment(new javax.swing.JFrame(), true, null);
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
    private FormComponent.combobox.JavaCombobox comboDivision;
    private FormComponent.JavaTextField departmentName;
    private FormComponent.JavaTextField departmentNameKh;
    private javax.swing.JPanel jPanel1;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
