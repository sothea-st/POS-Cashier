package Reporting.ReportingItem;

import Constant.JavaConstant;

/**
 *
 * @author MOBILE-APP.02
 */
public class ReportOfSaled extends javax.swing.JPanel {

     public ReportOfSaled() {
          initComponents();
     }

     public void setValue(
          String numberValue,
          String transactionValue,
          String dateValue,
          String productNameValue,
          String qtyValue,
          String priceValue,
          String discountValue,
          String amountValue,
          String taxTypeValue,
          String totalSaleValue,
          String vatAmtValue,
          String pltValue,
          String netSaleValue,
          String costValue,
          String marginValue,
          String staffValue
     ) {
          number.setText(numberValue);
          transactionNumber.setText(transactionValue);
          
          if(dateValue != "null"){
               date.setText(JavaConstant.formateDateDDMMYYYY(dateValue));
          }else{
              date.setText("N/A");
          }
          
          productName.setText(productNameValue);
          qty.setText(qtyValue);
          price.setText("$ "+priceValue);
          discount.setText("$ "+discountValue);
          amount.setText("$ "+amountValue);
          taxType.setText(taxTypeValue);
          totalSaled.setText("$ "+totalSaleValue);
          vatAmt.setText("$ "+vatAmtValue);
          plt.setText("$ "+pltValue);
          netSale.setText("$ "+netSaleValue);
          cost.setText("$ "+costValue);
          margin.setText("$ "+marginValue);
          staff.setText(staffValue);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          number = new javax.swing.JLabel();
          taxType = new javax.swing.JLabel();
          productName = new javax.swing.JLabel();
          price = new javax.swing.JLabel();
          amount = new javax.swing.JLabel();
          totalSaled = new javax.swing.JLabel();
          cost = new javax.swing.JLabel();
          date = new javax.swing.JLabel();
          qty = new javax.swing.JLabel();
          margin = new javax.swing.JLabel();
          transactionNumber = new javax.swing.JLabel();
          discount = new javax.swing.JLabel();
          vatAmt = new javax.swing.JLabel();
          plt = new javax.swing.JLabel();
          netSale = new javax.swing.JLabel();
          staff = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
          header.setPreferredSize(new java.awt.Dimension(1839, 35));

          number.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          number.setText("#");

          taxType.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          taxType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          taxType.setText("Tax Type ");

          productName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          productName.setText("Product Name");

          price.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          price.setText("Price");

          amount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          amount.setText("Amount (Include Tax) ");

          totalSaled.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          totalSaled.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          totalSaled.setText("Total Sale Exclude VAT ");

          cost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          cost.setText("Cost ");

          date.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          date.setText("Date");

          qty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          qty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          qty.setText("Qty");

          margin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          margin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          margin.setText("Margin ");

          transactionNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          transactionNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          transactionNumber.setText("# Trans. ");

          discount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          discount.setText("Discount");

          vatAmt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          vatAmt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          vatAmt.setText("VAT Amt ");

          plt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          plt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          plt.setText("PLT ");

          netSale.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          netSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          netSale.setText("Net Sale ");

          staff.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          staff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          staff.setText("staff");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(transactionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(taxType, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(totalSaled, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(vatAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(plt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(netSale, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(margin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(staff, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(75, 75, 75))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(number)
                         .addComponent(price)
                         .addComponent(cost)
                         .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(qty)
                         .addComponent(margin)
                         .addComponent(transactionNumber)
                         .addComponent(plt)
                         .addComponent(netSale)
                         .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(taxType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(totalSaled, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(vatAmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(staff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1797, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1797, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 0, Short.MAX_VALUE)))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 40, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                         .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(0, 0, Short.MAX_VALUE)))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel amount;
     private javax.swing.JLabel cost;
     private javax.swing.JLabel date;
     private javax.swing.JLabel discount;
     private javax.swing.JPanel header;
     private javax.swing.JLabel margin;
     private javax.swing.JLabel netSale;
     private javax.swing.JLabel number;
     private javax.swing.JLabel plt;
     private javax.swing.JLabel price;
     private javax.swing.JLabel productName;
     private javax.swing.JLabel qty;
     private javax.swing.JLabel staff;
     private javax.swing.JLabel taxType;
     private javax.swing.JLabel totalSaled;
     private javax.swing.JLabel transactionNumber;
     private javax.swing.JLabel vatAmt;
     // End of variables declaration//GEN-END:variables
}
