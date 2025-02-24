package feature.promotion.view.component.dialog_description;

import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.promotion.model.ProductPromotionResponse.ProductPromotionResponseDetail;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DescriptionRowData extends javax.swing.JPanel {

     private ProductPromotionResponseDetail detail;

     private String iconChecked = "/icon/checked.png";
     private String iconUnChecked = "/icon/check.png";
     private Boolean isChcek;

     public DescriptionRowData(ProductPromotionResponseDetail detail) {

          this.detail = detail;

          initComponents();

          setData();

          JavaConstant.setPointer(btnCheck);
     }

     public void initEvent(ButtonEvent event) {
          btnCheck.addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {
                    event.onMouseClick();
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

     public void checked(Boolean value) {
          
          this.isChcek = value;

          ImageIcon icon = new ImageIcon(getClass().getResource(value ? iconChecked : iconUnChecked));

          btnCheck.setIcon(icon);
     }

     private void setData() {
          lbBarcode.setText(detail.getBarcode());
          lbCategoryName.setText(detail.getCategoryName());
          lbEngDesc.setText(detail.getEnglishDescription());
          lbOnHandQty.setText(String.valueOf(detail.getOnHandQty()));
          lbSalePrice.setText("      " + JavaConstant.setAmount(detail.getSalePrice()));
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          lbBarcode = new javax.swing.JLabel();
          jLabel2 = new javax.swing.JLabel();
          lbCategoryName = new javax.swing.JLabel();
          lbOnHandQty = new javax.swing.JLabel();
          lbEngDesc = new javax.swing.JLabel();
          lbSalePrice = new javax.swing.JLabel();
          btnCheck = new javax.swing.JLabel();

          panel.setBackground(new java.awt.Color(255, 255, 255));
          panel.setPreferredSize(new java.awt.Dimension(1257, 45));

          lbBarcode.setBackground(new java.awt.Color(255, 255, 255));
          lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbBarcode.setText("Barcode");

          jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          jLabel2.setForeground(new java.awt.Color(255, 255, 255));
          jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel2.setText("No");

          lbCategoryName.setBackground(new java.awt.Color(255, 255, 255));
          lbCategoryName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCategoryName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCategoryName.setText("Category Name");

          lbOnHandQty.setBackground(new java.awt.Color(255, 255, 255));
          lbOnHandQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOnHandQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbOnHandQty.setText("On Hand Qty ");

          lbEngDesc.setBackground(new java.awt.Color(255, 255, 255));
          lbEngDesc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEngDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbEngDesc.setText("English Description");

          lbSalePrice.setBackground(new java.awt.Color(255, 255, 255));
          lbSalePrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSalePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbSalePrice.setText("Sale Price");

          btnCheck.setBackground(new java.awt.Color(255, 255, 255));
          btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)
                    .addComponent(lbCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEngDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lbOnHandQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(402, 402, 402))
               .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                         .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                         .addGap(1257, 1257, 1257)))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbEngDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbOnHandQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                         .addComponent(btnCheck, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                         .addComponent(lbBarcode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
               .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                         .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 14, Short.MAX_VALUE)))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 867, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnCheck;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel lbBarcode;
     private javax.swing.JLabel lbCategoryName;
     private javax.swing.JLabel lbEngDesc;
     private javax.swing.JLabel lbOnHandQty;
     private javax.swing.JLabel lbSalePrice;
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables
}
