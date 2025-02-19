package feature.adjustment.component;

import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.adjustment.model.AdjustmentModel.AdjustmentDetail;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashMap;

public class AdjustmentItem extends javax.swing.JPanel {

     private AdjustmentDetail detail;
     private Integer number;

     public AdjustmentItem(AdjustmentDetail detail, Integer number) {
          this.detail = detail;
          this.number = number;
          initComponents();
          setPreferredSize(new Dimension(1530, 45));
          cmdStatus();

          JavaConstant.setPointer(btnEdit);
          JavaConstant.setPointer(btnInfo);
          JavaConstant.setPointer(btnDedit);

          if (detail.getApprovalUser() != null) {
               btnEdit.setIcon(null);
               btnEdit.setText("   ");
               btnEdit.setEnabled(false);

               btnDelete.setIcon(null);
               btnDelete.setText("   ");
               btnDelete.setEnabled(false);
          }

          setData();
     }

     private void setData() {
          lbNo.setText(String.valueOf(number));
          lbTransactionNo.setText(detail.getTransaction());
          lbTransactionDate.setText(JavaConstant.formateDateDDMMYYYY(detail.getTransactionDate()));
          lbPostDate.setText(detail.getPostDate() == null ? "" : JavaConstant.formateDateDDMMYYYY(detail.getPostDate()));
          lbReferenceName.setText(detail.getReferenceName());
          lbApprovalUser.setText(detail.getApprovalUser());
          lbReason.setText(detail.getReason());
          lbTotalQty.setText(String.valueOf(detail.getTotalQty()));
          lbTotalCost.setText(JavaConstant.setAmount(detail.getTotalCost()));

          if (detail.getApprovalUser() == null || detail.getApprovalUser().isEmpty()) {
               cmdStatus.setSelectedItem(detail.getStatus());
          } else {
               cmdStatus.setSelectedItem(detail.getStatus());
               cmdStatus.setDiable();
          }
     }

     private void cmdStatus() {
          LinkedHashMap<String, String> map = new LinkedHashMap<>();
          map.put("Draft", "Draft");
          map.put("Posted", "Posted");
          map.put("Cancelled", "Cancelled");
          cmdStatus.setMap(map);
     }

     public void initEvent(ButtonEvent event) {
          cmdStatus.initEvent(event);
          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {

                    if (btnDelete.getIcon() == null) {
                         return;
                    }

                    event.onDelete();
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

          btnInfo.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onInfo();
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

          btnEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {

                    if (btnEdit.getIcon() == null) {
                         return;
                    }
                    event.onEdit();
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

          btnDedit = new javax.swing.JPanel();
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
          btnInfo = new javax.swing.JLabel();
          lbNo = new javax.swing.JLabel();
          cmdStatus = new FormComponent.combobox.JavaCombobox();

          btnDedit.setBackground(new java.awt.Color(255, 255, 255));
          btnDedit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          btnDedit.setPreferredSize(new java.awt.Dimension(1604, 40));

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

          btnInfo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

          lbNo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNo.setText("No");

          cmdStatus.setBackground(new java.awt.Color(255, 255, 255));
          cmdStatus.setLabelName("");
          cmdStatus.setName(""); // NOI18N

          javax.swing.GroupLayout btnDeditLayout = new javax.swing.GroupLayout(btnDedit);
          btnDedit.setLayout(btnDeditLayout);
          btnDeditLayout.setHorizontalGroup(
               btnDeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(btnDeditLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cmdStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );
          btnDeditLayout.setVerticalGroup(
               btnDeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTransactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPostDate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReferenceName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbApprovalUser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReason, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(cmdStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btnDedit, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(btnDedit, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel btnDedit;
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel btnEdit;
     private javax.swing.JLabel btnInfo;
     private FormComponent.combobox.JavaCombobox cmdStatus;
     private javax.swing.JLabel lbApprovalUser;
     private javax.swing.JLabel lbNo;
     private javax.swing.JLabel lbPostDate;
     private javax.swing.JLabel lbReason;
     private javax.swing.JLabel lbReferenceName;
     private javax.swing.JLabel lbTotalCost;
     private javax.swing.JLabel lbTotalQty;
     private javax.swing.JLabel lbTransactionDate;
     private javax.swing.JLabel lbTransactionNo;
     // End of variables declaration//GEN-END:variables

}
