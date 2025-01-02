 
package feature.report.report_sale_return;
 
public class SaleReturnData extends javax.swing.JPanel {

 
    public SaleReturnData() {
        initComponents();
    }

    
    public void setData(
         String number,
         String invoiceNo,
         String date,
         String productName,
         String qty,
         String price,
         String discount,
         String cost,
         String reason,
         String staff
    ){
         lbNumber.setText(number);
         lbInvoice.setText(invoiceNo);
         lbDate.setText(date);
         lbProductName.setText(productName);
         lbQty.setText(qty);
         lbPrice.setText(price);
         lbDiscount.setText(discount);
         lbCost.setText(cost);
         lbReason.setText(reason);
         lbStaff.setText(staff);
    }
    
    @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbNumber = new javax.swing.JLabel();
          lbProductName = new javax.swing.JLabel();
          lbPrice = new javax.swing.JLabel();
          lbCost = new javax.swing.JLabel();
          lbDate = new javax.swing.JLabel();
          lbQty = new javax.swing.JLabel();
          lbStaff = new javax.swing.JLabel();
          lbInvoice = new javax.swing.JLabel();
          lbReason = new javax.swing.JLabel();
          lbDiscount = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("#");

          lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbProductName.setText("Product Name");

          lbPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPrice.setText("Price");

          lbCost.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCost.setText("Cost ");

          lbDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDate.setText("Date");

          lbQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbQty.setText("Qty");

          lbStaff.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbStaff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbStaff.setText("Staff");

          lbInvoice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbInvoice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbInvoice.setText("Invoice №");

          lbReason.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbReason.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbReason.setText("Reason");

          lbDiscount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDiscount.setText("Discount");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(111, 111, 111)
                    .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(732, 732, 732))
               .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                         .addGap(766, 766, 766)
                         .addComponent(lbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addContainerGap(1032, Short.MAX_VALUE)))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addComponent(lbPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbInvoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbCost;
     private javax.swing.JLabel lbDate;
     private javax.swing.JLabel lbDiscount;
     private javax.swing.JLabel lbInvoice;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbPrice;
     private javax.swing.JLabel lbProductName;
     private javax.swing.JLabel lbQty;
     private javax.swing.JLabel lbReason;
     private javax.swing.JLabel lbStaff;
     // End of variables declaration//GEN-END:variables
}
