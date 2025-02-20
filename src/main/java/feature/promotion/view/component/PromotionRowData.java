package feature.promotion.view.component;

import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.promotion.model.PromotionModel.PromotionDetail;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PromotionRowData extends javax.swing.JPanel {

     private PromotionDetail promotionDetail;

     public PromotionRowData(PromotionDetail promotionDetail) {
          this.promotionDetail = promotionDetail;

          initComponents();

          setPreferredSize(new Dimension(1360, 45));

          custom();
          
          setData();
     }

     private void custom() {
          JavaConstant.setPointer(btnEdit);
          JavaConstant.setPointer(btnInfo);
          JavaConstant.setPointer(btnDelete);

//          if (detail.getApprovalUser() != null) {
//               btnEdit.setIcon(null);
//               btnEdit.setText("   ");
//               btnEdit.setEnabled(false);
//
//               btnDelete.setIcon(null);
//               btnDelete.setText("   ");
//               btnDelete.setEnabled(false);
//          }
     }

     
     public void initEvent(ButtonEvent event) {
          btnDelete.addMouseListener(new MouseListener(){
               @Override
               public void mouseClicked(MouseEvent e) {
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
     }
     
     private void setData() {

          lbCreatedData.setText(JavaConstant.formateDateDDMMYYYY(promotionDetail.getCreatedDate()));
          lbCreatedBy.setText(promotionDetail.getCreatedBy());
          lbPromotionType.setText(promotionDetail.getPromotionType());
          lbStartDate.setText(JavaConstant.formateDateDDMMYYYY(promotionDetail.getStartDate()));
          lbEndDate.setText(JavaConstant.formateDateDDMMYYYY(promotionDetail.getEndDate()));
          lbPercentage.setText(String.valueOf(promotionDetail.getPercentage()));
          lbSalePrice.setText(JavaConstant.setAmount(promotionDetail.getSalePrice()));
          lbAfterDiscount.setText(JavaConstant.setAmount(promotionDetail.getAfterDiscount()));
          switchStatus.setSelected(promotionDetail.getIsStatus());
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          lbCreatedData = new javax.swing.JLabel();
          lbCreatedBy = new javax.swing.JLabel();
          lbStartDate = new javax.swing.JLabel();
          lbPromotionType = new javax.swing.JLabel();
          lbPercentage = new javax.swing.JLabel();
          lbAfterDiscount = new javax.swing.JLabel();
          lbEndDate = new javax.swing.JLabel();
          lbSalePrice = new javax.swing.JLabel();
          switchStatus = new SwitchButton.SwitchButton();
          btnEdit = new javax.swing.JLabel();
          btnInfo = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();

          panel.setBackground(new java.awt.Color(255, 255, 255));
          panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          panel.setPreferredSize(new java.awt.Dimension(400, 35));

          lbCreatedData.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCreatedData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCreatedData.setText("Created Date");

          lbCreatedBy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCreatedBy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCreatedBy.setText("Create By");

          lbStartDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbStartDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbStartDate.setText("Start Date");

          lbPromotionType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPromotionType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPromotionType.setText("Promotion Type");

          lbPercentage.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPercentage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPercentage.setText("Percentage");

          lbAfterDiscount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbAfterDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbAfterDiscount.setText("After Discount");

          lbEndDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbEndDate.setText("End Date");

          lbSalePrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSalePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbSalePrice.setText("Sale Price");

          switchStatus.setBackground(new java.awt.Color(204, 204, 204));

          btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

          btnInfo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lbCreatedData, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCreatedBy, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPromotionType, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addGap(12, 12, 12)
                    .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbAfterDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addGap(37, 37, 37)
                    .addComponent(switchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelLayout.createSequentialGroup()
                              .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(panelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(switchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCreatedData, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbCreatedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPromotionType, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbAfterDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(0, 0, Short.MAX_VALUE))
                         .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(btnInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel btnEdit;
     private javax.swing.JLabel btnInfo;
     private javax.swing.JLabel lbAfterDiscount;
     private javax.swing.JLabel lbCreatedBy;
     private javax.swing.JLabel lbCreatedData;
     private javax.swing.JLabel lbEndDate;
     private javax.swing.JLabel lbPercentage;
     private javax.swing.JLabel lbPromotionType;
     private javax.swing.JLabel lbSalePrice;
     private javax.swing.JLabel lbStartDate;
     private javax.swing.JPanel panel;
     private SwitchButton.SwitchButton switchStatus;
     // End of variables declaration//GEN-END:variables
}
