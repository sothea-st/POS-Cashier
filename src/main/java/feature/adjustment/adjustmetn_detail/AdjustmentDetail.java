package feature.adjustment.adjustmetn_detail;

import Components.Color.WindowColor;
import feature.adjustment.AdjustmentForm;
import javax.swing.JFrame;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdjustmentDetail extends javax.swing.JDialog {
     
     private Integer adjustmentId;
     private AdjustmentDetailController controller;
     private AdjustmentDetail adjustmentDetail;
     
     public AdjustmentDetail(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          
          initComponents();
          
          this.adjustmentDetail = this;

          custom();
     }

     private void custom() {
          setBackground(WindowColor.slightGreen);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          
          objHeader.hideAction();
     }
     
     public void setAdjustmentId(Integer id){
          this.adjustmentId = id;
          
          controller = new AdjustmentDetailController(adjustmentDetail);
          controller.readById(adjustmentId);
          
     }
     
    

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          labelPopUpTitle4 = new Components.LabelPopUpTitle();
          objTransactionDate = new feature.main_detail.MainTitleDetail();
          btnCancel = new Button.Button();
          objReason = new feature.main_detail.MainTitleDetail();
          objTransaction = new feature.main_detail.MainTitleDetail();
          objReference = new feature.main_detail.MainTitleDetail();
          objComment = new feature.main_detail.MainTitleDetail();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          boxTotal = new feature.adjustment.component.BoxTotal();
          objHeader = new feature.adjustment.component.HeaderFormCreate();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          labelPopUpTitle4.setLabelTitle("Adjustment Information");

          objTransactionDate.setLabelName("Transaction Date");

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          objReason.setLabelName("Reason");

          objTransaction.setLabelName("Transaction");

          objReference.setLabelName("Reference");

          objComment.setLabelName("Comment");

          jScrollPane.setBorder(null);

          panelData.setPreferredSize(new java.awt.Dimension(1418, 509));

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1418, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 509, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          objHeader.setPreferredSize(new java.awt.Dimension(1418, 35));

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addGroup(layout.createSequentialGroup()
                                        .addComponent(objTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                   .addComponent(objTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objComment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(objReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(objReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                         .addComponent(boxTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(jScrollPane)
                         .addComponent(objHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(labelPopUpTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(10, 10, 10)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(objTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(objReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(10, 10, 10)
                              .addComponent(objTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(objReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(objComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(objHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(boxTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
          AdjustmentForm adjustmentForm = new AdjustmentForm(new JFrame(), true);
          adjustmentForm.setVisible(true);
     }//GEN-LAST:event_btnCancelMouseClicked

     public static void main(String args[]) {
        
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    AdjustmentDetail dialog = new AdjustmentDetail(new javax.swing.JFrame(), true);
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
     private javax.swing.JScrollPane jScrollPane;
     private Components.LabelPopUpTitle labelPopUpTitle4;
     private feature.main_detail.MainTitleDetail objComment;
     private feature.adjustment.component.HeaderFormCreate objHeader;
     private feature.main_detail.MainTitleDetail objReason;
     private feature.main_detail.MainTitleDetail objReference;
     private feature.main_detail.MainTitleDetail objTransaction;
     private feature.main_detail.MainTitleDetail objTransactionDate;
     private javax.swing.JPanel panelData;
     // End of variables declaration//GEN-END:variables
}
