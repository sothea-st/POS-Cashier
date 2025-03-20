package feature.order_online.component;

import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.order_online.model.OrderOnlineModelResponse.OrderOnlineData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderOnlineRowData extends javax.swing.JPanel {

     private OrderOnlineData data;

     public OrderOnlineRowData(OrderOnlineData data) {
          initComponents();

          this.data = data;

          setData();

          JavaConstant.setPointer(btnInfo);
     }

     private void setData() {
          lbOrderDate.setText(JavaConstant.formateDateDDMMYYYY(data.getOrderDate()));
          lbOrderNumber.setText(data.getOrderNumber());
          lbOrderStatus.setText("    " + data.getOrderStatus());
          lbCusomerID.setText(data.getCustomerId());
          lbCustomerName.setText(" " + data.getCustomerName());

          lbPhoneNumber.setText("   " + JavaConstant.nullValueOrEmpty(data.getPhoneNumber()));

          lbDeliveryInfomation.setText("  " + data.getDeliveryInformation());
          lbTotalAmount.setText("  " + JavaConstant.setAmount(data.getTotalAmount()));
          lbPaymentMethod.setText("  " + data.getPaymentMethod());
          lbPaymentStatus.setText("  " + data.getPaymentStatus());
          lbDeliveryAddress.setText(data.getDeliveryAddress());
          lbCustomerNote.setText(data.getCustomerNote());
     }

     public void initEvent(ButtonEvent event) {
          btnInfo.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onClick();
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

          header = new javax.swing.JPanel();
          lbTotalAmount = new javax.swing.JLabel();
          btnInfo = new javax.swing.JLabel();
          lbPaymentMethod = new javax.swing.JLabel();
          lbCustomerName = new javax.swing.JLabel();
          lbDeliveryInfomation = new javax.swing.JLabel();
          lbOrderDate = new javax.swing.JLabel();
          lbOrderNumber = new javax.swing.JLabel();
          lbOrderStatus = new javax.swing.JLabel();
          lbCusomerID = new javax.swing.JLabel();
          lbPhoneNumber = new javax.swing.JLabel();
          lbPaymentStatus = new javax.swing.JLabel();
          lbDeliveryAddress = new javax.swing.JLabel();
          lbCustomerNote = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1487, 45));

          lbTotalAmount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTotalAmount.setForeground(new java.awt.Color(0, 0, 0));
          lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbTotalAmount.setText("Total Amount");

          btnInfo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          btnInfo.setForeground(new java.awt.Color(0, 0, 0));
          btnInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

          lbPaymentMethod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPaymentMethod.setForeground(new java.awt.Color(0, 0, 0));
          lbPaymentMethod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPaymentMethod.setText("Payment Method");

          lbCustomerName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCustomerName.setForeground(new java.awt.Color(0, 0, 0));
          lbCustomerName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCustomerName.setText("Customer Name");

          lbDeliveryInfomation.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDeliveryInfomation.setForeground(new java.awt.Color(0, 0, 0));
          lbDeliveryInfomation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbDeliveryInfomation.setText("Delivery Information");

          lbOrderDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOrderDate.setForeground(new java.awt.Color(0, 0, 0));
          lbOrderDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbOrderDate.setText("Order Date");

          lbOrderNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOrderNumber.setForeground(new java.awt.Color(0, 0, 0));
          lbOrderNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbOrderNumber.setText("Order Number");

          lbOrderStatus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOrderStatus.setForeground(new java.awt.Color(0, 0, 0));
          lbOrderStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbOrderStatus.setText("Order Status");

          lbCusomerID.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCusomerID.setForeground(new java.awt.Color(0, 0, 0));
          lbCusomerID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCusomerID.setText("Customer ID");

          lbPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPhoneNumber.setForeground(new java.awt.Color(0, 0, 0));
          lbPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPhoneNumber.setText("Phone Number");

          lbPaymentStatus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPaymentStatus.setForeground(new java.awt.Color(0, 0, 0));
          lbPaymentStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPaymentStatus.setText("Payment Status");

          lbDeliveryAddress.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDeliveryAddress.setForeground(new java.awt.Color(0, 0, 0));
          lbDeliveryAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbDeliveryAddress.setText("Delivery Address");

          lbCustomerNote.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCustomerNote.setForeground(new java.awt.Color(0, 0, 0));
          lbCustomerNote.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCustomerNote.setText("Customer Note");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(btnInfo)
                    .addGap(37, 37, 37)
                    .addComponent(lbOrderDate, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbOrderNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbOrderStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCusomerID, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDeliveryInfomation, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPaymentMethod, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPaymentStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDeliveryAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCustomerNote, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbOrderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCusomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDeliveryInfomation, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbDeliveryAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(headerLayout.createSequentialGroup()
                    .addComponent(lbCustomerNote, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
               .addComponent(btnInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1826, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnInfo;
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbCusomerID;
     private javax.swing.JLabel lbCustomerName;
     private javax.swing.JLabel lbCustomerNote;
     private javax.swing.JLabel lbDeliveryAddress;
     private javax.swing.JLabel lbDeliveryInfomation;
     private javax.swing.JLabel lbOrderDate;
     private javax.swing.JLabel lbOrderNumber;
     private javax.swing.JLabel lbOrderStatus;
     private javax.swing.JLabel lbPaymentMethod;
     private javax.swing.JLabel lbPaymentStatus;
     private javax.swing.JLabel lbPhoneNumber;
     private javax.swing.JLabel lbTotalAmount;
     // End of variables declaration//GEN-END:variables
}
