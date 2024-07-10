/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Stock.PurchaseOrder;

import javax.swing.Icon;

/**
 *
 * @author MOBILE-APP.02
 */
public class TdDetailPurchaseOrder extends javax.swing.JPanel {
     
     private String id;

     public TdDetailPurchaseOrder() {
          initComponents();
     }
     
     public void setDetail(
          String _number,
          String _barcode,
          String _proName,
          String _division,
          String _availableQty,
          String _qty,
          String _cost,
          String _amount,
          String _id
     ) {
          number.setText(_number);
          barcode.setText(_barcode);
          productName.setText(_proName);
          division.setText(_division);
          availableQty.setText(_availableQty);
          qty.setText(_qty);
          cost.setText(_cost);
          amount.setText(_amount);
          id = _id;
     }
     
     public void setImage(Icon image) {
          btnDelete.setText("");
          btnDelete.setIcon(image);
     }
     
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          getProduct = new javax.swing.JPanel();
          cost = new javax.swing.JLabel();
          number = new javax.swing.JLabel();
          barcode = new javax.swing.JLabel();
          productName = new javax.swing.JLabel();
          division = new javax.swing.JLabel();
          availableQty = new javax.swing.JLabel();
          amount = new javax.swing.JLabel();
          qty = new javax.swing.JTextField();
          btnDelete = new javax.swing.JLabel();

          getProduct.setBackground(new java.awt.Color(255, 255, 255));
          getProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          cost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          cost.setForeground(new java.awt.Color(0, 0, 0));
          cost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          cost.setText("Cost");

          number.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          number.setForeground(new java.awt.Color(0, 0, 0));
          number.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          number.setText("Number");

          barcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          barcode.setForeground(new java.awt.Color(0, 0, 0));
          barcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          barcode.setText("Barcode");

          productName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          productName.setForeground(new java.awt.Color(0, 0, 0));
          productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          productName.setText("Product Name");

          division.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          division.setForeground(new java.awt.Color(0, 0, 0));
          division.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          division.setText("Division");

          availableQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          availableQty.setForeground(new java.awt.Color(0, 0, 0));
          availableQty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          availableQty.setText("Available Qty");

          amount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          amount.setForeground(new java.awt.Color(0, 0, 0));
          amount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          amount.setText("Amount");

          qty.setText("Qty");

          btnDelete.setText("ddd");

          javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
          getProduct.setLayout(getProductLayout);
          getProductLayout.setHorizontalGroup(
               getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getProductLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnDelete)
                    .addGap(38, 38, 38)
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(availableQty)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(70, 70, 70))
          );
          getProductLayout.setVerticalGroup(
               getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(getProductLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(qty, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                              .addComponent(cost)
                              .addComponent(amount))
                         .addGroup(getProductLayout.createSequentialGroup()
                              .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(number)
                                        .addComponent(barcode)
                                        .addComponent(productName)
                                        .addComponent(division)
                                        .addComponent(availableQty))
                                   .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(getProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(getProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel amount;
     private javax.swing.JLabel availableQty;
     private javax.swing.JLabel barcode;
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel cost;
     private javax.swing.JLabel division;
     private javax.swing.JPanel getProduct;
     private javax.swing.JLabel number;
     private javax.swing.JLabel productName;
     private javax.swing.JTextField qty;
     // End of variables declaration//GEN-END:variables
}
