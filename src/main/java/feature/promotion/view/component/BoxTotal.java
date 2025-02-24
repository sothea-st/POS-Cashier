package feature.promotion.view.component;

import Components.Fonts.WindowFonts;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BoxTotal extends javax.swing.JPanel {

     public BoxTotal() {
          initComponents();
          
          custom();
     }

     private void custom() {
          lbTotalQty.setFont(WindowFonts.timeNewRomanBold14);
          lbTotalAmount.setFont(WindowFonts.timeNewRomanBold14);
          txtTotalSalePrice.setFont(WindowFonts.timeNewRomanBold14);
          txtTotalAfterDiscount.setFont(WindowFonts.timeNewRomanBold14);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jPanel1 = new javax.swing.JPanel();
          lbTotalQty = new javax.swing.JLabel();
          txtTotalSalePrice = new javax.swing.JTextField();
          lbTotalAmount = new javax.swing.JLabel();
          txtTotalAfterDiscount = new javax.swing.JTextField();

          lbTotalQty.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTotalQty.setText("Total Sale Price :");

          txtTotalSalePrice.setEditable(false);
          txtTotalSalePrice.setText("0");

          lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTotalAmount.setText("Total After Discount :");

          txtTotalAfterDiscount.setEditable(false);
          txtTotalAfterDiscount.setText("0");

          javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                         .addComponent(lbTotalQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(txtTotalSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(txtTotalAfterDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbTotalQty)
                         .addComponent(txtTotalSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbTotalAmount)
                         .addComponent(txtTotalAfterDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel jPanel1;
     private javax.swing.JLabel lbTotalAmount;
     private javax.swing.JLabel lbTotalQty;
     private javax.swing.JTextField txtTotalAfterDiscount;
     private javax.swing.JTextField txtTotalSalePrice;
     // End of variables declaration//GEN-END:variables
}
