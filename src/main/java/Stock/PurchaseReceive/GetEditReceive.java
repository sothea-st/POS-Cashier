package Stock.PurchaseReceive;

import javax.swing.JLabel;

public class GetEditReceive extends javax.swing.JPanel {

     private Integer productId;
     private Integer qtyUnit;
     private String cost;
     private String amount;
     private String orderQty;

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
          qty.setText(orderQty);
          qtyUnit = Integer.valueOf(orderQty);
         

          String _cost = cost.replace("$", "");
          _cost = _cost.replace(",", "");
          setCost(_cost);

          String _amount = amount.replace("$", "");
          _amount = _amount.replace(",", "");
          setAmount(_amount);
          setProductId(Integer.valueOf(_productId));
     }

     public String getOrderQty() {
          return orderQty;
     }

     public void setOrderQty(String orderQty) {
          this.orderQty = orderQty;
          lbOrderQty.setText(orderQty);
     }
     
     
     public JLabel getLbOrderQty() {
          return lbOrderQty;
     }

     public void setLbOrderQty(JLabel lbOrderQty) {
          this.lbOrderQty = lbOrderQty;
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
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBarcode.setText("Barcode");

        lbOrderQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbOrderQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOrderQty.setText("Ordered Qty");

        lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbProductName.setText("Product Name");

        lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCost.setText("Cost");

        lbAmount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAmount.setText("Amount");

        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbOrderQty, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbOrderQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbBarcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

          if (!_qty.isEmpty() || _qty != null) {
               setQtyUnit(Integer.valueOf(_qty));
          }

//          System.out.println("ddddddddddddddddddd = " + _qty);
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
