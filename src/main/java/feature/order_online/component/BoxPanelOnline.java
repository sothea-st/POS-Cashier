package feature.order_online.component;

import Constant.UtilShadow;
import java.awt.Color;
import java.awt.Graphics;

public class BoxPanelOnline extends javax.swing.JPanel {

     public BoxPanelOnline() {
          initComponents();
     }

     public void setTitle(String title) {
          lbTitle.setText(title);
     }

     public void setTotalOrder(Integer totalOrder) {
          lbTotalOrder.setText(String.valueOf(totalOrder));
     }

     public void setTotalAmount(String value) {
          lbTotalAmount.setText(value);
     }

     public void setBg(Color color) {
          setBackground(color);
     }

     //======================Create Shadow Box===========================
     @Override
     protected void paintComponent(Graphics grphcs) {
          setOpaque(false);
          UtilShadow.createShadowSearch(grphcs, getWidth(), getHeight(), getBackground());
          super.paintComponent(grphcs);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbTitle = new javax.swing.JLabel();
          lbTotalOrder = new javax.swing.JLabel();
          lbTotalAmount = new javax.swing.JLabel();

          lbTitle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          lbTitle.setForeground(new java.awt.Color(255, 255, 255));
          lbTitle.setText("Title");

          lbTotalOrder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          lbTotalOrder.setForeground(new java.awt.Color(0, 0, 0));
          lbTotalOrder.setText("10");

          lbTotalAmount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
          lbTotalAmount.setForeground(new java.awt.Color(0, 0, 0));
          lbTotalAmount.setText("10");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                              .addGap(103, 103, 103))
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(lbTotalOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(44, 44, 44)
                              .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGap(36, 36, 36))))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lbTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(lbTotalOrder)
                         .addComponent(lbTotalAmount))
                    .addGap(20, 20, 20))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel lbTitle;
     private javax.swing.JLabel lbTotalAmount;
     private javax.swing.JLabel lbTotalOrder;
     // End of variables declaration//GEN-END:variables
}
