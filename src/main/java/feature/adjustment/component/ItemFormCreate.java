package feature.adjustment.component;

import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.adjustment.AdjustmentCreateForm;
import feature.adjustment.adjustment_controller.CreateAdjustmentController;
import feature.adjustment.model.ProductAdjustment;
import java.awt.Dimension;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemFormCreate extends javax.swing.JPanel {

     private ProductAdjustment productData;
     private Integer number;
     private AdjustmentCreateForm form;
     private CreateAdjustmentController controller;

     public ItemFormCreate(ProductAdjustment productData, Integer number) {
          this.productData = productData;
          this.number = number;
          initComponents();

          objAdjustQty.setOnlyDigit();

          JavaConstant.setPointer(btnDelete);

          setData();

          eventAdjustQty();
 
     }
     
     public void setDisable(){
          objAdjustQty.setDisable();
     }

     public void hideDelete(){
          btnDelete.setVisible(false);
     }
     
     public void eventAdjustQty() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    for (ProductAdjustment data : controller.getListTmp()) {
                         if (data.getBarcode().equals(lbBarcode.getText())) {
                              Integer adjustQty = Integer.valueOf(objAdjustQty.getValueTextField().replace(",", ""));
                              data.setAdjustQty(adjustQty);
                              break;
                         }
                    }
                    form.getBoxTotal().calculate();
               }
          };

          objAdjustQty.initEvent(event);
     }

     private void setData() {
          lbNumber.setText(String.valueOf(number));
          lbItemCode.setText(productData.getItemCode());
          lbBarcode.setText(productData.getBarcode());
          lbDesEnglish.setText(productData.getProNameEn());
          lbKhDesc.setText(productData.getProNameEn());
          lbUom.setText(productData.getUom());
          lbOnHandQty.setText(String.valueOf(productData.getOnHandQty()));
          objAdjustQty.setText(String.valueOf(productData.getAdjustQty()));
          lbCost.setText(JavaConstant.setAmount(productData.getCost()));
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbNumber = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();
          lbItemCode = new javax.swing.JLabel();
          lbOnHandQty = new javax.swing.JLabel();
          lbCost = new javax.swing.JLabel();
          lbBarcode = new javax.swing.JLabel();
          lbDesEnglish = new javax.swing.JLabel();
          lbKhDesc = new javax.swing.JLabel();
          lbUom = new javax.swing.JLabel();
          objAdjustQty = new FormComponent.JavaTextField();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1487, 50));

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("No.");

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
          btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnDeleteMouseClicked(evt);
               }
          });

          lbItemCode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbItemCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbItemCode.setText("Item Code");

          lbOnHandQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOnHandQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbOnHandQty.setText("On Hand Qty");

          lbCost.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCost.setText("Cost");

          lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbBarcode.setText("\tBarcode");

          lbDesEnglish.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDesEnglish.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDesEnglish.setText("English Description");

          lbKhDesc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbKhDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbKhDesc.setText("\tKhmer Description");

          lbUom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbUom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbUom.setText("UOM");

          objAdjustQty.setBackground(new java.awt.Color(255, 255, 255));
          objAdjustQty.setLabelName("");
          objAdjustQty.setPlaceHolder("");
          objAdjustQty.setPreferredSize(new java.awt.Dimension(300, 50));

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDesEnglish, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbKhDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(lbUom, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbOnHandQty, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(objAdjustQty, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(objAdjustQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(lbItemCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                         .addComponent(lbBarcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbDesEnglish, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbKhDesc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbUom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbOnHandQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
               .addComponent(lbCost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents

     private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        
        
          controller.getListTmp().
               removeIf(item -> item.getBarcode().equals(lbBarcode.getText()));
          
          
          controller.appendData(null);
          
     }//GEN-LAST:event_btnDeleteMouseClicked


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbBarcode;
     private javax.swing.JLabel lbCost;
     private javax.swing.JLabel lbDesEnglish;
     private javax.swing.JLabel lbItemCode;
     private javax.swing.JLabel lbKhDesc;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbOnHandQty;
     private javax.swing.JLabel lbUom;
     private FormComponent.JavaTextField objAdjustQty;
     // End of variables declaration//GEN-END:variables
}
