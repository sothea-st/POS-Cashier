package feature.adjustment;

import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import FormComponent.combobox.JavaComboBoxSelection;
import feature.adjustment.adjustment_controller.CreateAdjustmentController;
import feature.adjustment.model.ProductAdjustment;
import feature.adjustment.model.ProductBarcode.ProductBarcodeDetail;
import feature.adjustment.model.request.AdjustmentRequest;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaValidation;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class AdjustmentCreateForm extends javax.swing.JDialog {

     private List<ProductBarcodeDetail> listItems = new ArrayList<>();
     private AdjustmentCreateForm adjustmentCreateForm;
     private CreateAdjustmentController controller;
     private Integer adjustmentId;

     public AdjustmentCreateForm(java.awt.Frame parent, boolean modal) {

          super(parent, modal);

          initComponents();

          this.adjustmentCreateForm = this;
          controller = new CreateAdjustmentController(adjustmentCreateForm);

          cmdReason();

          custom();

          barcodeController();
     }

     private void custom() {
          JavaConstant.setScroll(jScrollPane);
          panelData.setLayout(new BoxLayout(panelData, BoxLayout.Y_AXIS));

     }

     private void barcodeController() {
          controller.eventBarcode();
     }

     public void update(Integer id) {
          this.adjustmentId = id;
          controller.update(id);
     }

     private void cmdReason() {
          JavaComboBoxSelection.addComboBox(objReason,
               JavaRoute.reason + "Adjustment",
               "reason",
               JavaComboBoxSelection.DESC);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          btnCancel = new Button.Button();
          panelTop = new javax.swing.JPanel();
          objTransactionDate = new FormComponent.datepicker.JavaDatePicker();
          objReference = new FormComponent.JavaTextField();
          objReason = new FormComponent.combobox.JavaCombobox();
          objComment = new FormComponent.JavaTextField();
          objBarcode = new FormComponent.JavaTextField();
          headerFormCreate1 = new feature.adjustment.component.HeaderFormCreate();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          buttonSave1 = new ButtonPackage.ButtonSave();
          buttonSave2 = new ButtonPackage.ButtonSave();
          boxTotal = new feature.adjustment.component.BoxTotal();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          objTransactionDate.setLabelName("Transaction Date");

          objReference.setLabelName("Reference *");
          objReference.setPlaceHolder("Reference");

          objReason.setLabelName(" Reason *");
          objReason.setName(""); // NOI18N

          objComment.setLabelName("Comment");
          objComment.setPlaceHolder("Comment");

          objBarcode.setLabelName("Barcode");
          objBarcode.setPlaceHolder("Barcode");

          javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
          panelTop.setLayout(panelTopLayout);
          panelTopLayout.setHorizontalGroup(
               panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelTopLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelTopLayout.createSequentialGroup()
                              .addComponent(objReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(1, 1, 1))
                         .addGroup(panelTopLayout.createSequentialGroup()
                              .addComponent(objBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objTransactionDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(objComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(20, 20, 20))
          );
          panelTopLayout.setVerticalGroup(
               panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelTopLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(objBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objComment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(objTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(10, Short.MAX_VALUE))
          );

          jScrollPane.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1418, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 493, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSave1MouseClicked(evt);
               }
          });

          buttonSave2.setTitleButton("Save & Close");
          buttonSave2.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSave2MouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGap(0, 0, Short.MAX_VALUE)
                              .addComponent(boxTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelLayout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(6, 6, 6)
                              .addComponent(buttonSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(jScrollPane)
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(headerFormCreate1, javax.swing.GroupLayout.PREFERRED_SIZE, 1418, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(20, 20, 20))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                    .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(headerFormCreate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(boxTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(buttonSave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
          AdjustmentForm form = new AdjustmentForm(new JFrame(), true);
          form.setVisible(true);
     }//GEN-LAST:event_btnCancelMouseClicked

     private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked
          save(false);
     }//GEN-LAST:event_buttonSave1MouseClicked

     private void buttonSave2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave2MouseClicked
          save(true);
     }//GEN-LAST:event_buttonSave2MouseClicked

     private void save(boolean isClose) {

          System.err.println("reasonId : " + objReason.getSelectedItem());
          System.err.println("reference : " + objReference.getValueTextField());
          System.err.println("transactionDate : " + objTransactionDate.getSelectedDate());
          System.err.println("comment : " + objComment.getValueTextField());
          System.err.println("totalQty : " + boxTotal.getTxtTotalQty().getText());

          Boolean isCheck = JavaValidation.checkValidation(panelTop);

          if (isCheck) {
               if (boxTotal.getTxtTotalQty().getText().equals("0")) {
                    JavaAlertMessage alertMessage = new JavaAlertMessage(new JFrame(), true);
                    alertMessage.setMessage("Total Qty can not be 0 !");
                    alertMessage.setVisible(true);
                    return;
               }

               if (boxTotal.getTxtTotalAmount().getText().equals("0")) {
                    JavaAlertMessage alertMessage = new JavaAlertMessage(new JFrame(), true);
                    alertMessage.setMessage("Total Amount can not be 0 !");
                    alertMessage.setVisible(true);
                    return;
               }

               JSONObject json = new JSONObject();
               json.put("reasonId", objReason.getSelectedItem());
               json.put("reference", objReference.getValueTextField());
               json.put("transactionDate", objTransactionDate.getSelectedDate());
               json.put("comment", objComment.getValueTextField());
               json.put("totalQty", boxTotal.getTxtTotalQty().getText());
               json.put("totalCost", JavaConstant.getReplace(boxTotal.getTxtTotalAmount().getText()));

               List<AdjustmentRequest> details = new ArrayList<>();

               for (ProductAdjustment data : controller.getListTmp()) {

                    if (data.getAdjustQty() == null || data.getAdjustQty() == 0) {
                         JavaAlertMessage alertMessage = new JavaAlertMessage(new JFrame(), true);
                         alertMessage.setMessage("Invalid adjust qty can not be empty or 0 !");
                         alertMessage.setVisible(true);
                         return;
                    }

                    details.add(AdjustmentRequest.builder()
                         .productId(data.getId())
                         .qty(data.getAdjustQty())
                         .build());
               }
               json.put("details", details);

               Response response = null;

               if (adjustmentId == null) {
                    response = JavaConnection.post(JavaRoute.adjustment, json);
               } else {
                    response = JavaConnection.put(JavaRoute.adjustment + "/" + adjustmentId, json);
               }

               System.err.println("low view json : " + json);
               System.err.println("low view response : " + response);

               try {
                    if (response.isSuccessful()) {
                         if (!isClose) {
                              controller.getListTmp().clear();
                              objReason.setToFirstItem();
                              cmdReason();
                              objReference.setText(null);
                              objTransactionDate.setSelectedDate(JavaConstant.getCurrentDate());
                              objComment.setText(null);
                              panelData.removeAll();
                              panelData.revalidate();
                              panelData.repaint();
                              boxTotal.getTxtTotalQty().setText("0");
                              boxTotal.getTxtTotalAmount().setText("0");
                         } else {
                              dispose();
                              AdjustmentForm adjustmentForm = new AdjustmentForm(new JFrame(), true);
                              adjustmentForm.setVisible(isClose);
                         }
                    }
               } catch (Exception e) {
                    System.err.println("error post adjustment : " + e);
               }
          }

     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AdjustmentCreateForm dialog = new AdjustmentCreateForm(new javax.swing.JFrame(), true);
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
     private feature.adjustment.component.BoxTotal boxTotal;
     private Button.Button btnCancel;
     private ButtonPackage.ButtonSave buttonSave1;
     private ButtonPackage.ButtonSave buttonSave2;
     private feature.adjustment.component.HeaderFormCreate headerFormCreate1;
     private javax.swing.JScrollPane jScrollPane;
     private FormComponent.JavaTextField objBarcode;
     private FormComponent.JavaTextField objComment;
     private FormComponent.combobox.JavaCombobox objReason;
     private FormComponent.JavaTextField objReference;
     private FormComponent.datepicker.JavaDatePicker objTransactionDate;
     private javax.swing.JPanel panel;
     private javax.swing.JPanel panelData;
     private javax.swing.JPanel panelTop;
     // End of variables declaration//GEN-END:variables
}
