package Reporting.ReportingItem;

public class ReportOfPurchaseOrder extends javax.swing.JPanel {

    public ReportOfPurchaseOrder() {
        initComponents();
        lbId.setVisible(false);
    }
    
    public void setData(
         String numberValue,
         String vendorNameValue,
         String transactionNoValue,
         String referenceNoValue,
         String transactionDateValue,
         String requestedByValue,
         String checkedByValue,
         String approvedByValue,
         String rejectedByValue,
         String totalQtyValue,
         String totalCostValue,
         String statusValue
         
    ){
         number.setText(numberValue);
         vendorName.setText(vendorNameValue);
         transactionNo.setText(transactionNoValue);
         referenceNo.setText(referenceNoValue);
         transactionDate.setText(transactionDateValue);
         requestedBy.setText(requestedByValue);
         checkedBy.setText(checkedByValue);
         approvedBy.setText(approvedByValue);
         rejectedBy.setText(rejectedByValue);
         totalQty.setText(totalQtyValue);
         totalCost.setText(totalCostValue);
         status.setText(statusValue);
    }

    @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          referenceNo = new javax.swing.JLabel();
          vendorName = new javax.swing.JLabel();
          lbId = new javax.swing.JLabel();
          number = new javax.swing.JLabel();
          transactionDate = new javax.swing.JLabel();
          totalQty = new javax.swing.JLabel();
          totalCost = new javax.swing.JLabel();
          transactionNo = new javax.swing.JLabel();
          status = new javax.swing.JLabel();
          requestedBy = new javax.swing.JLabel();
          checkedBy = new javax.swing.JLabel();
          approvedBy = new javax.swing.JLabel();
          rejectedBy = new javax.swing.JLabel();

          panel.setBackground(new java.awt.Color(255, 255, 255));
          panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          panel.setPreferredSize(new java.awt.Dimension(615, 35));

        referenceNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        referenceNo.setText("Reference №");

        vendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        vendorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        vendorName.setText("Vendor Name");

          lbId.setText("jLabel1");

        number.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("1");

        transactionDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        transactionDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        transactionDate.setText("Transaction Date");

        totalQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        totalQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalQty.setText("Total Qty");

        totalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        totalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalCost.setText("Total Cost");

        transactionNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        transactionNo.setText("Transaction №");

        status.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Status");

        requestedBy.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        requestedBy.setText("Requested By");

        checkedBy.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        checkedBy.setText("Checked By");

        approvedBy.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        approvedBy.setText("Approved By");

        rejectedBy.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        rejectedBy.setText("Rejected By");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(referenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(requestedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approvedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rejectedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbId)
                .addContainerGap(11, Short.MAX_VALUE))
            .addComponent(number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(transactionNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(referenceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(transactionDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(requestedBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(checkedBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(approvedBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rejectedBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(totalQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(totalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1831, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel approvedBy;
     private javax.swing.JLabel checkedBy;
     private javax.swing.JLabel lbId;
     private javax.swing.JLabel number;
     private javax.swing.JPanel panel;
     private javax.swing.JLabel referenceNo;
     private javax.swing.JLabel rejectedBy;
     private javax.swing.JLabel requestedBy;
     private javax.swing.JLabel status;
     private javax.swing.JLabel totalCost;
     private javax.swing.JLabel totalQty;
     private javax.swing.JLabel transactionDate;
     private javax.swing.JLabel transactionNo;
     private javax.swing.JLabel vendorName;
     // End of variables declaration//GEN-END:variables
}
