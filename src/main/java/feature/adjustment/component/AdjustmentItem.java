package feature.adjustment.component;

public class AdjustmentItem extends javax.swing.JPanel {

     public AdjustmentItem() {
          initComponents();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          lbApprovalUser = new javax.swing.JLabel();
          lbTotalQty = new javax.swing.JLabel();
          btnEdit = new javax.swing.JLabel();
          lbTotalCost = new javax.swing.JLabel();
          lbTransactionNo = new javax.swing.JLabel();
          lbTransactionDate = new javax.swing.JLabel();
          lbPostDate = new javax.swing.JLabel();
          lbReferenceName = new javax.swing.JLabel();
          lbReason = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();
          btnDetail = new javax.swing.JLabel();
          lbNo = new javax.swing.JLabel();
          cmbBrand = new FormComponent.combobox.JavaCombobox();

          panel.setBackground(new java.awt.Color(255, 255, 255));
          panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          panel.setPreferredSize(new java.awt.Dimension(1604, 40));

          lbApprovalUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbApprovalUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbApprovalUser.setText("ADJ-20250214");

          lbTotalQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbTotalQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTotalQty.setText("30");

          btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

          lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTotalCost.setText("$44.7");

          lbTransactionNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbTransactionNo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTransactionNo.setText("ADJ101-250212001");

          lbTransactionDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbTransactionDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
          lbTransactionDate.setText("12-Feb-2025\t");

          lbPostDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbPostDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPostDate.setText("14-Feb-2025");

          lbReferenceName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbReferenceName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbReferenceName.setText("ADJ-20250214");

          lbReason.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbReason.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbReason.setText("Physical Count Out");

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

          btnDetail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

          lbNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNo.setText("No");

          cmbBrand.setBackground(new java.awt.Color(255, 255, 255));
          cmbBrand.setLabelName("");
          cmbBrand.setName(""); // NOI18N

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17)
                    .addComponent(lbTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)
                    .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addComponent(lbPostDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbReferenceName, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(lbApprovalUser, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbReason, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTotalQty, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addGap(25, 25, 25)
                    .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTransactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPostDate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReferenceName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbApprovalUser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(cmbBrand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel btnDetail;
     private javax.swing.JLabel btnEdit;
     private FormComponent.combobox.JavaCombobox cmbBrand;
     private javax.swing.JLabel lbApprovalUser;
     private javax.swing.JLabel lbNo;
     private javax.swing.JLabel lbPostDate;
     private javax.swing.JLabel lbReason;
     private javax.swing.JLabel lbReferenceName;
     private javax.swing.JLabel lbTotalCost;
     private javax.swing.JLabel lbTotalQty;
     private javax.swing.JLabel lbTransactionDate;
     private javax.swing.JLabel lbTransactionNo;
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables

}
