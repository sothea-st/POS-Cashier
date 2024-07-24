package Stock.PurchaseReceive;

public class GetEditReceive extends javax.swing.JPanel {

     private Integer productId;
     private Integer qtyUnit;
     private String cost;
     private String amount;

     public GetEditReceive() {
          initComponents();
     }

     public void setData(
          String _productId,
          String number,
          String barcode,
          String productName,
          String orderQty,
          String cost,
          String amount
     ) {
          lbNumber.setText(number);
          lbBarcode.setText(barcode);
          lbProductName.setText(productName);
          lbOrderQty.setText(orderQty);
          lbCost.setText(cost);
          lbAmount.setText(amount);
          qty.setText(null);

          String _cost = cost.replace("$", "");
          _cost = _cost.replace(",", "");
          setCost(_cost);

          String _amount = amount.replace("$", "");
          _amount = _amount.replace(",", "");
          setAmount(_amount);
          setProductId(Integer.valueOf(_productId));
     }

     public Integer getProductId() {
          return productId;
     }

     public void setProductId(Integer productId) {
          this.productId = productId;
     }

     public Integer getQtyUnit() {
          return qtyUnit;
     }

     public void setQtyUnit(Integer qtyUnit) {
          this.qtyUnit = qtyUnit;
     }

     public String getCost() {
          return cost;
     }

     public void setCost(String cost) {
          this.cost = cost;
     }

     public String getAmount() {
          return amount;
     }

     public void setAmount(String amount) {
          this.amount = amount;
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbNumber = new javax.swing.JLabel();
          lbBarcode = new javax.swing.JLabel();
          lbOrderQty = new javax.swing.JLabel();
          lbProductName = new javax.swing.JLabel();
          lbCost = new javax.swing.JLabel();
          lbAmount = new javax.swing.JLabel();
          qty = new javax.swing.JTextField();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbNumber.setForeground(new java.awt.Color(0, 0, 0));
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("#");

          lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbBarcode.setForeground(new java.awt.Color(0, 0, 0));
          lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbBarcode.setText("Barcode");

          lbOrderQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbOrderQty.setForeground(new java.awt.Color(0, 0, 0));
          lbOrderQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbOrderQty.setText("Ordered Qty");

          lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbProductName.setForeground(new java.awt.Color(0, 0, 0));
          lbProductName.setText("Product Name");

          lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbCost.setForeground(new java.awt.Color(0, 0, 0));
          lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCost.setText("Cost");

          lbAmount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbAmount.setForeground(new java.awt.Color(0, 0, 0));
          lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbAmount.setText("Amount");

          qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          qty.setText("Qty");
          qty.addKeyListener(new java.awt.event.KeyAdapter() {
               public void keyReleased(java.awt.event.KeyEvent evt) {
                    qtyKeyReleased(evt);
               }
          });

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbOrderQty, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbNumber)
                         .addComponent(lbBarcode)
                         .addComponent(lbOrderQty)
                         .addComponent(lbCost)
                         .addComponent(lbProductName)
                         .addComponent(lbAmount)
                         .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
     }// </editor-fold>//GEN-END:initComponents

     private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
          String _qty = qty.getText();
          setQtyUnit(Integer.valueOf(_qty));
          System.out.println("ddddddddddddddddddd = " + _qty);
     }//GEN-LAST:event_qtyKeyReleased


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbAmount;
     private javax.swing.JLabel lbBarcode;
     private javax.swing.JLabel lbCost;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbOrderQty;
     private javax.swing.JLabel lbProductName;
     private javax.swing.JTextField qty;
     // End of variables declaration//GEN-END:variables
}
