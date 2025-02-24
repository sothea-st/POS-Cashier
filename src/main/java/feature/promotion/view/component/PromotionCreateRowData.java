package feature.promotion.view.component;

import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.promotion.model.ProductPromotionResponse;
import feature.promotion.model.ProductPromotionResponse.ProductPromotionResponseDetail;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromotionCreateRowData extends javax.swing.JPanel {

     private ProductPromotionResponse.ProductPromotionResponseDetail detail;
     private Integer number;
     private Integer productId;
     
     public PromotionCreateRowData(ProductPromotionResponseDetail detail, Integer number) {

          this.detail = detail;
          this.number = number;

          initComponents();

          setData();

          JavaConstant.setPointer(btnDelete);
     }

     public void initEvent(ButtonEvent event) {

          btnDelete.addMouseListener(new MouseListener() {
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

     public void setData() {
          this.productId = detail.getProductId();
          lbNumber.setText(String.valueOf(number));
          lbBarcode.setText(detail.getBarcode());
          lbCategoryName.setText(detail.getCategoryName());
          lbEnglishDesc.setText(detail.getEnglishDescription());

          if (JavaConstant.containsKhmer(detail.getKhrDescription())) {
               lbKhrDesc.setFont(WindowFonts.khmerOsContent12);
          }

          lbKhrDesc.setText(detail.getKhrDescription());

          lbDivision.setText(detail.getDivision());
          lbDepartment.setText(detail.getDepartment());
          lbPercentage.setText(detail.getPercentage());
          lbSalePrice.setText(JavaConstant.setAmount(detail.getSalePrice()));
          lbAfterDiscount.setText(JavaConstant.setAmount(detail.getAfterDiscount()));
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          lbBarcode = new javax.swing.JLabel();
          lbNumber = new javax.swing.JLabel();
          lbCategoryName = new javax.swing.JLabel();
          lbKhrDesc = new javax.swing.JLabel();
          lbEnglishDesc = new javax.swing.JLabel();
          lbDepartment = new javax.swing.JLabel();
          btnDelete = new javax.swing.JLabel();
          lbSalePrice = new javax.swing.JLabel();
          lbDivision = new javax.swing.JLabel();
          lbPercentage = new javax.swing.JLabel();
          lbAfterDiscount = new javax.swing.JLabel();

          panel.setBackground(new java.awt.Color(255, 255, 255));
          panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          panel.setPreferredSize(new java.awt.Dimension(400, 45));

          lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbBarcode.setText("Barcode");

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("No");

          lbCategoryName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbCategoryName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCategoryName.setText("Category Name");

          lbKhrDesc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbKhrDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbKhrDesc.setText("Khmer Description");

          lbEnglishDesc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbEnglishDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbEnglishDesc.setText("English Description");

          lbDepartment.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbDepartment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDepartment.setText("Department Name");

          btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N

          lbSalePrice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbSalePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbSalePrice.setText("Sale Price");

          lbDivision.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbDivision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDivision.setText("Division Name");

          lbPercentage.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbPercentage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbPercentage.setText("Percentage");

          lbAfterDiscount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbAfterDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbAfterDiscount.setText("\tAfter Discount");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEnglishDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbKhrDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbAfterDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(18, 18, 18))
               .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                         .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                         .addGap(1257, 1257, 1257)))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbKhrDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbEnglishDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(lbAfterDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE))
               .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                         .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 14, Short.MAX_VALUE)))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1347, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel btnDelete;
     private javax.swing.JLabel lbAfterDiscount;
     private javax.swing.JLabel lbBarcode;
     private javax.swing.JLabel lbCategoryName;
     private javax.swing.JLabel lbDepartment;
     private javax.swing.JLabel lbDivision;
     private javax.swing.JLabel lbEnglishDesc;
     private javax.swing.JLabel lbKhrDesc;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbPercentage;
     private javax.swing.JLabel lbSalePrice;
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables
}
