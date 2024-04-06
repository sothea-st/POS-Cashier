package Components;
 
import javax.swing.JLabel;

public class ReceiptBox extends javax.swing.JPanel {
     
     
     private String productName;
     private String qtyStr;
     private String barcodeStr;
     private String unitPriceStr;
     private String amountStr;
 
    public ReceiptBox() {
        initComponents();
        Receipt.Receipt.setFontSizeForLabels(this, 11);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbProduct = new javax.swing.JLabel();
        lbBarcode = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        unitPriceLb = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbProduct.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        lbProduct.setForeground(new java.awt.Color(56, 56, 56));
        lbProduct.setText("9556439882270");
        lbProduct.setToolTipText("");

        lbBarcode.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        lbBarcode.setForeground(new java.awt.Color(56, 56, 56));
        lbBarcode.setText("Label");

        lbQty.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        lbQty.setForeground(new java.awt.Color(56, 56, 56));
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("1");

        unitPriceLb.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        unitPriceLb.setForeground(new java.awt.Color(56, 56, 56));
        unitPriceLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unitPriceLb.setText("$ 1.85");

        amount.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        amount.setForeground(new java.awt.Color(56, 56, 56));
        amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amount.setText("$ 1.85");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(unitPriceLb, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbQty)
                    .addComponent(unitPriceLb)
                    .addComponent(amount)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
     
     public void setQtyStr(String qtyStr) {
          this.qtyStr = qtyStr;
          lbQty.setText(qtyStr);
     }

     public void setBarcodeStr(String barcodeStr) {
          this.barcodeStr = barcodeStr;
          lbBarcode.setText(barcodeStr);
     }

     public void setUnitPriceStr(String unitPriceStr) {
          this.unitPriceStr = unitPriceStr;
          unitPriceLb.setText(unitPriceStr);
     }

     public void setAmountStr(String amountStr) {
          this.amountStr = amountStr;
          amount.setText(amountStr);
     }

     public void setProductName(String productName) {
          this.productName = productName;
          lbProduct.setText("<html>"+productName+"</html>");
     }
     
     
     
 
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amount;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbProduct;
    private javax.swing.JLabel lbQty;
    private javax.swing.JLabel unitPriceLb;
    // End of variables declaration//GEN-END:variables
}
