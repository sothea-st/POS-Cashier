 
package feature.order_online.component;

import Constant.JavaConstant;
import feature.order_online.model.OrderOnlineModelResponse.OrderOnlineDetailResponse;
 

 
public class DetailOrderOnlineRowData extends javax.swing.JPanel {
     private OrderOnlineDetailResponse detail;
     private Integer number;
     public DetailOrderOnlineRowData(OrderOnlineDetailResponse detail,Integer number) {
          initComponents();
          
          this.detail = detail;
          this.number = number;
          
          setData();
     }

     private void setData(){
          lbNumber.setText(String.valueOf(number));
          lbBarcode.setText(detail.getBarcode());
          lbEnglishName.setText(detail.getEnglishName());
          lbKhmerName.setText(detail.getKhmerName());
          lbQty.setText(detail.getQty()+"");
          lbSalePrice.setText(JavaConstant.setAmount(detail.getSalePrice()));
          lbDiscountType.setText(detail.getDiscountPrice());
          lbDiscountPrice.setText(detail.getDiscountPrice());
          lbToal.setText(JavaConstant.setAmount(detail.getTotal()));
     }
      
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbDiscountType = new javax.swing.JLabel();
          lbNumber = new javax.swing.JLabel();
          lbDiscountPrice = new javax.swing.JLabel();
          lbKhmerName = new javax.swing.JLabel();
          lbSalePrice = new javax.swing.JLabel();
          lbBarcode = new javax.swing.JLabel();
          lbEnglishName = new javax.swing.JLabel();
          lbQty = new javax.swing.JLabel();
          lbToal = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1487, 45));

          lbDiscountType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDiscountType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbDiscountType.setText("Discount Type");

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("No");

          lbDiscountPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDiscountPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbDiscountPrice.setText("Discount Price");

          lbKhmerName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbKhmerName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbKhmerName.setText("Khmer Name");

          lbSalePrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSalePrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbSalePrice.setText("Sale Price");

          lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbBarcode.setText("Barcode");

          lbEnglishName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbEnglishName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbEnglishName.setText("English Name");

          lbQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbQty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbQty.setText("Qty");

          lbToal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbToal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbToal.setText("Total");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbEnglishName, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbKhmerName, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbQty, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDiscountType, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDiscountPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbToal, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addContainerGap())
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
               .addComponent(lbBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbEnglishName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbKhmerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbQty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbDiscountType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbDiscountPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbToal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1546, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbBarcode;
     private javax.swing.JLabel lbDiscountPrice;
     private javax.swing.JLabel lbDiscountType;
     private javax.swing.JLabel lbEnglishName;
     private javax.swing.JLabel lbKhmerName;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbQty;
     private javax.swing.JLabel lbSalePrice;
     private javax.swing.JLabel lbToal;
     // End of variables declaration//GEN-END:variables
}
