package feature.company_profile;

import Components.Color.WindowColor;
import feature.company_profile.business.view.BusinessView;
import feature.company_profile.individual.view.IndividualView;
import javax.swing.JFrame;

public class CompanyProfileView extends javax.swing.JDialog {
     
     public CompanyProfileView(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          
          custom();
     }
     
     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          setBackground(WindowColor.slightGreen);
          setTitle("Company Profile");
     }
     
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          objPromotion1 = new Components.SettingBox();
          objCompanyProfile = new Components.SettingBox();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          objPromotion1.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/individual.png"))); // NOI18N
          objPromotion1.setTitle("Individual");
          objPromotion1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objPromotion1MouseClicked(evt);
               }
          });

          objCompanyProfile.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/stock/business.png"))); // NOI18N
          objCompanyProfile.setInheritsPopupMenu(true);
          objCompanyProfile.setOpaque(false);
          objCompanyProfile.setTitle("Business");
          objCompanyProfile.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objCompanyProfileMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(objPromotion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(objCompanyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(320, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objPromotion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objCompanyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(385, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void objPromotion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objPromotion1MouseClicked
          dispose();
          IndividualView individualView = new IndividualView(new JFrame(), true);
          individualView.setVisible(true);
     }//GEN-LAST:event_objPromotion1MouseClicked

     private void objCompanyProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_objCompanyProfileMouseClicked
          dispose();
          BusinessView businessView = new BusinessView(new JFrame(), true);
          businessView.setVisible(true);
     }//GEN-LAST:event_objCompanyProfileMouseClicked
     
     public static void main(String args[]) {
          
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    CompanyProfileView dialog = new CompanyProfileView(new javax.swing.JFrame(), true);
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
     private Components.SettingBox objCompanyProfile;
     private Components.SettingBox objPromotion1;
     // End of variables declaration//GEN-END:variables
}
