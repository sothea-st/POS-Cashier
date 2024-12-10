package Setting.Vendor;

import Event.ButtonEvent;
import feature.user_permission.JavaPermission;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class GetVendor extends javax.swing.JPanel {

     private Integer id;
     private String vendorName;
     private String vendorCode;
     private String phoneNumber;
     private String email;
     private String address;
     private Icon iconEdit;
     private Icon iconDelete;
     private String website;

     public GetVendor() {
          initComponents();
          lbId.setVisible(false);

          // check permission
          // permissionId: 20 is primary key id from table pos_permission
          btnEdit.setVisible(JavaPermission.getPermissionDetail(20).getIsUpdate());
          btnDelete.setVisible(JavaPermission.getPermissionDetail(20).getIsUpdate());

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

     public String getVendorCode() {
          return vendorCode;
     }

     public void setVendorCode(String vendorCode) {
          this.vendorCode = vendorCode;
          lbVendorCode.setText(vendorCode);
     }

     public String getPhoneNumber() {
          return phoneNumber;
     }

     public void setPhoneNumber(String phoneNumber) {
          this.phoneNumber = phoneNumber;
          lbPhoneNumber.setText(phoneNumber);
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
          lbEmail.setText(email);
     }

     public Icon getIconEdit() {
          return iconEdit;
     }

     public void setIconEdit(Icon iconEdit) {
          this.iconEdit = iconEdit;
          btnEdit.setIcon(iconEdit);
     }

     public Icon getIconDelete() {
          return iconDelete;
     }

     public void setIconDelete(Icon iconDelete) {
          this.iconDelete = iconDelete;
          btnDelete.setIcon(iconDelete);
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
          lbAddress.setText(address);
     }

     public String getWebsite() {
          return website;
     }

     public void setWebsite(String website) {
          this.website = website;
          lbWebsite.setText(website);
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

          btnDelete.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onRemove("" + id);
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
        lbVendorCode = new javax.swing.JLabel();
        lbVendorName = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        lbPhoneNumber = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbWebsite = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setPreferredSize(new java.awt.Dimension(615, 35));

        lbVendorCode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbVendorCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVendorCode.setText("Vendor Code");

        lbVendorName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbVendorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbVendorName.setText("Vendor Name");

        lbId.setText("jLabel1");

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

        lbPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhoneNumber.setText("Phone Number");

        lbEmail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbEmail.setText("Email");

        lbAddress.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbAddress.setText("Address");

        lbWebsite.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbWebsite.setText("Website");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVendorCode, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbWebsite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbVendorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbId)
                .addContainerGap(11, Short.MAX_VALUE))
            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbVendorCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbPhoneNumber;
    private javax.swing.JLabel lbVendorCode;
    private javax.swing.JLabel lbVendorName;
    private javax.swing.JLabel lbWebsite;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
