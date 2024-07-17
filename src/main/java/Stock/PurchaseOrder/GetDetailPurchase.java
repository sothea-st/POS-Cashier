package Stock.PurchaseOrder;

public class GetDetailPurchase extends javax.swing.JPanel {

    public void setValue(
            String numberValue,
            String barcodeValue,
            String productNameValue,
            String diisionValue,
            String departmentValue,
            String categoryValue,
            String subCategoryValue,
            String availableQtyValue,
            String qtyValue,
            String costValue,
            String amountValue
    ) {
        lbNumber.setText(numberValue);
        lbBarcode.setText(barcodeValue);
        lbProductName.setText(productNameValue);
        lbDivision.setText(diisionValue);
        lbDepartment.setText(departmentValue);
        lbCategory.setText(categoryValue);
        lbSubCategory.setText(subCategoryValue);
        lbAvailbleQty.setText(availableQtyValue);
        lbQty.setText(qtyValue);
        lbCost.setText("$ " + costValue);
        lbTotalCost.setText("$ " + amountValue);
    }
    
    public GetDetailPurchase() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNumber = new javax.swing.JLabel();
        lbProductName = new javax.swing.JLabel();
        lbDivision = new javax.swing.JLabel();
        lbBarcode = new javax.swing.JLabel();
        lbDepartment = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        lbAvailbleQty = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        lbSubCategory = new javax.swing.JLabel();
        lbCost = new javax.swing.JLabel();
        lbTotalCost = new javax.swing.JLabel();

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

        lbDepartment.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbDepartment.setForeground(new java.awt.Color(0, 0, 0));
        lbDepartment.setText("Department Name");

        lbCategory.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCategory.setForeground(new java.awt.Color(0, 0, 0));
        lbCategory.setText("Category Name");

        lbAvailbleQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbAvailbleQty.setForeground(new java.awt.Color(0, 0, 0));
        lbAvailbleQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAvailbleQty.setText("Available Qty");

        lbQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbQty.setForeground(new java.awt.Color(0, 0, 0));
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("Order Qty");

        lbSubCategory.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbSubCategory.setForeground(new java.awt.Color(0, 0, 0));
        lbSubCategory.setText("Sub Category Name");

        lbCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbCost.setForeground(new java.awt.Color(0, 0, 0));
        lbCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCost.setText("Cost");

        lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalCost.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalCost.setText("Total Cost");

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
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAvailbleQty, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCost, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lbProductName)
                    .addComponent(lbDivision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartment)
                    .addComponent(lbBarcode)
                    .addComponent(lbCategory)
                    .addComponent(lbAvailbleQty)
                    .addComponent(lbQty)
                    .addComponent(lbSubCategory)
                    .addComponent(lbCost)
                    .addComponent(lbTotalCost))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1565, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbAvailbleQty;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbCost;
    private javax.swing.JLabel lbDepartment;
    private javax.swing.JLabel lbDivision;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel lbQty;
    private javax.swing.JLabel lbSubCategory;
    private javax.swing.JLabel lbTotalCost;
    // End of variables declaration//GEN-END:variables
}
