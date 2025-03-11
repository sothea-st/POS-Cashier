package feature.report.report_vendor.component;

import Constant.JavaConstant;
import feature.report.report_vendor.model.ReportVendorResponse.ReportVendorResponseDetail;

public class ReportVendorRowData extends javax.swing.JPanel {

     private ReportVendorResponseDetail detail;
     private Integer index;

     public ReportVendorRowData(ReportVendorResponseDetail detail, Integer index) {
          initComponents();
          this.detail = detail;
          this.index = index;
          
          setData();
     }

     private void setData(){
          lbNo.setText(String.valueOf(index));
          lbSupplierName.setText(detail.getVendorName());
          lbPhoneNumber.setText("  "+JavaConstant.formatPhoneNumber(detail.getContact()));
          lbEmail.setText("  "+detail.getEmail());
          lbWebSite.setText(detail.getWebsite());
          lbAddress.setText(detail.getAddress());
     }
     
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbNo = new javax.swing.JLabel();
          lbAddress = new javax.swing.JLabel();
          lbSupplierName = new javax.swing.JLabel();
          lbPhoneNumber = new javax.swing.JLabel();
          lbEmail = new javax.swing.JLabel();
          lbWebSite = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1487, 45));

          lbNo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNo.setForeground(new java.awt.Color(0, 0, 0));
          lbNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNo.setText("No.");

          lbAddress.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbAddress.setForeground(new java.awt.Color(0, 0, 0));
          lbAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbAddress.setText("Address");

          lbSupplierName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSupplierName.setForeground(new java.awt.Color(0, 0, 0));
          lbSupplierName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbSupplierName.setText("Supplier Name");

          lbPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPhoneNumber.setForeground(new java.awt.Color(0, 0, 0));
          lbPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPhoneNumber.setText("Phone Number");

          lbEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEmail.setForeground(new java.awt.Color(0, 0, 0));
          lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbEmail.setText("Email");

          lbWebSite.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbWebSite.setForeground(new java.awt.Color(0, 0, 0));
          lbWebSite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbWebSite.setText("Website");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addComponent(lbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(lbPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbWebSite, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbSupplierName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(lbAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(lbWebSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1590, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbAddress;
     private javax.swing.JLabel lbEmail;
     private javax.swing.JLabel lbNo;
     private javax.swing.JLabel lbPhoneNumber;
     private javax.swing.JLabel lbSupplierName;
     private javax.swing.JLabel lbWebSite;
     // End of variables declaration//GEN-END:variables
}
