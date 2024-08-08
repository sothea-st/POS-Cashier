package Products;

import Constant.JavaConstant;

public class GetDetailProduct extends javax.swing.JPanel {

    public GetDetailProduct() {
        initComponents();
    }
    
    public void setValue(
        String numberValue,
        String importDateValue,
        String qtyValue,
        String costValue,
        String priceValue
    ) {
        lbNumber.setText(numberValue);
        if(importDateValue != "null"){
            lbImportDate.setText(JavaConstant.formateDateDDMMYYYY(importDateValue));
        }else{
            lbImportDate.setText("N/A");
        }
        lbQty.setText(qtyValue);
        lbCost.setText("$ " + costValue);
        lbPrice.setText("$ " + priceValue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        lbImportDate = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        lbCost = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        header.setPreferredSize(new java.awt.Dimension(1566, 29));

        lbNumber.setBackground(new java.awt.Color(0, 0, 0));
        lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbNumber.setForeground(new java.awt.Color(0, 0, 0));
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        lbImportDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbImportDate.setForeground(new java.awt.Color(0, 0, 0));
        lbImportDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImportDate.setText("Import Date");

        lbQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbQty.setForeground(new java.awt.Color(0, 0, 0));
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("Qty");

        lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCost.setForeground(new java.awt.Color(0, 0, 0));
        lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCost.setText("Cost");

        lbPrice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(0, 0, 0));
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrice.setText("Price");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lbImportDate)
                    .addComponent(lbQty)
                    .addComponent(lbCost)
                    .addComponent(lbPrice))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbCost;
    private javax.swing.JLabel lbImportDate;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQty;
    // End of variables declaration//GEN-END:variables
}
