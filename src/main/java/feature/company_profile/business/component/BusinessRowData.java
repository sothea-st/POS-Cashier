package feature.company_profile.business.component;

import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.company_profile.business.model.BusinessModel.BusinessModelDetail;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BusinessRowData extends javax.swing.JPanel {

     private BusinessModelDetail detail;

     public BusinessRowData(BusinessModelDetail detail) {
          this.detail = detail;

          initComponents();
          custom();

          setData();
     }

     private void setData() {
          String address = detail.getFullAddressKh().trim();
          lbCustomerName.setText("   " + detail.getCustomerName());
          lbComanyName.setText("   " + detail.getCompanyName());
          lbPhoneNumber.setText("   " + JavaConstant.formatPhoneNumber(detail.getPhoneNumber()));
          lbEmail.setText("   " + detail.getEmail());
          lbVatNumber.setText(detail.getVatNumber() == null || detail.getVatNumber().isEmpty() ? "" : "             " + detail.getVatNumber());

          if (JavaConstant.containsKhmer(detail.getFullAddressKh())) {
               if (address.startsWith(",")) {
                    address = address.substring(1).trim();
               }
               lbAddress.setText("            " + address);
               lbAddress.setFont(WindowFonts.khmerOsContentBold12);
          }

          lbCreatedDate.setText(" " + detail.getCreatedDate());
     }

     public void initEvent(ButtonEvent event) {
          btnDelete.addMouseListener(new MouseListener() {
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

          btnEdit.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
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

     private void custom() {
          JavaConstant.setPointer(btnEdit);
          JavaConstant.setPointer(btnDelete);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbCreatedDate = new javax.swing.JLabel();
          lbVatNumber = new javax.swing.JLabel();
          lbAddress = new javax.swing.JLabel();
          lbCustomerName = new javax.swing.JLabel();
          lbComanyName = new javax.swing.JLabel();
          lbPhoneNumber = new javax.swing.JLabel();
          lbEmail = new javax.swing.JLabel();
          btnEdit = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1487, 45));

          lbCreatedDate.setBackground(new java.awt.Color(0, 0, 0));
          lbCreatedDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCreatedDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCreatedDate.setText("\t Created Date");

          lbVatNumber.setBackground(new java.awt.Color(0, 0, 0));
          lbVatNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbVatNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbVatNumber.setText("VAT Number");

          lbAddress.setBackground(new java.awt.Color(0, 0, 0));
          lbAddress.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbAddress.setText("Address");

          lbCustomerName.setBackground(new java.awt.Color(0, 0, 0));
          lbCustomerName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCustomerName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCustomerName.setText("Customer Name");

          lbComanyName.setBackground(new java.awt.Color(0, 0, 0));
          lbComanyName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbComanyName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbComanyName.setText("Company Name");

          lbPhoneNumber.setBackground(new java.awt.Color(0, 0, 0));
          lbPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPhoneNumber.setText("Phone Number");

          lbEmail.setBackground(new java.awt.Color(0, 0, 0));
          lbEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbEmail.setText("Email");

          btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbComanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addGap(40, 40, 40)
                    .addComponent(lbVatNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCreatedDate, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGap(6, 6, 6))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbCustomerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbComanyName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbVatNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbCreatedDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, headerLayout.createSequentialGroup()
                              .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1597, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel btnEdit;
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbAddress;
     private javax.swing.JLabel lbComanyName;
     private javax.swing.JLabel lbCreatedDate;
     private javax.swing.JLabel lbCustomerName;
     private javax.swing.JLabel lbEmail;
     private javax.swing.JLabel lbPhoneNumber;
     private javax.swing.JLabel lbVatNumber;
     // End of variables declaration//GEN-END:variables
}
