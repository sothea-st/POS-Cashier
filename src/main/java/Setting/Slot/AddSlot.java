package Setting.Slot;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import FormComponent.combobox.JavaComboBoxSelection;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;
@Setter
@Getter

public class AddSlot extends javax.swing.JDialog {

    private String rangeId;
    private Integer id;
    private JPanel listGetSlot;
    private String pageNumber;
    private ListSlot obj;
    
    public AddSlot(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        txtSlot.requestFocus();
        cmdWarehouse();
    }

    private void cmdWarehouse(){
        JavaComboBoxSelection.addComboBox(comboRange,
                JavaRoute.range,
                "rangeNameEn",
                JavaComboBoxSelection.DESC);

        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onSelected(String id) {
                rangeId = id;
            }
        };
        comboRange.initEvent(event);
    }
     
     
    //Value Edit
    public void setValueEdit(
        String rangeName,
        String rangeNameKh,
        String idWarehouse
    ) throws IOException {
        
        if(rangeName != null && rangeName != ""){
            txtSlot.setText(rangeName);  
        }
        
        if(rangeNameKh != null && rangeNameKh != ""){
            txtSlotKh.setText(rangeNameKh);
        }  
        
        comboRange.setSelectedItem(idWarehouse);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        comboRange = new FormComponent.combobox.JavaCombobox();
        txtSlot = new FormComponent.JavaTextField();
        txtSlotKh = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Add Slot");

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

        comboRange.setLabelName("Range *");

        txtSlot.setLabelName("Slot Name *");
        txtSlot.setPlaceHolder("Slot Name");

        txtSlotKh.setLabelName("Slot Name (KH)");
        txtSlotKh.setPlaceHolder("Slot Name (KH)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSlotKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(comboRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSlotKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        String slotNameEn = txtSlot.getValueTextField();
        String slotNameKh = txtSlotKh.getValueTextField();
        
        try {

            boolean isCheck = JavaValidation.checkValidation(jPanel1);

            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("slotNameEn", slotNameEn);
                json.put("slotNameKh", slotNameKh);
                json.put("rangeId", rangeId);
                json.put("createBy", JavaConstant.cashierId);

                // create response 
                Response response = null;
                if (id != null) { // update
                    response = JavaConnection.put(JavaRoute.slot + '/' + id, json);
                } else { // add new 
                    response = JavaConnection.post(JavaRoute.slot, json);
                }

                try {
                    if (response.isSuccessful()) {
                        
                        dispose();
                        ListSlot list = new ListSlot(new JFrame(), true);
                        list.setVisible(true);
                        listGetSlot.removeAll();
                        listGetSlot.revalidate();
                        listGetSlot.repaint();
                        list.getSlot(listGetSlot,true,pageNumber);
                        
                    }

                } catch (Exception e) {
                    System.err.println("error post slot : " + e);
                }
            }

        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }
        
//        
//
//        try {
//
//            JSONObject json = new JSONObject();
//            json.put("slotNameEn", slotNameEn);
//            json.put("slotNameKh", slotNameKh);
//            json.put("rangeId", rangeId);
//            json.put("createBy", JavaConstant.cashierId);
//
//            if (id != null) {
//                Response response = JavaConnection.put(JavaRoute.slot + '/' + id, json);
//                
//                String responeData = response.body().string();
//                JSONObject jsonResponse = new JSONObject(responeData);
//                
//                if (jsonResponse.has("error")) {
//
//                    JOptionPane.showMessageDialog(this, "Save Failed!");
//                    
//                }else{
//                    listGetSlot.removeAll();
//                    listGetSlot.revalidate();
//                    listGetSlot.repaint();
//                    obj.getSlot(listGetSlot,true,pageNumber);
//                    dispose();
//                }
//
//            } else {
//                Response response = JavaConnection.post(JavaRoute.range, json);
//                String responeData = response.body().string();
//                JSONObject jsonResponse = new JSONObject(responeData);
//                
//                if (jsonResponse.has("error")) {
//                    
//                    JOptionPane.showMessageDialog(this, "Save Failed!");
//                    
//                }else{
//                    listGetSlot.removeAll();
//                    listGetSlot.revalidate();
//                    listGetSlot.repaint();
//                    obj.getSlot(listGetSlot,true,pageNumber);
//                    dispose();
//                }
//            }
//
//        } catch (Exception e) {
//            System.err.println("errr -- " + e);
//        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        titlePopUp.setLabelTitle("Edit Slot");
    }

    public JPanel getListGetSlot() {
        return listGetSlot;
    }

    public void setListGetSlot(JPanel listGetSlot) {
        this.listGetSlot = listGetSlot;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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
            java.util.logging.Logger.getLogger(AddSlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddSlot dialog = new AddSlot(new javax.swing.JFrame(), true);
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
    private FormComponent.combobox.JavaCombobox comboRange;
    private javax.swing.JPanel jPanel1;
    private Components.LabelPopUpTitle titlePopUp;
    private FormComponent.JavaTextField txtSlot;
    private FormComponent.JavaTextField txtSlotKh;
    // End of variables declaration//GEN-END:variables
}
