package Stock.PurchaseReceive;

import Constant.JavaConstant;
import Event.ButtonEvent;
import feature.user_permission.JavaPermission;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class GetPurchaseReceive extends javax.swing.JPanel {

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

     public GetPurchaseReceive() {
          initComponents();
          lbId.setVisible(false);

          JavaConstant.setPointer(btnDetail);
          JavaConstant.setPointer(btnEdit);

          // check permission
          // permissonId: 10 is primary key id from table pos_permission
          btnDetail.setVisible(JavaPermission.getPermissionDetail(10).getIsView());
          btnEdit.setVisible(JavaPermission.getPermissionDetail(10).getIsUpdate());

     }

     public String getRemark() {
          return remark;
     }

     public void setRemark(String remark) {
          this.remark = remark;
          lbStatus.setText(remark);
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
          btnEdit.setIcon(iconEdit);
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
          btnEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onSelect("" + id);
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
        btnEdit = new javax.swing.JLabel();
        lbTranactionNo = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

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

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

        lbTranactionNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTranactionNo.setForeground(new java.awt.Color(0, 0, 0));
        lbTranactionNo.setText("Transaction №");

        lbStatus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 0, 0));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStatus.setText("Status");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTranactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTotalCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTotalQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTransactionDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbReferenceNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTranactionNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbId)
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(lbVendorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbReferenceNo;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTotalCost;
    private javax.swing.JLabel lbTotalQty;
    private javax.swing.JLabel lbTranactionNo;
    private javax.swing.JLabel lbTransactionDate;
    private javax.swing.JLabel lbVendorName;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
