package feature.adjustment.component;

import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.adjustment.AdjustmentCreateForm;
import java.awt.Component;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoxTotal extends javax.swing.JPanel {

     private AdjustmentCreateForm adjustmentCreateForm;

     public BoxTotal() {
          initComponents();

          custom();
     }

     private void custom() {
          lbTotalQty.setFont(WindowFonts.timeNewRomanBold14);
          lbTotalAmount.setFont(WindowFonts.timeNewRomanBold14);
          txtTotalQty.setFont(WindowFonts.timeNewRomanBold14);
          txtTotalAmount.setFont(WindowFonts.timeNewRomanBold14);
     }

     public void setAdjustmentCreateForm(AdjustmentCreateForm adjustmentCreateForm) {
          this.adjustmentCreateForm = adjustmentCreateForm;
          
          calculate();
     }

     public void calculate() {
          Integer totalQty = 0;
          Double totalAmount = 0.00;

          for (Component com : adjustmentCreateForm.getPanelData().getComponents()) {
               if (com instanceof ItemFormCreate) {
                    var obj = ((ItemFormCreate) com);
                    Integer adjustQty = Integer.valueOf(obj.getObjAdjustQty().getValueTextField());
                    Double cost = JavaConstant.getReplace(obj.getLbCost().getText());
                    Double amount = adjustQty * cost;

                    totalAmount += amount;
                    totalQty += adjustQty;
               }
          }

          txtTotalQty.setText(String.valueOf(totalQty));
          txtTotalAmount.setText(JavaConstant.setAmount(BigDecimal.valueOf(totalAmount)));
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbTotalQty = new javax.swing.JLabel();
          txtTotalQty = new javax.swing.JTextField();
          lbTotalAmount = new javax.swing.JLabel();
          txtTotalAmount = new javax.swing.JTextField();

          lbTotalQty.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTotalQty.setText("Total QTY :");

          txtTotalQty.setEditable(false);
          txtTotalQty.setText("0");

          lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTotalAmount.setText("Total Amount : ");

          txtTotalAmount.setEditable(false);
          txtTotalAmount.setText("0");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbTotalQty)
                         .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbTotalAmount)
                         .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel lbTotalAmount;
     private javax.swing.JLabel lbTotalQty;
     private javax.swing.JTextField txtTotalAmount;
     private javax.swing.JTextField txtTotalQty;
     // End of variables declaration//GEN-END:variables
}
