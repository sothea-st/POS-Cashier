package Reporting.ReportingItem;

public class ReportOfPurchase extends javax.swing.JPanel {

    public ReportOfPurchase() {
        initComponents();
    }

    
    public void setData(
         String number,
         String purchaseNo,
         String transactionNo,
         String transactionDate,
         String orderDate,
         String referenceNo,
         String vendorName,
         String totalQty,
         String totalCost
    ){
         lbNumber.setText(number);
         lbPurchaseNo.setText(purchaseNo);
         lbTransactionNo.setText(transactionNo);
         lbTransactionDate.setText(transactionDate);
         lbOrderDate.setText(orderDate);
         lbReferenceNo.setText(referenceNo);
         lbVendorName.setText(vendorName);
         lbTotalQty.setText(totalQty);
         lbTotalCost.setText(totalCost);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        lbTransactionNo = new javax.swing.JLabel();
        lbTransactionDate = new javax.swing.JLabel();
        lbPurchaseNo = new javax.swing.JLabel();
        lbOrderDate = new javax.swing.JLabel();
        lbReferenceNo = new javax.swing.JLabel();
        lbTotalQty = new javax.swing.JLabel();
        lbTotalCost = new javax.swing.JLabel();
        lbVendorName = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbNumber.setBackground(new java.awt.Color(0, 0, 0));
        lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbNumber.setForeground(new java.awt.Color(0, 0, 0));
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        lbTransactionNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTransactionNo.setForeground(new java.awt.Color(0, 0, 0));
        lbTransactionNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTransactionNo.setText("Transaction №");

        lbTransactionDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTransactionDate.setForeground(new java.awt.Color(0, 0, 0));
        lbTransactionDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTransactionDate.setText("Trasaction Date");

        lbPurchaseNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbPurchaseNo.setForeground(new java.awt.Color(0, 0, 0));
        lbPurchaseNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPurchaseNo.setText("Purchase Order №");

        lbOrderDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbOrderDate.setForeground(new java.awt.Color(0, 0, 0));
        lbOrderDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOrderDate.setText("Order Date");

        lbReferenceNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbReferenceNo.setForeground(new java.awt.Color(0, 0, 0));
        lbReferenceNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReferenceNo.setText("Reference №");

        lbTotalQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalQty.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalQty.setText("Total Qty");

        lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalCost.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalCost.setText("Total Cost");

        lbVendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbVendorName.setForeground(new java.awt.Color(0, 0, 0));
        lbVendorName.setText("Vendor Name");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPurchaseNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lbTransactionNo)
                    .addComponent(lbTransactionDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbOrderDate)
                    .addComponent(lbPurchaseNo)
                    .addComponent(lbReferenceNo)
                    .addComponent(lbTotalQty)
                    .addComponent(lbTotalCost)
                    .addComponent(lbVendorName))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1402, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbOrderDate;
    private javax.swing.JLabel lbPurchaseNo;
    private javax.swing.JLabel lbReferenceNo;
    private javax.swing.JLabel lbTotalCost;
    private javax.swing.JLabel lbTotalQty;
    private javax.swing.JLabel lbTransactionDate;
    private javax.swing.JLabel lbTransactionNo;
    private javax.swing.JLabel lbVendorName;
    // End of variables declaration//GEN-END:variables
}
