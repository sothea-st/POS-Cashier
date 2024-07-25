package Reporting.ReportingItem;

public class ReportOfCheck extends javax.swing.JPanel {

    public ReportOfCheck() {
        initComponents();
        lbId.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        lbReferenceNo = new javax.swing.JLabel();
        lbVendorName = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        btnDetail = new javax.swing.JLabel();
        lbTransactionDate = new javax.swing.JLabel();
        lbTotalQty = new javax.swing.JLabel();
        lbTotalCost = new javax.swing.JLabel();
        lbTranactionNo = new javax.swing.JLabel();
        lbremark = new javax.swing.JLabel();
        lbTransactionDate1 = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        lbReferenceNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbReferenceNo.setForeground(new java.awt.Color(0, 0, 0));
        lbReferenceNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReferenceNo.setText("Reference №");

        lbVendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbVendorName.setForeground(new java.awt.Color(0, 0, 0));
        lbVendorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbVendorName.setText("Vendor Name");

        lbId.setText("jLabel1");

        btnDetail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(0, 0, 0));
        btnDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDetail.setText("1");

        lbTransactionDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTransactionDate.setForeground(new java.awt.Color(0, 0, 0));
        lbTransactionDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTransactionDate.setText("Transaction Date");

        lbTotalQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalQty.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalQty.setText("Total Qty");

        lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalCost.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalCost.setText("Total Cost");

        lbTranactionNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTranactionNo.setForeground(new java.awt.Color(0, 0, 0));
        lbTranactionNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTranactionNo.setText("Transaction №");

        lbremark.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbremark.setForeground(new java.awt.Color(0, 0, 0));
        lbremark.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbremark.setText("remark");

        lbTransactionDate1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTransactionDate1.setForeground(new java.awt.Color(0, 0, 0));
        lbTransactionDate1.setText("Requested By");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTranactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbTransactionDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbremark, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbReferenceNo)
                    .addComponent(lbVendorName)
                    .addComponent(lbId)
                    .addComponent(lbTransactionDate)
                    .addComponent(lbTotalQty)
                    .addComponent(lbTotalCost)
                    .addComponent(lbTranactionNo)
                    .addComponent(lbremark)
                    .addComponent(lbTransactionDate1)
                    .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDetail;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbReferenceNo;
    private javax.swing.JLabel lbTotalCost;
    private javax.swing.JLabel lbTotalQty;
    private javax.swing.JLabel lbTranactionNo;
    private javax.swing.JLabel lbTransactionDate;
    private javax.swing.JLabel lbTransactionDate1;
    private javax.swing.JLabel lbVendorName;
    private javax.swing.JLabel lbremark;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
