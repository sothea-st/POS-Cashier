package feature.company_profile.individual.component;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.company_profile.individual.model.IndividualResponseModel;
import feature.company_profile.individual.model.IndividualResponseModel.IndividualResponseDetail;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

public class IndividualRowData extends javax.swing.JPanel {

     private IndividualResponseModel.IndividualResponseDetail detail;

     public IndividualRowData(IndividualResponseDetail detail) {
          this.detail = detail;
          initComponents();
          custom();

          setData();
     }

     private void setData() {
          lbCusomterId.setText("        " + detail.getCustomerId());
          lbGender.setText("   " + detail.getGender());
          lbNationality.setText(detail.getNationality());
          lbEngName.setText(detail.getLastName() + " " + detail.getFirstName());
          lbKhName.setText(null);
          lbPhoneNumber.setText(JavaConstant.formatPhoneNumber(detail.getPhoneNumber()));
          lbEmail.setText(detail.getEmail());
          lbTotalAmount.setText(JavaConstant.setAmount(BigDecimal.valueOf(0)));
          lbCreatedDate.setText(detail.getCreatedDate());
     }

     private void custom() {
          setBackground(WindowColor.white);

          JavaConstant.setPointer(btnEdit);
          JavaConstant.setPointer(btnView);
          JavaConstant.setPointer(btnDelete);
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

          btnView.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onView();
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

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbCusomterId = new javax.swing.JLabel();
          lbGender = new javax.swing.JLabel();
          lbNationality = new javax.swing.JLabel();
          lbEngName = new javax.swing.JLabel();
          lbKhName = new javax.swing.JLabel();
          lbPhoneNumber = new javax.swing.JLabel();
          lbEmail = new javax.swing.JLabel();
          lbTotalAmount = new javax.swing.JLabel();
          lbCreatedDate = new javax.swing.JLabel();
          btnEdit = new javax.swing.JLabel();
          btnView = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();

          setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          lbCusomterId.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCusomterId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCusomterId.setText("Customer ID");

          lbGender.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbGender.setText("Gender");

          lbNationality.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNationality.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNationality.setText("Nationality");

          lbEngName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEngName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbEngName.setText("English Name");

          lbKhName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbKhName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbKhName.setText("Khmer Name");

          lbPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPhoneNumber.setText("Phone Number");

          lbEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbEmail.setText("Email");

          lbTotalAmount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTotalAmount.setText("Total Amount Spend");

          lbCreatedDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCreatedDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCreatedDate.setText("Created Date ");

          btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

          btnView.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbCusomterId, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbGender, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbNationality, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEngName, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbKhName, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addGap(41, 41, 41)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCreatedDate, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addContainerGap())
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbCusomterId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbGender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbNationality, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbCreatedDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbKhName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(lbEngName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(btnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel btnEdit;
     private javax.swing.JLabel btnView;
     private javax.swing.JLabel lbCreatedDate;
     private javax.swing.JLabel lbCusomterId;
     private javax.swing.JLabel lbEmail;
     private javax.swing.JLabel lbEngName;
     private javax.swing.JLabel lbGender;
     private javax.swing.JLabel lbKhName;
     private javax.swing.JLabel lbNationality;
     private javax.swing.JLabel lbPhoneNumber;
     private javax.swing.JLabel lbTotalAmount;
     // End of variables declaration//GEN-END:variables
}
