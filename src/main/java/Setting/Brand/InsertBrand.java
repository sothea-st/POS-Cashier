package Setting.Brand;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import okhttp3.Response;
import org.json.JSONObject;

public class InsertBrand extends javax.swing.JDialog {

    private JPanel listGetBrand;
    private Integer id;
    
    public InsertBrand(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        event();
        brandEn.requestFocus();
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
        brandEn.initEvent(btnevent);
        brandKh.initEvent(btnevent);
    }
    
    
        //Value Edit
    public void setValueEdit(
        String brandNameEn,
        String brandNameKh
    ) throws IOException {
        
        if(brandNameEn != null && brandNameEn != ""){
            brandEn.setValueTextField(brandNameEn);  
        }
        
        if(brandNameKh != null && brandNameKh != ""){
            brandKh.setValueTextField(brandNameKh);
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
        brandKh = new Components.TextField();
        brandEn = new Components.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(422, 201));

        titlePopUp.setLabelTitle("Add Brand");

        label1.setLabelName("Brand Name Kh");

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

        label3.setLabelName("Brand Name");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        brandKh.setLabelTextField("Brand Name Kh");

        brandEn.setLabelTextField("Brand Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(brandEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(brandKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(brandEn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
        String brandNameEn = brandEn.getValueTextField();
        String brandNameKh = brandKh.getValueTextField();

        try {
            if (brandNameEn == null || brandNameEn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Brand Name is required!");
                return;
            }

            JSONObject json = new JSONObject();
            json.put("brandNameEn", brandNameEn);
            json.put("brandNameKh", brandNameKh);

            if (id != null) {
                Response response = JavaConnection.put(JavaRoute.brand + '/' + id, json);

                if (response.isSuccessful()) {
                    ListBrand list = new ListBrand(new JFrame(), true);
                    listGetBrand.removeAll();
                    listGetBrand.revalidate();
                    listGetBrand.repaint();
                    list.getBrand(listGetBrand,true);
                    dispose();

                } else if (response.code() == 500) {
                    JOptionPane.showMessageDialog(this, "The Name is already used!");
                } else {
                    JOptionPane.showMessageDialog(this, "Save Failed!");
                }

            } else {
                json.put("createBy", JavaConstant.cashierId);

                Response response = JavaConnection.post(JavaRoute.brand, json);
                
                System.out.println("response : " + response);
                System.out.println("json : " + json);

                if (response.isSuccessful()) {
                    ListBrand list = new ListBrand(new JFrame(), true);
                    listGetBrand.removeAll();
                    listGetBrand.revalidate();
                    listGetBrand.repaint();
                    list.getBrand(listGetBrand, true);
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

    public JPanel getListGetBrand() {
        return listGetBrand;
    }

    public void setListGetBrand(JPanel listGetBrand) {
        this.listGetBrand = listGetBrand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        titlePopUp.setLabelTitle("Edit Brand");
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
            java.util.logging.Logger.getLogger(InsertBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertBrand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InsertBrand dialog = new InsertBrand(new javax.swing.JFrame(), true);
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
    private Components.TextField brandEn;
    private Components.TextField brandKh;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private Components.Label label1;
    private Components.Label label3;
    private Components.LabelPopUpTitle titlePopUp;
    // End of variables declaration//GEN-END:variables
}
