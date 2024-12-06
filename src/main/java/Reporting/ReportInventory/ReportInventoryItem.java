package Reporting.ReportInventory;

public class ReportInventoryItem extends javax.swing.JPanel {

     public ReportInventoryItem() {
          initComponents();
     }

     public void setData(
          String number,
          String _date,
          String _productName,
          String _beginningQty,
          String _stockIn,
          String _availableQty,
          String _stockOutQty,
          String _returnOutQty,
          String _endingQty,
          String _returnInQty
     ) {
          lbNumber.setText(number);
          date.setText(_date);
          productName.setText(_productName);
          beginningQty.setText(_beginningQty);
          stockIn.setText(_stockIn);
          availableQty.setText(_availableQty);
          stockOutQty.setText(_stockOutQty);
          returnOutQty.setText(_returnOutQty);
          endingQty.setText(_endingQty);
          returnInQty.setText(_returnInQty);
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        beginningQty = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        stockIn = new javax.swing.JLabel();
        availableQty = new javax.swing.JLabel();
        returnOutQty = new javax.swing.JLabel();
        endingQty = new javax.swing.JLabel();
        stockOutQty = new javax.swing.JLabel();
        returnInQty = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbNumber.setBackground(new java.awt.Color(0, 0, 0));
        lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbNumber.setForeground(new java.awt.Color(0, 0, 0));
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        productName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        productName.setForeground(new java.awt.Color(0, 0, 0));
        productName.setText("product Name");

        beginningQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        beginningQty.setForeground(new java.awt.Color(0, 0, 0));
        beginningQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beginningQty.setText("beginning Qty");

        date.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        date.setForeground(new java.awt.Color(0, 0, 0));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("Date");

        stockIn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        stockIn.setForeground(new java.awt.Color(0, 0, 0));
        stockIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stockIn.setText("stock in");

        availableQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        availableQty.setForeground(new java.awt.Color(0, 0, 0));
        availableQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableQty.setText("available qty");

        returnOutQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        returnOutQty.setForeground(new java.awt.Color(0, 0, 0));
        returnOutQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        returnOutQty.setText("return out qty");

        endingQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        endingQty.setForeground(new java.awt.Color(0, 0, 0));
        endingQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        endingQty.setText("Total Cost");

        stockOutQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        stockOutQty.setForeground(new java.awt.Color(0, 0, 0));
        stockOutQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stockOutQty.setText("stock out qty");

        returnInQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        returnInQty.setForeground(new java.awt.Color(0, 0, 0));
        returnInQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        returnInQty.setText("return in qty");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginningQty, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockIn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availableQty, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockOutQty, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnInQty, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnOutQty, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endingQty, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lbNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(beginningQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stockIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(availableQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stockOutQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(returnInQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(returnOutQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(endingQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1411, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableQty;
    private javax.swing.JLabel beginningQty;
    private javax.swing.JLabel date;
    private javax.swing.JLabel endingQty;
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel returnInQty;
    private javax.swing.JLabel returnOutQty;
    private javax.swing.JLabel stockIn;
    private javax.swing.JLabel stockOutQty;
    // End of variables declaration//GEN-END:variables
}
