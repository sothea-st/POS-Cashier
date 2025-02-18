package feature.main_detail;

import Components.Fonts.WindowFonts;
import static Components.Label.containsKhmer;

public class MainTitleDetail extends javax.swing.JPanel {

     public MainTitleDetail() {
          initComponents();
          

     }

     public void setLabelName(String labelName) {
          lbTitle.setText(labelName);
          if (containsKhmer(lbTitle.getText())) {
               lbTitle.setFont(WindowFonts.khmerOsContent14);
          } else {
               lbTitle.setFont(WindowFonts.timeNewRomanBold14);
          }
     }

     public void setData(String text) {
          lbDetail.setText(text);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbTitle = new javax.swing.JLabel();
          lbDot = new javax.swing.JLabel();
          lbDetail = new javax.swing.JLabel();

          lbTitle.setText("Transaction Date");

          lbDot.setText(":");

          lbDetail.setText("jLabel1");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(lbDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(lbDot)
                         .addComponent(lbDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel lbDetail;
     private javax.swing.JLabel lbDot;
     private javax.swing.JLabel lbTitle;
     // End of variables declaration//GEN-END:variables
}
