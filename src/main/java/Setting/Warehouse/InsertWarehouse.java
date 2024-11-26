package Setting.Warehouse;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
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
          event();
          txtWarehouseEn.requestFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtWarehouseEn.initEvent(btnevent);
          txtWarehouseKh.initEvent(btnevent);
     }

     //Value Edit
     public void setValueEdit(
          String warehouseNameEn,
          String warehouseNameKh
     ) throws IOException {

          if (warehouseNameEn != null && warehouseNameEn != "") {
               txtWarehouseEn.setValueTextField(warehouseNameEn);
          }

          if (warehouseNameKh != null && warehouseNameKh != "") {
               txtWarehouseKh.setValueTextField(warehouseNameKh);
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
        txtWarehouseKh = new Components.TextField();
        txtWarehouseEn = new Components.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(422, 201));

        titlePopUp.setLabelTitle("Add Warehouse");

        label1.setLabelName("Warehouse Name Kh");

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

        label3.setLabelName("Warehouse Name");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        txtWarehouseKh.setLabelTextField("Warehouse Name Kh");

        txtWarehouseEn.setLabelTextField("Warehouse Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtWarehouseEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(txtWarehouseKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtWarehouseEn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWarehouseKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
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
              if (warehouseNameEn == null || warehouseNameEn.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Warehouse Name is required!");
                   return;
              }

              JSONObject json = new JSONObject();
              json.put("warehouseNameEn", warehouseNameEn);
              json.put("warehouseNameKh", warehouseNameKh);
              json.put("createBy", JavaConstant.cashierId);

              if (id != null) {
                    Response response = JavaConnection.put(JavaRoute.warehouse + '/' + id, json);
                    String responeData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responeData);
                   
                    if (jsonResponse.has("error")) {
                        JSONObject error = jsonResponse.getJSONObject("error");
                        int code = error.getInt("code");
                        String reason = error.getString("reason");
                        if (code == 409) {
                            JOptionPane.showMessageDialog(this, reason);
                        }
                    }else{
                        listGetWarehouse.removeAll();
                        listGetWarehouse.revalidate();
                        listGetWarehouse.repaint();
                        obj.getWarehouse(listGetWarehouse, true,pageNumber);
                        dispose();
                    }
                    
              } else {
                  
                    Response response = JavaConnection.post(JavaRoute.warehouse, json);
                    String responeData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responeData);
                   
                    if (jsonResponse.has("error")) {
                        JSONObject error = jsonResponse.getJSONObject("error");
                        int code = error.getInt("code");
                        String reason = error.getString("reason");
                        if (code == 409) {
                            JOptionPane.showMessageDialog(this, reason);
                        }
                    }else{
                        listGetWarehouse.removeAll();
                        listGetWarehouse.revalidate();
                        listGetWarehouse.repaint();
                        obj.getWarehouse(listGetWarehouse, true,pageNumber);
                        dispose();
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private Components.Label label1;
    private Components.Label label3;
    private Components.LabelPopUpTitle titlePopUp;
    private Components.TextField txtWarehouseEn;
    private Components.TextField txtWarehouseKh;
    // End of variables declaration//GEN-END:variables
}
