package feature.invoice_listing.component;

import Constant.JavaConstant;
import Model.Report.ReportSaleDetail;
import java.awt.Dimension;

public class InvoiceRowData extends javax.swing.JPanel {

     private ReportSaleDetail detail;
     private Integer number;

     public InvoiceRowData(ReportSaleDetail detail, Integer number) {

          this.detail = detail;
          this.number = number;

          initComponents();

          setData();

//          setPreferredSize(new Dimension(2120, 100));
     }

     private void setData() {
          lbNumber.setText(String.valueOf(number));
          lbCreateDate.setText(JavaConstant.formateDateDDMMYYYY(detail.getSaleDate()));
          lbTransactionType.setText(detail.getTransactionType());
          lbInvoiceNumber.setText(detail.getInvoiceNumber());
          lbPosId.setText(detail.getPosId());
          lbCashier.setText(detail.getUserName());
          lbTotalOrder.setText(JavaConstant.setAmount(detail.getTotalOrder()));
          lbPaymentStatus.setText(detail.getPaymentStatus());
          lbPaymentMethod.setText(detail.getPaymentMethod());
          lbOrderSource.setText(detail.getOrderSource());
          lbCustomerType.setText(detail.getCustomerType());
          lbSalePrice.setText(JavaConstant.setAmount(detail.getPrice()));
          lbDiscountOffer.setText(String.valueOf(detail.getDiscount()));
          lbDiscountOrder.setText(String.valueOf(detail.getDiscount()));
          lbSaleIncTax.setText(JavaConstant.setAmount(detail.getAmountWithTax()));
          lbTaxableAmount.setText(JavaConstant.setAmount(detail.getTotalSaledExcludeVAT()));
          lbTaxAmount.setText(JavaConstant.setAmount(detail.getVatAmt()));
          lbNetSaleValue.setText(JavaConstant.setAmount(detail.getNetSale()));
          lbCash.setText(JavaConstant.setAmount(detail.getCost()));
          lbKhqrCode.setText(JavaConstant.setAmount(detail.getKhqrCode()));

          // Refresh UI
          revalidate();
          repaint();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          header = new javax.swing.JPanel();
          lbNumber = new javax.swing.JLabel();
          lbPaymentMethod = new javax.swing.JLabel();
          lbInvoiceNumber = new javax.swing.JLabel();
          lbCashier = new javax.swing.JLabel();
          lbPaymentStatus = new javax.swing.JLabel();
          lbOrderSource = new javax.swing.JLabel();
          lbDiscountOrder = new javax.swing.JLabel();
          lbTransactionType = new javax.swing.JLabel();
          lbPosId = new javax.swing.JLabel();
          lbTaxableAmount = new javax.swing.JLabel();
          lbCreateDate = new javax.swing.JLabel();
          lbTotalOrder = new javax.swing.JLabel();
          lbCustomerType = new javax.swing.JLabel();
          lbSalePrice = new javax.swing.JLabel();
          lbDiscountOffer = new javax.swing.JLabel();
          lbSaleIncTax = new javax.swing.JLabel();
          lbTaxAmount = new javax.swing.JLabel();
          lbNetSaleValue = new javax.swing.JLabel();
          lbCash = new javax.swing.JLabel();
          lbKhqrCode = new javax.swing.JLabel();

          header.setBackground(new java.awt.Color(255, 255, 255));
          header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

          lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNumber.setText("#");

          lbPaymentMethod.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPaymentMethod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPaymentMethod.setText("Payment Method");

          lbInvoiceNumber.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbInvoiceNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbInvoiceNumber.setText("Invoice Number");

          lbCashier.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCashier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCashier.setText("Cashier");

          lbPaymentStatus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPaymentStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPaymentStatus.setText("Payment Status");

          lbOrderSource.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbOrderSource.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbOrderSource.setText("Order Source");

          lbDiscountOrder.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDiscountOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbDiscountOrder.setText("Discunt Order");

          lbTransactionType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTransactionType.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
          lbTransactionType.setText("Transaction Type");

          lbPosId.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbPosId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbPosId.setText("POS ID");

          lbTaxableAmount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTaxableAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTaxableAmount.setText("Taxable Amount");

          lbCreateDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCreateDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCreateDate.setText("Create Date");

          lbTotalOrder.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTotalOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbTotalOrder.setText("Total Order");

          lbCustomerType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCustomerType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbCustomerType.setText("Customer Type");

          lbSalePrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSalePrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbSalePrice.setText("Sale Price");

          lbDiscountOffer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbDiscountOffer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
          lbDiscountOffer.setText("Discount Offer");

          lbSaleIncTax.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbSaleIncTax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbSaleIncTax.setText("Sale (inc.tax)");

          lbTaxAmount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbTaxAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbTaxAmount.setText("Tax Amount");

          lbNetSaleValue.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbNetSaleValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbNetSaleValue.setText("Net Sale Value");

          lbCash.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbCash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbCash.setText("Cash");

          lbKhqrCode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbKhqrCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbKhqrCode.setText("KHQR Code");

          javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
          header.setLayout(headerLayout);
          headerLayout.setHorizontalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(headerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(lbTransactionType)
                    .addGap(70, 70, 70)
                    .addComponent(lbInvoiceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(lbPosId, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lbCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTotalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lbOrderSource, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDiscountOffer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbDiscountOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSaleIncTax, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lbTaxableAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbTaxAmount)
                    .addGap(18, 18, 18)
                    .addComponent(lbNetSaleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbCash, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbKhqrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          headerLayout.setVerticalGroup(
               headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lbCreateDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbPosId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbCashier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbTotalOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbPaymentStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbPaymentMethod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbOrderSource, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbCustomerType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbSalePrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbDiscountOffer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbDiscountOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbSaleIncTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbTaxableAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbTaxAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbNetSaleValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(lbKhqrCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInvoiceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 2139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel header;
     private javax.swing.JLabel lbCash;
     private javax.swing.JLabel lbCashier;
     private javax.swing.JLabel lbCreateDate;
     private javax.swing.JLabel lbCustomerType;
     private javax.swing.JLabel lbDiscountOffer;
     private javax.swing.JLabel lbDiscountOrder;
     private javax.swing.JLabel lbInvoiceNumber;
     private javax.swing.JLabel lbKhqrCode;
     private javax.swing.JLabel lbNetSaleValue;
     private javax.swing.JLabel lbNumber;
     private javax.swing.JLabel lbOrderSource;
     private javax.swing.JLabel lbPaymentMethod;
     private javax.swing.JLabel lbPaymentStatus;
     private javax.swing.JLabel lbPosId;
     private javax.swing.JLabel lbSaleIncTax;
     private javax.swing.JLabel lbSalePrice;
     private javax.swing.JLabel lbTaxAmount;
     private javax.swing.JLabel lbTaxableAmount;
     private javax.swing.JLabel lbTotalOrder;
     private javax.swing.JLabel lbTransactionType;
     // End of variables declaration//GEN-END:variables
}
