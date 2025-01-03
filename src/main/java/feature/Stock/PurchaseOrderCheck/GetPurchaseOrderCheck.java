package feature.Stock.PurchaseOrderCheck;

import Constant.JavaConstant;
import Components.Event.ButtonEvent;
import feature.user_permission.JavaPermission;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class GetPurchaseOrderCheck extends javax.swing.JPanel {

     private Integer id;
     private String vendorName;
     private String referenceNo;
     private String transactionNo;
     private String transactionDate;
     private String totalQty;
     private String totalCost;
     private String remark;
     private Icon iconEdit;
     private Icon iconDetail;
     private Icon iconDelete;

     public GetPurchaseOrderCheck(String typeForm) {
          initComponents();
          lbId.setVisible(false);
          JavaConstant.setPointer(btnDetail);

 

          // check permission
          // permissonId: 13,14 is primary key id from table pos_permission
          if (typeForm.equals("approved")) {
               btnDetail.setVisible(JavaPermission.getPermissionDetail(14).getIsView());
          } else if (typeForm.equals("checked")) {
               btnDetail.setVisible(JavaPermission.getPermissionDetail(13).getIsView());
          }

     }

     public String getRemark() {
          return remark;
     }

     public void setRemark(String remark) {
          this.remark = remark;
          lbremark.setText(remark);
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
          lbId.setText("" + id);
     }

     public String getVendorName() {
          return vendorName;
     }

     public void setVendorName(String vendorName) {
          this.vendorName = vendorName;
          lbVendorName.setText(vendorName);
     }

     public String getReferenceNo() {
          return referenceNo;
     }

     public void setReferenceNo(String referenceNo) {
          this.referenceNo = referenceNo;
          lbReferenceNo.setText(referenceNo);
     }

     public String getTransactionNo() {
          return transactionNo;
     }

     public void setTransactionNo(String transactionNo) {
          this.transactionNo = transactionNo;
          lbTranactionNo.setText(transactionNo);
     }

     public String getTransactionDate() {
          return transactionDate;
     }

     public void setTransactionDate(String transactionDate) {
          this.transactionDate = transactionDate;
          lbTransactionDate.setText(transactionDate);
     }

     public String getTotalQty() {
          return totalQty;
     }

     public void setTotalQty(String totalQty) {
          this.totalQty = totalQty;
          lbTotalQty.setText(totalQty);
     }

     public String getTotalCost() {
          return totalCost;
     }

     public void setTotalCost(String totalCost) {
          this.totalCost = totalCost;
          lbTotalCost.setText(totalCost);
     }

     public Icon getIconEdit() {
          return iconEdit;
     }

     public void setIconEdit(Icon iconEdit) {
          this.iconEdit = iconEdit;
//        btnEdit.setIcon(iconEdit);
     }

     public Icon getIconDetail() {
          return iconDetail;
     }

     public void setIconDetail(Icon iconDetail) {
          this.iconDetail = iconDetail;
          btnDetail.setIcon(iconDetail);
     }

     public Icon getIconDelete() {
          return iconDelete;
     }

     public void setIconDelete(Icon iconDelete) {
          this.iconDelete = iconDelete;
//        btnDelete.setIcon(iconDelete);
     }

     public void initEvent(ButtonEvent event) {

          btnDetail.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelectDetail("" + id);
               }

               @Override
               public void mousePressed(MouseEvent e) {
               }

               @Override
               public void mouseReleased(MouseEvent e) {
               }

               @Override
               public void mouseEntered(MouseEvent e) {
               }

               @Override
               public void mouseExited(MouseEvent e) {
               }
          });
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

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        lbReferenceNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbReferenceNo.setForeground(new java.awt.Color(0, 0, 0));
        lbReferenceNo.setText("Reference №");

        lbVendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbVendorName.setForeground(new java.awt.Color(0, 0, 0));
        lbVendorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbVendorName.setText("Vendor Name");

        lbId.setText("jLabel1");

        btnDetail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(0, 0, 0));
        btnDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

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

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTranactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbremark, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lbTransactionDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTotalQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbReferenceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTranactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbVendorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbremark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
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
    private javax.swing.JLabel lbVendorName;
    private javax.swing.JLabel lbremark;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
