/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Stock.PurchaseOrder;

import BlogCode.JavaBlogImage;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author MOBILE-APP.02
 */
public class TdDetailPurchaseOrder extends javax.swing.JPanel {

     private String id;
     private Icon image;
     private String index;

     private String amountValue;

     public TdDetailPurchaseOrder() {
          initComponents();
          JavaConstant.setPointer(btnDelete);
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
          index = _number;
     }

     public String getAmountValue() {
          return amount.getText();
     }

     public void setAmountValue(String amountValue) {
          this.amountValue = amountValue;
     }
     
     public String getQtyUnit(){
          return qty.getText();
     }
     
     public void setIndex(String i){
          number.setText(i);
     }

 

     
     public Icon getImage() {
          return image;
     }

     public void setImage(Icon image) {
          this.image = image;
          btnDelete.setIcon(image);
     }

     public void initEvent(ButtonEvent event) {
          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onRemove(index);
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

          qty.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
//                    JavaConstant.onlyDigits(qtyValue);
               }

               @Override
               public void keyPressed(KeyEvent e) {
//                    JavaConstant.onlyDigits(qtyValue);
               }

               @Override
               public void keyReleased(KeyEvent e) {
                    String _cost = cost.getText();
                    _cost = _cost.replace("$", "");
                    _cost = _cost.replace(",", "");

                    double qtyValue = Double.parseDouble(qty.getText());
                    double costValue = Double.parseDouble(_cost);
                    double result = qtyValue * costValue;

                    String _text = "$".concat(String.format("%.2f", result));
                    amount.setText(_text);
                    event.onKeyPress();
               }

          });
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

          cost.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          cost.setForeground(new java.awt.Color(0, 0, 0));
          cost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          cost.setText("Cost");

          number.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          number.setForeground(new java.awt.Color(0, 0, 0));
          number.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          number.setText("Number");

          barcode.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          barcode.setForeground(new java.awt.Color(0, 0, 0));
          barcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          barcode.setText("Barcode");

          productName.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          productName.setForeground(new java.awt.Color(0, 0, 0));
          productName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          productName.setText("Product Name");

          division.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          division.setForeground(new java.awt.Color(0, 0, 0));
          division.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          division.setText("Division");

          availableQty.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          availableQty.setForeground(new java.awt.Color(0, 0, 0));
          availableQty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          availableQty.setText("Available Qty");

          amount.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
          amount.setForeground(new java.awt.Color(0, 0, 0));
          amount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          amount.setText("Amount");

          qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
          qty.setText("Qty");

          javax.swing.GroupLayout getProductLayout = new javax.swing.GroupLayout(getProduct);
          getProduct.setLayout(getProductLayout);
          getProductLayout.setHorizontalGroup(
               getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getProductLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(btnDelete)
                    .addGap(46, 46, 46)
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(availableQty)
                    .addGap(64, 64, 64)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(58, 58, 58)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24))
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
                              .addGroup(getProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                   .addComponent(number)
                                   .addComponent(barcode)
                                   .addComponent(productName)
                                   .addComponent(division)
                                   .addComponent(availableQty)
                                   .addComponent(btnDelete))
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
