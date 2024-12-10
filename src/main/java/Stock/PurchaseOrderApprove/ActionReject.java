package Stock.PurchaseOrderApprove;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Stock.PurchaseOrderCheck.DetailPurchaseOrderCheck;
import Stock.PurchaseOrderCheck.ListPurchaseOrderCheck;
import javax.swing.JOptionPane;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

public class ActionReject extends javax.swing.JDialog {

    private Integer id;
    private ListPurchaseOrderCheck obj;
    private DetailPurchaseOrderCheck detail;
    private String typeForm;
    
    public ActionReject(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtComment.requestFocus();
        event();
    }
    
    //Place Holder
    void event() {
        ButtonEvent btnevent = new ButtonEvent() {
            @Override
            public void onFocusGain() {

            }
        };
        txtComment.initEvent(btnevent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReject = new javax.swing.JPanel();
        labelPopUpTitle = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        button1 = new Button.Button();
        txtComment = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle.setLabelTitle("Reject");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        button1.setBackground(new java.awt.Color(47, 152, 70));
        button1.setButtonName("Save");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });

        txtComment.setLabelName("Comment *");
        txtComment.setName(""); // NOI18N
        txtComment.setPlaceHolder("Comment");

        javax.swing.GroupLayout panelRejectLayout = new javax.swing.GroupLayout(panelReject);
        panelReject.setLayout(panelRejectLayout);
        panelRejectLayout.setHorizontalGroup(
            panelRejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRejectLayout.createSequentialGroup()
                .addGroup(panelRejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRejectLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRejectLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelRejectLayout.setVerticalGroup(
            panelRejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRejectLayout.createSequentialGroup()
                .addComponent(labelPopUpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelRejectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
        
        String comment = txtComment.getValueTextField();

        try {
            
            boolean isCheck = JavaValidation.checkValidation(panelReject);
            
            if (isCheck) {
                JSONObject json = new JSONObject();
                json.put("rejectBy", JavaConstant.cashierId);
                json.put("msg", comment);

                Response response = JavaConnection.post(JavaRoute.imports + "/rejectPurchaseOrder/" + getId() , json);

                System.out.println("response : " + response);
                System.out.println("json : " + json);

                try {
                     if (response.isSuccessful()) {
                        dispose();
                        detail.dispose();
                        
                        if(typeForm.equals("checked")){
                             obj.setTypeForm("checked");
                             obj.setTitle("Purchase Check");
                             obj.setVisible(true);
                         }else {
                             obj.setTypeForm("approved");
                             obj.setTitle("Purchase Approval");
                             obj.setVisible(true);
                         }
                        
                         obj.getData(obj,true);
                     }
                } catch (Exception e) {
                     System.out.println("error : " + e);
                }
            }
            
        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }
    }//GEN-LAST:event_button1MouseClicked

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListPurchaseOrderCheck getObj() {
        return obj;
    }

    public void setObj(ListPurchaseOrderCheck obj) {
        this.obj = obj;
    }

    public DetailPurchaseOrderCheck getDetail() {
        return detail;
    }

    public void setDetail(DetailPurchaseOrderCheck detail) {
        this.detail = detail;
    }

    public String getTypeForm() {
        return typeForm;
    }

    public void setTypeForm(String typeForm) {
        this.typeForm = typeForm;
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
            java.util.logging.Logger.getLogger(ActionReject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActionReject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActionReject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActionReject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActionReject dialog = new ActionReject(new javax.swing.JFrame(), true);
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
    private Button.Button button1;
    private ButtonPackage.ButtonCancel buttonCancel;
    private Components.LabelPopUpTitle labelPopUpTitle;
    private javax.swing.JPanel panelReject;
    private FormComponent.JavaTextField txtComment;
    // End of variables declaration//GEN-END:variables
}
