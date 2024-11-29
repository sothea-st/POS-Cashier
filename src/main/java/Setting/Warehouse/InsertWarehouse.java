package Setting.Warehouse;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaConflicValidation;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;
import pagination.PaginationPanel;

@Setter
@Getter
public class InsertWarehouse extends javax.swing.JDialog {

     private JPanel listGetWarehouse;
     private Integer id;
     private PaginationPanel paginationPanel;
     private String pageNumber;
     private ListWarehouse obj;

     public InsertWarehouse(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          txtWarehouseEn.requestFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
     }

     //Value Edit
     public void setValueEdit(
          String warehouseNameEn,
          String warehouseNameKh
     ) throws IOException {

          if (warehouseNameEn != null && warehouseNameEn != "") {
               txtWarehouseEn.setText(warehouseNameEn);
          }

          if (warehouseNameKh != null && warehouseNameKh != "") {
               txtWarehouseKh.setText(warehouseNameKh);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        txtWarehouseEn = new FormComponent.JavaTextField();
        txtWarehouseKh = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(422, 201));

        titlePopUp.setLabelTitle("Add Warehouse");

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

        txtWarehouseEn.setLabelName("Warehouse Name *");
        txtWarehouseEn.setPlaceHolder("Warehouse Name");

        txtWarehouseKh.setLabelName("Warehouse Name (KH)");
        txtWarehouseKh.setPlaceHolder("Warehouse Name (KH)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWarehouseKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWarehouseEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(txtWarehouseEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtWarehouseKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String warehouseNameEn = txtWarehouseEn.getValueTextField();
         String warehouseNameKh = txtWarehouseKh.getValueTextField();
         
         
         try {
            
            boolean isCheck = JavaValidation.checkValidation(jPanel1);
            
            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("warehouseNameEn", warehouseNameEn);
                json.put("warehouseNameKh", warehouseNameKh);
                json.put("createBy", JavaConstant.cashierId);
                
                // create response 
                Response response = null;
                if (id != null) { // update
                    response = JavaConnection.put(JavaRoute.warehouse + '/' + id, json);
                } else { // add new 
                    response = JavaConnection.post(JavaRoute.warehouse, json);
                }
                
                // check if name already exist
                List<JavaConflicValidation> fields = new ArrayList<>();

                fields.add(JavaConflicValidation.builder()
                        .key("WarehouseNameEn") // specific word that exist in key "reason"
                        .msg("This name is already existed!") // message to show 
                        .field(txtWarehouseEn) // obj of JavaTextField
                        .build());
                
                fields.add(JavaConflicValidation.builder()
                        .key("WarehouseNameKh") // specific word that exist in key "reason"
                        .msg("This name is already existed!") // message to show 
                        .field(txtWarehouseKh) // obj of JavaTextField
                        .build());

                /* 
                        isExist = true ( name not yet used )
                        isExist =  false ( name already used )
                 */
                boolean isExist = JavaValidation.checkNameExist(response, fields);

                try {
                    if (response.isSuccessful() && isExist) {
                        listGetWarehouse.removeAll();
                        listGetWarehouse.revalidate();
                        listGetWarehouse.repaint();
                        obj.getWarehouse(listGetWarehouse, true,pageNumber);
                        dispose();
                    }

                } catch (Exception e) {
                    System.err.println("error post warehouse : " + e);
                }
            }

        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

     public JPanel getListGetWarehouse() {
          return listGetWarehouse;
     }

     public void setListGetWarehouse(JPanel listGetWarehouse) {
          this.listGetWarehouse = listGetWarehouse;
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
          titlePopUp.setLabelTitle("Edit Warehouse");
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
               java.util.logging.Logger.getLogger(InsertWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(InsertWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(InsertWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(InsertWarehouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    InsertWarehouse dialog = new InsertWarehouse(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private Components.LabelPopUpTitle titlePopUp;
    private FormComponent.JavaTextField txtWarehouseEn;
    private FormComponent.JavaTextField txtWarehouseKh;
    // End of variables declaration//GEN-END:variables
}
