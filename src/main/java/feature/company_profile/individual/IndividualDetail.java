package feature.company_profile.individual;

import Components.Color.WindowColor;

import Constant.JavaConstant;
import feature.company_profile.individual.model.IndividualModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class IndividualDetail extends javax.swing.JDialog {

     public IndividualDetail(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          setBackground(WindowColor.slightGreen);
          setTitle("Individual Detail");
     }

     public void setDetail(IndividualModel.IndividualDetail detail) {
          lbEnglishName.setLabelName(detail.getLastName() + " " + detail.getFirstName());
          lbPhoneNumber.setLabelName(JavaConstant.formatPhoneNumber(detail.getPhoneNumber()));
          lbEmail.setLabelName(detail.getEmail());
          lbAddress.setLabelName(detail.getFullAddressKh());
          lbRegisterDate.setLabelName(detail.getCreatedDate());

          if (detail.getProfileImage() != null && !detail.getProfileImage().isEmpty()) {
               JavaConstant.coverImageUrl(detail.getProfileImage(), lbFile, 124, 235);
          }

     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbProductName = new Components.Label();
          lbEnglishName = new Components.Label();
          lbProductNameKh = new Components.Label();
          lbPhoneNumber = new Components.Label();
          labelPopUpTitle2 = new Components.LabelPopUpTitle();
          lbProductNameKh1 = new Components.Label();
          lbEmail = new Components.Label();
          lbProductNameKh2 = new Components.Label();
          lbAddress = new Components.Label();
          lbProductNameKh3 = new Components.Label();
          lbRegisterDate = new Components.Label();
          lbFile = new javax.swing.JLabel();
          btnCancel = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          lbProductName.setLabelName("English Name :");

          lbEnglishName.setLabelName("");

          lbProductNameKh.setLabelName("Phone Number :");

          lbPhoneNumber.setLabelName("");

          labelPopUpTitle2.setLabelTitle("Individual Detail");

          lbProductNameKh1.setLabelName("Email :");

          lbEmail.setLabelName("");

          lbProductNameKh2.setLabelName("Address :");

          lbAddress.setLabelName("");

          lbProductNameKh3.setLabelName("Registered Date :");

          lbRegisterDate.setLabelName("");

          lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_default.jpg"))); // NOI18N
          lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                              .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbEnglishName, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                              .addComponent(lbProductNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(lbProductNameKh1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                              .addComponent(lbProductNameKh2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(lbProductNameKh3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(lbRegisterDate, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(170, 170, 170)
                    .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(171, Short.MAX_VALUE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(lbEnglishName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(lbPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductNameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductNameKh1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductNameKh2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(lbRegisterDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductNameKh3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    IndividualDetail dialog = new IndividualDetail(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                         @Override
                         public void windowClosing(java.awt.event.WindowEvent e) {
                              System.exit(0);
                         }
                    });
                    dialog.setVisible(true);
               }
          });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Button.Button btnCancel;
     private Components.LabelPopUpTitle labelPopUpTitle2;
     private Components.Label lbAddress;
     private Components.Label lbEmail;
     private Components.Label lbEnglishName;
     private javax.swing.JLabel lbFile;
     private Components.Label lbPhoneNumber;
     private Components.Label lbProductName;
     private Components.Label lbProductNameKh;
     private Components.Label lbProductNameKh1;
     private Components.Label lbProductNameKh2;
     private Components.Label lbProductNameKh3;
     private Components.Label lbRegisterDate;
     // End of variables declaration//GEN-END:variables
}
