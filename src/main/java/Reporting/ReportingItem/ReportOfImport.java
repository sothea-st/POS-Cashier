package Reporting.ReportingItem;

/**
 *
 * @author MOBILE-APP.02
 */
public class ReportOfImport extends javax.swing.JPanel {

     public ReportOfImport() {
          initComponents();
     }

     public void setValue(
          String number,
          String productName,
          String productBarcode,
          String cost,
          String qty,
          String amount,
          String total,
          String discount,
          String impDate
          ) {
          lbNumber.setText(number);
          lbProductName.setText(productName);
          lbProductBarcode.setText(productBarcode);
          lbCost.setText("$ "+ cost);
          lbQuantity.setText(qty);
          lbAmount.setText("$ "+amount);
          lbTotal.setText("$ "+total);
          lbDiscount.setText("$ "+discount);
          lbImport.setText(impDate);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        lbProductBarcode = new javax.swing.JLabel();
        lbCost = new javax.swing.JLabel();
        lbProductName = new javax.swing.JLabel();
        lbQuantity = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        lbDiscount = new javax.swing.JLabel();
        lbImport = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbNumber.setBackground(new java.awt.Color(0, 0, 0));
        lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbNumber.setForeground(new java.awt.Color(0, 0, 0));
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        lbProductBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbProductBarcode.setForeground(new java.awt.Color(0, 0, 0));
        lbProductBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProductBarcode.setText("Product Barcode");

        lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCost.setForeground(new java.awt.Color(0, 0, 0));
        lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCost.setText("Cost");

        lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(0, 0, 0));
        lbProductName.setText("Product Name");

        lbQuantity.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbQuantity.setForeground(new java.awt.Color(0, 0, 0));
        lbQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQuantity.setText("Quantity");

        lbAmount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbAmount.setForeground(new java.awt.Color(0, 0, 0));
        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAmount.setText("Amount");

        lbDiscount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbDiscount.setForeground(new java.awt.Color(0, 0, 0));
        lbDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDiscount.setText("Discount");

        lbImport.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbImport.setForeground(new java.awt.Color(0, 0, 0));
        lbImport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImport.setText("Import Date");

        lbTotal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 0, 0));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("Total");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImport, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lbProductBarcode)
                    .addComponent(lbCost)
                    .addComponent(lbQuantity)
                    .addComponent(lbProductName)
                    .addComponent(lbAmount)
                    .addComponent(lbDiscount)
                    .addComponent(lbImport)
                    .addComponent(lbTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbCost;
    private javax.swing.JLabel lbDiscount;
    private javax.swing.JLabel lbImport;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbProductBarcode;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JLabel lbTotal;
    // End of variables declaration//GEN-END:variables
}
