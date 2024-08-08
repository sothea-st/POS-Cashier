package Stock.PurchaseReceive;

public class GetDetailReceive extends javax.swing.JPanel {

    public GetDetailReceive() {
        initComponents();
    }
    
     public void setValue(
            String numberValue,
            String barcodeValue,
            String productNameValue,
            String divisionValue,
            String availableQtyValue,
            String qtyValue,
            String receivedQtyValue,
            String costValue,
            String amountValue
    ) {
        lbNumber.setText(numberValue);
        lbBarcode.setText(barcodeValue);
        lbProductName.setText(productNameValue);
        lbDivision.setText(divisionValue);
        lbAvailbleQty.setText(availableQtyValue);
        lbOrderQty.setText(qtyValue);
        if(receivedQtyValue != "null"){
            lbReceivedQty.setText(receivedQtyValue);
        }else{
            lbReceivedQty.setText("0");
        }
        
        lbCost.setText(costValue);
        lbTotalCost.setText(amountValue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        lbProductName = new javax.swing.JLabel();
        lbDivision = new javax.swing.JLabel();
        lbBarcode = new javax.swing.JLabel();
        lbAvailbleQty = new javax.swing.JLabel();
        lbOrderQty = new javax.swing.JLabel();
        lbCost = new javax.swing.JLabel();
        lbTotalCost = new javax.swing.JLabel();
        lbReceivedQty = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        header.setPreferredSize(new java.awt.Dimension(1566, 29));

        lbNumber.setBackground(new java.awt.Color(0, 0, 0));
        lbNumber.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbNumber.setForeground(new java.awt.Color(0, 0, 0));
        lbNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumber.setText("#");

        lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(0, 0, 0));
        lbProductName.setText("Product Name");

        lbDivision.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbDivision.setForeground(new java.awt.Color(0, 0, 0));
        lbDivision.setText("Division Name");

        lbBarcode.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbBarcode.setForeground(new java.awt.Color(0, 0, 0));
        lbBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBarcode.setText("Barcode");

        lbAvailbleQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbAvailbleQty.setForeground(new java.awt.Color(0, 0, 0));
        lbAvailbleQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAvailbleQty.setText("Available Qty");

        lbOrderQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbOrderQty.setForeground(new java.awt.Color(0, 0, 0));
        lbOrderQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOrderQty.setText("Ordered Qty");

        lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCost.setForeground(new java.awt.Color(0, 0, 0));
        lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCost.setText("Cost");

        lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalCost.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalCost.setText("Total Cost");

        lbReceivedQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbReceivedQty.setForeground(new java.awt.Color(0, 0, 0));
        lbReceivedQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReceivedQty.setText("Received Qty");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAvailbleQty, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbOrderQty, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbReceivedQty, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCost, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lbProductName)
                    .addComponent(lbDivision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBarcode)
                    .addComponent(lbAvailbleQty)
                    .addComponent(lbOrderQty)
                    .addComponent(lbCost)
                    .addComponent(lbTotalCost)
                    .addComponent(lbReceivedQty))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1553, Short.MAX_VALUE)
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
    private javax.swing.JLabel lbAvailbleQty;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbCost;
    private javax.swing.JLabel lbDivision;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbOrderQty;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel lbReceivedQty;
    private javax.swing.JLabel lbTotalCost;
    // End of variables declaration//GEN-END:variables
}
